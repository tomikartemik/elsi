package com.komandor.komandor.cryptopro

import android.content.ContentResolver
import android.content.Context
import android.content.pm.ApplicationInfo
import android.util.Base64
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.documentfile.provider.DocumentFile
import com.komandor.komandor.R
import com.komandor.komandor.cryptopro.base.EncryptDecryptData
import com.komandor.komandor.cryptopro.model.DecryptedSessionKey
import com.komandor.komandor.cryptopro.model.EncryptedFile
import com.komandor.komandor.cryptopro.model.EncryptedMessage
import com.komandor.komandor.cryptopro.utils.KeyStoreType
import com.komandor.komandor.cryptopro.utils.KeyStoreUtil
import com.komandor.komandor.cryptopro.utils.ProviderServiceInfo
import com.komandor.komandor.data.api.model.EncryptedSessionKey
import com.komandor.komandor.data.database.model.Message
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.extension.getClientName
import com.komandor.komandor.extension.getCompanyName
import com.komandor.komandor.utils.SystemUtils
import com.objsys.asn1j.runtime.*
import org.apache.xml.security.utils.resolver.ResourceResolver
import ru.CryptoPro.AdES.AdESConfig
import ru.CryptoPro.JCP.ASN.CryptographicMessageSyntax.*
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_EncryptedKey
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_Key
import ru.CryptoPro.JCP.ASN.Gost28147_89_EncryptionSyntax.Gost28147_89_MAC
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.CertificateSerialNumber
import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Name
import ru.CryptoPro.JCP.JCP
import ru.CryptoPro.JCP.KeyStore.JCPPrivateKeyEntry
import ru.CryptoPro.JCP.params.CryptParamsSpec
import ru.CryptoPro.JCP.params.JCPProtectionParameter
import ru.CryptoPro.JCP.params.OID
import ru.CryptoPro.JCP.spec.GostCipherSpec
import ru.CryptoPro.JCP.tools.AlgorithmUtility
import ru.CryptoPro.JCP.tools.Array
import ru.CryptoPro.JCPxml.XmlInit
import ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI
import ru.CryptoPro.JCSP.CSPConfig
import ru.CryptoPro.JCSP.JCSP
import ru.CryptoPro.JCSP.support.BKSTrustStore
import ru.CryptoPro.reprov.RevCheck
import ru.CryptoPro.ssl.util.cpSSLConfig
import ru.cprocsp.ACSP.tools.common.CSPTool
import ru.cprocsp.ACSP.tools.config.ConfigInterface
import ru.cprocsp.ACSP.tools.integrity.ACSPIntegrity
import ru.cprocsp.ACSP.tools.integrity.CSPIntegrityConstants
import ru.cprocsp.ACSP.tools.integrity.IntegrityInterface
import ru.cprocsp.ACSP.tools.license.CSPLicenseConstants
import ru.cprocsp.ACSP.tools.license.LicenseInterface
import timber.log.Timber
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.nio.ByteBuffer
import java.security.*
import java.security.Signature
import java.security.cert.*
import java.security.cert.Certificate
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyAgreement
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
..import ru.CryptoPro.JCP.ASN.PKIX1Explicit88.SubjectPublicKeyInfo;


class CryproProManager(val context: Context) {
    private val CIPHER_ALG = "GOST28147"
    private val DIGEST_OID = JCP.GOST_DIGEST_OID
    private val CMS_SIGNATURE_ALGORITHM = JCP.GOST_EL_SIGN_NAME
    private val STR_CMS_OID_DATA = "1.2.840.113549.1.7.1"
    private val STR_CMS_OID_SIGNED = "1.2.840.113549.1.7.2"
    private val CIPHER_DATA_ALG_PARAMS = "/CFB/NoPadding"

    var cspTool: CSPTool? = null
    var currentPrivateKey: PrivateKey? = null
    var currentUser: User? = null

    private val CIPHER_KEY_ALG_PARAMS = "/SIMPLE_EXPORT/NoPadding"

    init {

    }
    /************************ Инициализация провайдера  */
    /**
     * Инициализация CSP провайдера.
     *
     * @return True в случае успешной инициализации.
     */
    internal fun initCSPProviders(): Pair<Boolean, String> {

        // Инициализация провайдера CSP. Должна выполняться
        // один раз в главном потоке приложения, т.к. использует
        // статические переменные.
        //
        // Далее может быть использована версия функции инициализации:
        // 1) расширенная - initEx(): она содержит init() и дополнительно
        // выполняет загрузку java-провайдеров (Java CSP, RevCheck, Java TLS)
        // и настройку некоторых параметров, например, Java TLS;
        // 2) обычная - init(): без загрузки java-провайдеров и настройки
        // параметров.
        //
        // Для совместного использования ГОСТ и не-ГОСТ TLS НЕ следует
        // переопределять свойства System.getProperty(javax.net.*) и
        // Security.setProperty(ssl.*).
        //
        // Ниже используется обычная версия init() функции инициализации
        // и свойства TLS переопределяются, т.к. в приложении имеется пример
        // работы с УЦ 1.5, который обращается к свойствам по умолчанию.
        //
        // 1. Создаем инфраструктуру CSP и копируем ресурсы
        // в папку. В случае ошибки мы, например, выводим окошко
        // (или как-то иначе сообщаем) и завершаем работу.
        val initCode = CSPConfig.init(context)
        val initOk = initCode == CSPConfig.CSP_INIT_OK

        // Если инициализация не удалась, то сообщим об ошибке.


        cryptoProErrorMapper[initCode]

        return Pair(initOk, cryptoProErrorMapper[initCode] ?: "UnknownError");
    }


    /**
     * Добавление нативного провайдера Java CSP,
     * SSL-провайдера и Revocation-провайдера в
     * список Security. Инициализируется JCPxml,
     * CAdES.
     *
     * Происходит один раз при инициализации.
     * Возможно только после инициализации в CSPConfig!
     *
     */
    internal fun initJavaProviders() {

        // %%% Инициализация остальных провайдеров %%%

        //
        // Загрузка Java CSP (хеш, подпись, шифрование,
        // генерация контейнеров).
        //
        if (Security.getProvider(JCSP.PROVIDER_NAME) == null) {
            Security.addProvider(JCSP())
        } // if

        //
        // Загрузка Java TLS (TLS).
        //
        // Необходимо переопределить свойства, чтобы в случае HttpsURLConnection
        // + system-свойства использовались менеджеры из cpSSL, а не Harmony.
        //
        // Внимание!
        // Чтобы не мешать не-ГОСТовой реализации, ряд свойств внизу *.ssl и
        // javax.net.* НЕ следует переопределять! Но при этом не исключены проблемы
        // в работе с ГОСТом там, где TLS-реализация клиента обращается к дефолтным
        // алгоритмам реализаций этих factory (особенно: apache http client или
        // HttpsURLConnection без явной передачи предварительно созданного SSLContext
        // и его SSLSocketFactory).
        // То есть если используется HttpsURLConnection + свойства хранилищ javax.net.*,
        // заданные через System.setProperty(), то переопределения свойств *.ssl ниже
        // нужны.
        // Рекомендуемый вариант: использовать ok http и другие подобные реализации
        // с явным созданием SSLContext и передачей его SSLSocketFactory в клиент
        // Ok http.
        //
        // Здесь эти свойства включены, т.к. пример УЦ 1.5 использует алгоритмы
        // по умолчанию.
        //
        // Если инициализировать провайдер в CSPConfig с помощью initEx(), то
        // свойства будут включены там, поэтому выше используется упрощенная
        // версия инициализации.
        //
        Security.setProperty(
            "ssl.KeyManagerFactory.algorithm",
            ru.CryptoPro.ssl.Provider.KEYMANGER_ALG
        )
        Security.setProperty(
            "ssl.TrustManagerFactory.algorithm",
            ru.CryptoPro.ssl.Provider.KEYMANGER_ALG
        )
        Security.setProperty("ssl.SocketFactory.provider", "ru.CryptoPro.ssl.SSLSocketFactoryImpl")
        Security.setProperty(
            "ssl.ServerSocketFactory.provider",
            "ru.CryptoPro.ssl.SSLServerSocketFactoryImpl"
        )

        // Добавление TLS провайдера.
        if (Security.getProvider(ru.CryptoPro.ssl.Provider.PROVIDER_NAME) == null) {
            Security.addProvider(ru.CryptoPro.ssl.Provider())
        } // if

        //
        // Провайдер хеширования, подписи, шифрования
        // по умолчанию.
        //
        cpSSLConfig.setDefaultSSLProvider(JCSP.PROVIDER_NAME)

        //
        // Загрузка Revocation Provider (CRL, OCSP).
        //
        if (Security.getProvider(RevCheck.PROVIDER_NAME) == null) {
            Security.addProvider(RevCheck())
        } // if

        //
        // Отключаем проверку цепочки штампа времени (CAdES-T),
        // чтобы не требовать него CRL.
        //
        System.setProperty("ru.CryptoPro.CAdES.validate_tsp", "false")

        //
        // Таймауты для CRL на всякий случай.
        //
        System.setProperty("com.sun.security.crl.timeout", "5")
        System.setProperty("ru.CryptoPro.crl.read_timeout", "5")

        // Задание провайдера по умолчанию для CAdES.
        AdESConfig.setDefaultProvider(JCSP.PROVIDER_NAME)

        // Инициализация XML DSig (хеш, подпись).
        XmlInit.init()

        // Добавление реализации поиска узла по ID.
        ResourceResolver.registerAtStart(XmlInit.JCP_XML_DOCUMENT_ID_RESOLVER)

        // Добавление XMLDSigRI провайдера, так как его
        // использует XAdES.
        val xmlDSigRi: Provider = XMLDSigRI()
        Security.addProvider(xmlDSigRi)
        val provider = Security.getProvider("XMLDSig")
        if (provider != null) {
            provider["XMLSignatureFactory.DOM"] =
                "ru.CryptoPro.JCPxml.dsig.internal.dom.DOMXMLSignatureFactory"
            provider["KeyInfoFactory.DOM"] =
                "ru.CryptoPro.JCPxml.dsig.internal.dom.DOMKeyInfoFactory"
        } // if

        // Включаем возможность онлайновой проверки статуса
        // сертификата.
        //
        // Для TLS проверку цепочки сертификатов другой стороны
        // можно отключить, если создать параметр
        // Enable_revocation_default=false в файле android_pref_store
        // (shared preferences), см.
        // {@link ru.CryptoPro.JCP.tools.pref_store#AndroidPrefStore}.
        //
        // В случае создания подписей формата BES или T можно отключить
        // проверку цепочки сертификатов подписанта (и службы) с помощью
        // параметра:
        // cAdESSignature.setOptions((new Options()).disableCertificateValidation()); // CAdES
        // или
        // xAdESSignature.setOptions((new Options()).disableCertificateValidation()); // XAdES
        // перед добавлением подписанта.
        // По умолчанию проверка цепочки сертификатов подписанта всегда
        // включена.
        System.setProperty("com.sun.security.enableCRLDP", "true")
        System.setProperty("com.ibm.security.enableCRLDP", "true")

        // Свойство, препятствующее созданию дефолтного (static) контекста
        // при использовании HttpsURLConnection без system-свойств и
        // динамически создаваемого SSL контекста в TLS-примерах.
        // См. {@link ru.CryptoPro.ACSPClientApp.client.example.base.TLSData}
        // См. {@link ru.CryptoPro.ssl.JavaTLSKeyStoreParameter} в cpSSL-javadoc.jar
        System.setProperty("disable_default_context", "true")

        // JCP-1422: в режиме Knox в Samsung используется
        // фиксированный провайдер для проверки подписи,
        // как в Oracle в jarsigner при проверке подписи,
        // потому в случае ГОСТ сертификата укажем наш
        // провайдер.
        //
        // Задаем параметр установки провайдера Java CSP
        // в качестве провайдера по умолчанию в ряде
        // случаев: при проверке подписи сертификата.
        System.setProperty("ngate_set_jcsp_if_gost", "true")

        // JCP-1848: отключаем проверку ru.CryptoPro.key_agreement_validation -
        // требование наличия бита key_agreement в key usage сертификата получателя.
        // Проверка отключена, потому что тестовый УЦ MSCA выпускает тестовые
        // сертификаты без key_agreement.
        System.setProperty("ru.CryptoPro.key_agreement_validation", "false")

        //
        // Настройки TLS для генерации контейнера и выпуска сертификата
        // в УЦ 2.0, т.к. обращение к УЦ 2.0 будет выполняться по протоколу
        // HTTPS и потребуется авторизация по сертификату. Указываем тип
        // хранилища с доверенным корневым сертификатом, путь к нему и пароль.
        //
        // Внимание!
        // Чтобы не мешать не-ГОСТовой реализации, ряд свойств внизу *.ssl и
        // javax.net.* НЕ следует переопределять! Но при этом не исключены проблемы
        // в работе с ГОСТом там, где TLS-реализация клиента обращается к дефолтным
        // алгоритмам реализаций этих factory (особенно: apache http client или
        // HttpsURLConnection без явной передачи предварительно созданного SSLContext
        // и его SSLSocketFactory).
        // То есть если используется HttpsURLConnection + свойства хранилищ javax.net.*,
        // заданные через System.setProperty(), то переопределения свойств *.ssl ниже
        // нужны.
        // Рекомендуемый вариант: использовать ok http и другие подобные реализации
        // с явным созданием SSLContext и передачей его SSLSocketFactory в клиент
        // Ok http.
        //
        // Здесь эти свойства включены, т.к. пример УЦ 1.5 использует алгоритмы
        // по умолчанию. Примеров УЦ 2.0 пока нет.
        //
        val trustStorePath = context.applicationInfo.dataDir + File.separator +
                BKSTrustStore.STORAGE_DIRECTORY + File.separator + BKSTrustStore.STORAGE_FILE_TRUST
        val trustStorePassword = String(BKSTrustStore.STORAGE_PASSWORD)
        Timber.d("Default trust store: $trustStorePath")
        System.setProperty("javax.net.ssl.trustStoreType", BKSTrustStore.STORAGE_TYPE)
        System.setProperty("javax.net.ssl.trustStore", trustStorePath)
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword)


    }


    internal fun initCspTool() {
        cspTool = CSPTool(context)
    }

    /**
     * Копирование тестовых контейнеров для подписи,
     * шифрования, обмена по TLS из архива в папку
     * keys приложения.
     *
     */
    internal fun installContainersfromRes(resId: Int) {
        cspTool?.let {
            it.appInfrastructure.copyContainerFromArchive(resId)//R.raw.srv2021n)}}
        }
    }

    internal fun installContainersfromFile(
        documentFile: DocumentFile,
        contentResolver: ContentResolver,
        dstPath: StringBuilder
    ): Boolean {
        var r = false
        Timber.d("cspTool = ${cspTool} documentFile name = ${documentFile.name} isDirectory = ${documentFile.isDirectory}")

        cspTool?.let {
            r = it.appInfrastructure.copyContainerFromDirectory(
                documentFile,
                contentResolver,
                dstPath
            )
        }
        return r
    }

    internal fun userName2Dir(context: Context): String {
        val appInfo: ApplicationInfo = context.getApplicationInfo()
        //Timber.d("appInfo = ${appInfo.uid}")
        return appInfo.uid.toString() + "." + appInfo.uid
    }

    internal fun isCheckKeysDirectory(context: Context): Boolean {
        val keysDirRootPath = cspTool?.appInfrastructure?.keysDirectory
        val keysDir = File(keysDirRootPath, userName2Dir(context))
        val keysDirPath = keysDir.absolutePath

        Timber.d("keysDirPath = ${keysDirPath} isDir = ${keysDir.isDirectory} ${keysDir.listFiles().size} exists = ${keysDir.exists()}")
        return keysDir.listFiles().size > 0
    }

    internal fun hasUserContainers(): Boolean {
        val cproCspDirectoryPath = cspTool?.appInfrastructure?.getCproCspDirectory()
        val cproCspDirectory = File(cproCspDirectoryPath)
        Timber.d(
            "cproCspDirectory = ${cproCspDirectory}" +
                    " exists = ${cproCspDirectory.exists()}" +
                    " isDirectory = ${cproCspDirectory.isDirectory()}" +
                    " listFiles isNotEmpty = ${cproCspDirectory.listFiles().size}"
        )
        return cproCspDirectory.exists() && cproCspDirectory.isDirectory() && cproCspDirectory.listFiles()
            .isNotEmpty()
    }

    internal fun checkContainer(): Boolean {
        // Проверка существования алиаса.
        try {
            val keyStore: KeyStore = KeyStore.getInstance(
                KeyStoreType.currentType(), JCSP.PROVIDER_NAME
            )
            Timber.d("keyStore = ${keyStore} ${keyStore.size()}")

            keyStore.load(null, null)
            val aliases = keyStore.aliases()
            Timber.d("aliases hasMoreElements = ${aliases.hasMoreElements()}")

            while (aliases.hasMoreElements()) {

                val alias = aliases.nextElement()
                Timber.d("alias = ${alias}")
            } //
            return keyStore.size() > 0
        } catch (err: Exception) {
            Timber.d("err = ${err}")
            return false

        }
    }

    /************************** Служебные функции  */
    /**
     * Вывод списка поддерживаемых алгоритмов.
     *
     */
    internal fun logJCspServices() {
        ProviderServiceInfo.logServiceInfo(KeyStoreUtil.DEFAULT_PROVIDER)
    }

    /**
     * Информация о тестовых контейнерах.
     *
     */
    internal fun logTestContainers() {

        // Список алиасов контейнеров.
        val aliases = arrayOf<String>(
            EncryptDecryptData.CLIENT_CONTAINER_NAME,  // ГОСТ 34.10-2001
            EncryptDecryptData.SERVER_CONTAINER_NAME,  // ГОСТ 34.10-2001
            EncryptDecryptData.CLIENT_CONTAINER_2012_256_NAME,  // ГОСТ 34.10-2012 (256)
            EncryptDecryptData.SERVER_CONTAINER_2012_256_NAME,  // ГОСТ 34.10-2012 (256)
            EncryptDecryptData.CLIENT_CONTAINER_2012_512_NAME,  // ГОСТ 34.10-2012 (512)
            EncryptDecryptData.SERVER_CONTAINER_2012_512_NAME // ГОСТ 34.10-2012 (512)
        )

        // Список паролей контейнеров.
        val passwords = arrayOf<CharArray>(
            EncryptDecryptData.CLIENT_KEY_PASSWORD,
            EncryptDecryptData.SERVER_KEY_PASSWORD,
            EncryptDecryptData.CLIENT_KEY_2012_256_PASSWORD,
            EncryptDecryptData.CLIENT_KEY_2012_256_PASSWORD,
            EncryptDecryptData.CLIENT_KEY_2012_512_PASSWORD,
            EncryptDecryptData.CLIENT_KEY_2012_512_PASSWORD
        )
        val format = context.getString(R.string.ContainerAboutTestContainer)
        Timber.d("$$$ About test containers $$$")
        for (i in aliases.indices) {
            val aboutTestContainer = String.format(
                format,
                aliases[i], passwords[i].toString()
            )
            Timber.d("** $i) $aboutTestContainer")
        } // for
    }


    /*
    -----------------------------------------------------------------------------------------------
    Работа с сертификатами
    -----------------------------------------------------------------------------------------------
    */
    fun getLocalCertificates(): Map<String, X509Certificate> {
        val certificates: MutableMap<String, X509Certificate> = HashMap()
        try {
            val keyStore = getKeyStore()

            //Timber.d("keyStores type = ${keyStore.type} provider = ${keyStore.provider} size =  ${keyStore.size()} ")
            val aliases = keyStore.aliases()
            //Timber.d("aliases = $aliases")


            while (aliases.hasMoreElements()) {
                val alias = aliases.nextElement()
                val certificate = keyStore.getCertificate(alias) as X509Certificate
                //Timber.d("certificate = ${certificate}")

                val clientName = certificate.getClientName()
                Timber.d("clientName = $clientName")

                val companyName = certificate.getCompanyName()

                //Timber.d("companyName = $companyName")
                if (clientName != null) {
                    certificates[alias] = certificate
                }


            }
        } catch (e: java.lang.Exception) {
            Timber.d("e = $e")

            //e.printStackTrace()
            //      Crashlytics.logException(e);
        }
        // Timber.d("certificates = ${certificates.size}")

        return certificates
    }

    fun deleteContainer(alias4del: String) {
        // Тип контейнера по умолчанию.
        val keyStoreType = KeyStoreType.currentType()
        Timber.d("Default container type: $keyStoreType")
        Timber.d("Delete one container: $alias4del")

        val keyStore = KeyStore.getInstance(
            keyStoreType,
            JCSP.PROVIDER_NAME
        )

        keyStore.load(null, null)
        var removedCount = 0

        val aliases = keyStore.aliases()
        while (aliases.hasMoreElements()) {
            val alias = aliases.nextElement()
            Timber.d("Container: $alias")

            // Если задан контейнер, то удаляем только его.
            if (alias4del != null) {
                if (alias == alias4del) {
                    Timber.d("Deleting container: $alias")
                    keyStore.deleteEntry(alias)
                } // if
                else {
                    Timber.d("Continue...")
                    continue
                } // else
            } // if
            else {
                Timber.d("Deleting container (all): $alias")
                keyStore.deleteEntry(alias)
            } // else
            removedCount++
            Timber.d("Container '$alias': removed.")
        } // while


        Timber.d("Removed containers' count: $removedCount")
    }


    /*
  -----------------------------------------------------------------------------------------------
     Работа с хранилищем ключей
  -----------------------------------------------------------------------------------------------
  */

    val STORE_TYPE = JCSP.HD_STORE_NAME
    val PROVIDER = JCSP.PROVIDER_NAME

    @Throws(java.lang.Exception::class)
    internal fun getKeyStore(): KeyStore {
/*
        val getKeyStoreTypeList = getKeyStoreTypeList(false)
        Timber.d("getKeyStoreTypeList = $getKeyStoreTypeList ${getKeyStoreTypeList.size}")

        val getKeyStoreTypeListRutokenIsActive = getKeyStoreTypeList(true)
        Timber.d("getKeyStoreTypeListRutokenIsActive = $getKeyStoreTypeListRutokenIsActive  ${getKeyStoreTypeListRutokenIsActive.size}")
        Timber.d("currentType = " + currentType()+" PROVIDER_NAME = ${ProviderType.currentProviderType()}" )
        val keyStore1= KeyStore.getInstance(currentType(), JCSP.PROVIDER_NAME)
        keyStore1.load(null)
        Timber.d("keyStore1 = ${keyStore1.size()} aliases = ${keyStore1.aliases()}")



        Timber.d("STORE_TYPE = " + STORE_TYPE + " PROVIDER_NAME = " + JCSP.PROVIDER_NAME)

 */
        val keyStore = KeyStore.getInstance(STORE_TYPE, JCSP.PROVIDER_NAME)
        keyStore.load(null)
        //Timber.d("keyStore = ${keyStore.size()} aliases = ${keyStore.aliases()}")
        return keyStore
    }


    fun checkIntegrity(isPrintInfo: Boolean): Boolean {
        val providerInfo = CSPConfig.INSTANCE.cspProviderInfo
        val integrity = providerInfo.integrity
        //Timber.d("integrity = ${integrity}")
        if (isPrintInfo) printInfoIntegrity(integrity)
        // Проверка целостности. Выполняется вычисление хешей
        // нативных библиотек и проверка на соответствие их
        // эталонам из ресурсов.
        //
        // Дополнительно можно задать и свои списки хешей.
        // Для этого нужно в папку raw ресурсов поместить файл
        // с расширением properties и именем, зависящим от
        // архитектуры процессора, имена перечислены в
        // {@link ru.cprocsp.ACSP.tools.common#CSPIntegrityConstants},
        // например, для arm64-v8a (arch64) файл должен носить имя
        // extra_digests64.properties.
        // Впереди задается приставка extra_
        // Сам хеш вычисляется с помощью утилиты cpverify:
        //
        // /opt/cprocsp/bin/amd64/cpverify -mk ACSPInClientApp/libs/arm64-v8a/libmy.so -alg GR3411_2012_256
        //
        // После чего в формате "ключ"="значение" помещается
        // в файл extra_digests64.properties (или иной, в
        // зависимости от архитектуры процессора):
        //
        // libmy.so=35F46516A1AB77F4732C0BB2F4D29C17C11AD2BE09447E138BD2D7FEAFA0ED62
        //
        // При инициализации объекта класса контроля целостности
        // помимо файлов хешей, имеющихся в ресурсах провайдера,
        // будет загружен файл extra_digests64.properties (или
        // иной, в зависимости от архитектуры процессора).
        //
        // Результат проверки и ее дата будут сохранены в папку
        // приложения.
        // Результат представлен в виде кода
        // {@link ru.cprocsp.ACSP.tools.common#CSPIntegrityConstants}:
        // CHECK_INTEGRITY_UNKNOWN (-1)
        // CHECK_INTEGRITY_SUCCESS  (0)
        // CHECK_INTEGRITY_INVALID  (1)


        // Проверка целостности. Выполняется вычисление хешей
        // нативных библиотек и проверка на соответствие их
        // эталонам из ресурсов.
        //
        // Дополнительно можно задать и свои списки хешей.
        // Для этого нужно в папку raw ресурсов поместить файл
        // с расширением properties и именем, зависящим от
        // архитектуры процессора, имена перечислены в
        // {@link ru.cprocsp.ACSP.tools.common#CSPIntegrityConstants},
        // например, для arm64-v8a (arch64) файл должен носить имя
        // extra_digests64.properties.
        // Впереди задается приставка extra_
        // Сам хеш вычисляется с помощью утилиты cpverify:
        //
        // /opt/cprocsp/bin/amd64/cpverify -mk ACSPInClientApp/libs/arm64-v8a/libmy.so -alg GR3411_2012_256
        //
        // После чего в формате "ключ"="значение" помещается
        // в файл extra_digests64.properties (или иной, в
        // зависимости от архитектуры процессора):
        //
        // libmy.so=35F46516A1AB77F4732C0BB2F4D29C17C11AD2BE09447E138BD2D7FEAFA0ED62
        //
        // При инициализации объекта класса контроля целостности
        // помимо файлов хешей, имеющихся в ресурсах провайдера,
        // будет загружен файл extra_digests64.properties (или
        // иной, в зависимости от архитектуры процессора).
        //
        // Результат проверки и ее дата будут сохранены в папку
        // приложения.
        // Результат представлен в виде кода
        // {@link ru.cprocsp.ACSP.tools.common#CSPIntegrityConstants}:
        // CHECK_INTEGRITY_UNKNOWN (-1)
        // CHECK_INTEGRITY_SUCCESS  (0)
        // CHECK_INTEGRITY_INVALID  (1)
        var result = integrity.check(true)
        //Timber.d("integrity result = ${result}")

        return result == CSPIntegrityConstants.CHECK_INTEGRITY_SUCCESS;
    }

    fun printInfoIntegrity(integrity: IntegrityInterface) {

        //Timber.d("Last check date: " + integrity.lastDate)
        val status = integrity.lastStatus

        when (status) {
            CSPIntegrityConstants.CHECK_INTEGRITY_SUCCESS -> {
                Timber.d("Status: completed")
            }
            CSPIntegrityConstants.CHECK_INTEGRITY_INVALID -> {
                Timber.d("Status: failed")
            }
            else -> {
                Timber.d("Status: unknown")
            }
        }

        // Удобочитаемый список проверяемых библиотек
        // и их хешей.


        // Удобочитаемый список проверяемых библиотек
        // и их хешей.
        val items = integrity
            .getItems(null, null, "")

        for (item in items) {
            Timber.d("\t" + item)
        } // for


        // Дополнительно, можно проверить целостность,
        // используя только файл. Необходимо учитывать
        // архитектуру процессора.
        //
        // В данном примере используется файл с хешом
        // библиотеки, вычисленным для arm64-v8a (arch64),
        // который не пройдет проверку для иных архитектур.


        // Дополнительно, можно проверить целостность,
        // используя только файл. Необходимо учитывать
        // архитектуру процессора.
        //
        // В данном примере используется файл с хешом
        // библиотеки, вычисленным для arm64-v8a (arch64),
        // который не пройдет проверку для иных архитектур.
        Timber.d("Checking (v2) (digests for arm64-v8a)...")

        val details: Set<Map.Entry<String, String>> = HashSet()
        val digestInputStream =
            context.resources.openRawResource(R.raw.extra_digests64) // отдельный файл из ресурсов


        val result = ACSPIntegrity.check(context, digestInputStream, details)
        when (result) {
            CSPIntegrityConstants.CHECK_INTEGRITY_SUCCESS -> {
                Timber.d("Status: completed")
            }
            CSPIntegrityConstants.CHECK_INTEGRITY_INVALID -> {
                Timber.d("Status: failed")
            }
            else -> {
                Timber.d("Status: unknown")
            }
        }
        val iterator = details.iterator()
        while (iterator.hasNext()) {
            val (key, value) = iterator.next()
            Timber.d("\tkey '$key' has value '$value'")
        }
    }

    fun checkLicense(isPrintInfo: Boolean): Boolean {
        // Проверка текущей лицензии.
        val providerInfo = CSPConfig.INSTANCE.cspProviderInfo

        val license = providerInfo.license
        val number = license.serialNumber

        //Timber.d("Checking current license "+ number + "...")

        var licenseStatus = license.checkAndSave()
        //Timber.d("Checking completed.")
        if (licenseStatus == CSPLicenseConstants.LICENSE_STATUS_OK) {
            if (isPrintInfo) printLicenseInfo(license, licenseStatus)
            val licenseType = license.licenseType
            if (licenseType != CSPLicenseConstants.LICENSE_TYPE_EXPIRED) {
                //Timber.d("Status: OK")
                return true;
            }
        } // if
        return false
    }


    fun checkNewLicense(isPrintInfo: Boolean): Boolean {
        val providerInfo = CSPConfig.INSTANCE.cspProviderInfo
        val license = providerInfo.license

        // Проверка новой лицензии.
        Timber.d("Installing license: " + getNumber() + "...")

        val licenseStatus = license.checkAndSave(getNumber(), false)
        if (licenseStatus == CSPLicenseConstants.LICENSE_STATUS_OK) {
            if (isPrintInfo) printLicenseInfo(license, licenseStatus)
            val licenseType = license.licenseType
            if (licenseType != CSPLicenseConstants.LICENSE_TYPE_EXPIRED) {
                Timber.d("Status: OK")
                return true;
            }
        }
        return false
    }

    /**
     * Вывод информации о лицензии.
     *
     * @param license       Лицензия.
     * @param licenseStatus Статус лицензии.
     */
    private fun printLicenseInfo(license: LicenseInterface, licenseStatus: Int) {
        Timber.d("Result: $licenseStatus")
        val licenseType = license.licenseType
        if (licenseStatus == CSPLicenseConstants.LICENSE_STATUS_OK) {
            Timber.d("Status: OK")
        } // if
        else {
            if (licenseType == CSPLicenseConstants.LICENSE_TYPE_EXPIRED) {
                Timber.d("Status: expired")
            } // if
            else {
                Timber.d("Status: invalid")
            } // else
        } // else
        if (licenseStatus != CSPLicenseConstants.LICENSE_STATUS_INVALID) {
            if (licenseType == CSPLicenseConstants.LICENSE_TYPE_EXPIRED) {
                Timber.d("expiredThroughDays: expired")
            } // if
            else if (licenseType == CSPLicenseConstants.LICENSE_TYPE_PERMANENT) {
                Timber.d("expiredThroughDays: permanent")
            } // else if
            else {
                Timber.d(
                    "expiredThroughDays: " +
                            license.expiredThroughDays
                )
            } // else
        } // if
        Timber.d(
            "Installation date: " +
                    license.licenseInstallDateAsString
        )
        if (licenseStatus != CSPLicenseConstants.LICENSE_STATUS_OK) {
            // throw new Exception("Could not check or/and install license."); // лицензия в примере может быть истекшей
            Timber.d("Could not check or/and install license. Continue.")
        } // if
    }

    /**
     * Получение номера лицензии.
     *
     * @return номер лицензии.
     */
    /**
     * Новая лицензия CSP.
     */
    private val LICENSE_NEW = "" // Лицензия CSP 5.0

    fun getNumber(): String? {
        return LICENSE_NEW
    }


    fun checkConfig() {
        val providerInfo = CSPConfig.INSTANCE.cspProviderInfo

        val config = providerInfo.config
        Timber.d("Changing configuration...")

        // Уровень логирования.


        // Уровень логирования.
        val currentLevel = config.currentLoggingLevel
        Timber.d("Current logging level: $currentLevel")

        // Проверка, отключены ли предупреждения об
        // использовании ГОСТ Р 34.10-2001.


        // Проверка, отключены ли предупреждения об
        // использовании ГОСТ Р 34.10-2001.
        val warning2001Disabled = config.isWarning2001Disabled
        Timber.d("Current warning 2001 disabled: $warning2001Disabled")

        // Считыватель.


        // Считыватель.
        val currentReaderName = config.currentReaderName
        Timber.d("Current reader name: $currentReaderName")

        // Список считывателей.


        // Список считывателей.
        val readerList = config.readerList
        Timber.d("-- Reader list from config --")

        for (reader in readerList) {
            Timber.d("reader: $reader")
        } // for


        // Список типов хранилищ.


        // Список типов хранилищ.
        val configKeyStoreTypes = config.keyStoreTypes
        val keyStoreTypes: Set<String> = configKeyStoreTypes.keys

        Timber.d("-- Key store types from config --")

        for (keyStoreType in keyStoreTypes) {
            Timber.d("type: $keyStoreType")
        } // for


        val cachedConfigKeyStoreTypes = config.cachedKeyStoreTypes
        val cachedKeyStoreTypes: Set<String> = cachedConfigKeyStoreTypes.keys

        Timber.d("-- Cached key store types --")

        for (keyStoreType in cachedKeyStoreTypes) {
            Timber.d("type: $keyStoreType")
        } // for


        //--------------------------------------------------------------------------------------------------------------

        // Смена уровня логирования.


        //--------------------------------------------------------------------------------------------------------------

        // Смена уровня логирования.
        val newLoggingLevel = ConfigInterface.LOGGING_LEVEL_NAMES[1]
        Timber.d("Set logging level $newLoggingLevel...")

        if (config.setLoggingLevel(newLoggingLevel)) {
            Timber.d("$newLoggingLevel set.")
        } // if
        else {
            Timber.d("$newLoggingLevel NOT set!")
            throw Exception("Couldn't set logging level")
        } // else


        // Восстановление предупреждений об
        // использовании ГОСТ Р 34.10-2001.


        // Восстановление предупреждений об
        // использовании ГОСТ Р 34.10-2001.
        if (config.disableWarning2001(false)) {
            Timber.d("Warning 2001 enabled.")
        } // if
        else {
            Timber.d("Warning 2001 still disabled!")
            throw Exception("Couldn't restore warning 2001")
        } // else


        // Смена считывателя.


        // Смена считывателя.
        Timber.d("Looking for reader to change...")
        var newReaderName: String? = null

        for (reader in readerList) {
            if (reader != currentReaderName) {
                newReaderName = reader
                break
            } // if
        } // for


        if (newReaderName != null) {
            Timber.d("Set reader $newReaderName...")
            if (config.setReaderName(newReaderName)) {
                Timber.d("$newReaderName set.")
            } // if
            else {
                Timber.d("$newReaderName NOT set!")
                throw Exception("Couldn't set reader")
            } // else
        } // if


        // Поиск типа для отключения.


        // Поиск типа для отключения.
        Timber.d("Looking for key store type to disable...")
        var disabledKeyStoreType: String? = null

        for (keyStoreType in keyStoreTypes) {
            if (!config.isKeyStoreAlwaysEnabled(keyStoreType)) {
                disabledKeyStoreType = keyStoreType
                break
            }
        } // for


        // Отключение типа.


        // Отключение типа.
        if (disabledKeyStoreType != null) {
            Timber.d("Disable key store type $disabledKeyStoreType...")
            if (config.disableKeyStoreType(disabledKeyStoreType)) {
                Timber.d("$disabledKeyStoreType disabled.")
            } // if
            else {
                Timber.d("$disabledKeyStoreType NOT disabled!")
                throw Exception("Couldn't disable key store type")
            } // else
        } // if
        else {
            Timber.d("No key store type available to disable.")
        } // else


        //--------------------------------------------------------------------------------------------------------------

        // Уровень логирования.


        //--------------------------------------------------------------------------------------------------------------

        // Уровень логирования.
        var newCurrentLevel = config.currentLoggingLevel
        Timber.d("New logging level: $newCurrentLevel")

        // Проверка, отключены ли предупреждения.


        // Проверка, отключены ли предупреждения.
        var newWarning2001Disabled = config.isWarning2001Disabled
        Timber.d("New warning2001 disabled: $newWarning2001Disabled")

        // Считыватель.


        // Считыватель.
        var newCurrentReaderName = config.currentReaderName
        Timber.d("New reader name: $newCurrentReaderName")

        // Список типов хранилищ.


        // Список типов хранилищ.
        val newConfigKeyStoreTypes = config.keyStoreTypes
        val newKeyStoreTypes: Set<String> = newConfigKeyStoreTypes.keys

        Timber.d("-- New key store types from config --")

        for (keyStoreType in newKeyStoreTypes) {
            Timber.d("type: $keyStoreType")
        } // for


        //--------------------------------------------------------------------------------------------------------------


        //--------------------------------------------------------------------------------------------------------------
        Timber.d("Restoring configuration...")

        // Восстановление уровня логирования.


        // Восстановление уровня логирования.
        Timber.d("Set logging level $currentLevel...")

        if (config.setLoggingLevel(currentLevel)) {
            Timber.d("$currentLevel set.")
        } // if
        else {
            Timber.d("$currentLevel NOT set!")
            throw Exception("Couldn't restore logging level")
        } // else


        val restoredLoggingLevel = config.currentLoggingLevel
        Timber.d("Restored logging level: $restoredLoggingLevel")

        // Отключение предупреждений об
        // использовании ГОСТ Р 34.10-2001.


        // Отключение предупреждений об
        // использовании ГОСТ Р 34.10-2001.
        if (config.disableWarning2001(true)) {
            Timber.d("Warning 2001 disabled.")
        } // if
        else {
            Timber.d("Warning 2001 still enabled!")
            throw Exception("Couldn't disable warning 2001")
        } // else


        val restoredWarning2001Disabled = config.isWarning2001Disabled
        Timber.d("Restored warning2001 (is disabled): $restoredWarning2001Disabled")

        // Восстановление считывателя.


        // Восстановление считывателя.
        if (newReaderName != null) {
            Timber.d("Set reader $currentReaderName...")
            if (config.setReaderName(currentReaderName)) {
                Timber.d("$currentReaderName set.")
            } // if
            else {
                Timber.d("$currentReaderName NOT set!")
                throw Exception("Couldn't restore reader")
            } // else
            val restoredCurrentReaderName = config.currentReaderName
            Timber.d("Restored reader name: $restoredCurrentReaderName")
        } // if


        // Включение типа хранилища.


        // Включение типа хранилища.
        if (disabledKeyStoreType != null) {
            Timber.d("Enable key store type $disabledKeyStoreType...")
            if (config.enableKeyStoreType(disabledKeyStoreType)) {
                Timber.d("$disabledKeyStoreType enabled.")
            } // if
            else {
                Timber.d("$disabledKeyStoreType NOT enabled!")
                throw Exception("Couldn't enable key store type")
            } // else
        } // if


        //--------------------------------------------------------------------------------------------------------------

        // Уровень логирования.


        //--------------------------------------------------------------------------------------------------------------

        // Уровень логирования.
        newCurrentLevel = config.currentLoggingLevel
        Timber.d("New logging level: $newCurrentLevel")

        // Проверка, отключены ли предупреждения.


        // Проверка, отключены ли предупреждения.
        newWarning2001Disabled = config.isWarning2001Disabled
        Timber.d("New warning2001 disabled: $newWarning2001Disabled")

        // Считыватель.


        // Считыватель.
        newCurrentReaderName = config.currentReaderName
        Timber.d("New reader name: $newCurrentReaderName")

        // Список типов хранилищ.


        // Список типов хранилищ.
        val restoredConfigKeyStoreTypes = config.keyStoreTypes
        val restoredKeyStoreTypes: Set<String> = restoredConfigKeyStoreTypes.keys

        Timber.d("-- Restored key store types from config --")

        for (keyStoreType in restoredKeyStoreTypes) {
            Timber.d("type: $keyStoreType")
        } // for


    }

    fun decodeBase64Certificate(encodedCertificateString: String?): X509Certificate? {
        var certificate: X509Certificate? = null
        try {
            if (encodedCertificateString != null) {
                val encodedCert = Base64.decode(encodedCertificateString, Base64.NO_WRAP)
                val inputStream = ByteArrayInputStream(encodedCert)
                val certFactory = CertificateFactory.getInstance("X.509")
                certificate = certFactory.generateCertificate(inputStream) as X509Certificate
            }
        } catch (e: CertificateException) {
            e.printStackTrace()
            //      Crashlytics.logException(e);
        }
        return certificate
    }

    fun encodeCertificate(certificate: X509Certificate): String? {
        return try {
            SystemUtils.encodeBase64(certificate.encoded)
        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }


    fun signCertificate(alias: String, password: String, cert: X509Certificate): String? {
        return signPassord(alias, password, cert.encoded)
    }

    /*
  -----------------------------------------------------------------------------------------------
  Работа с данными
  -----------------------------------------------------------------------------------------------
  */
    fun signPassord(alias: String, password: String, data: ByteArray): String? {
        val r = privateKey(alias, password)
        if (r)
            return sign(currentPrivateKey!!, data)
        else
            throw Exception("privateKey is null")

    }


    /*
  -----------------------------------------------------------------------------------------------
  Работа с данными
  -----------------------------------------------------------------------------------------------
  */
    /**
     * Простая подпись
     * @param pk PrivateKey
     * @param data byte[]
     * @return string|null
     */
    fun sign(data: ByteArray): String? {
        val signature: Signature
        return try {
            signature = Signature.getInstance(JCP.CRYPTOPRO_SIGN_2012_256_NAME, JCSP.PROVIDER_NAME)
            signature.initSign(currentPrivateKey)
            signature.update(data)
            SystemUtils.encodeBase64(signature.sign())
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            //      Crashlytics.logException(e);
            null
        }
    }

    /**
     * Простая подпись
     * @param pk PrivateKey
     * @param data byte[]
     * @return string|null
     */
    fun sign(pk: PrivateKey, data: ByteArray): String {
        val signature: Signature
        signature = Signature.getInstance(JCP.CRYPTOPRO_SIGN_2012_256_NAME, JCSP.PROVIDER_NAME)
        signature.initSign(pk)
        signature.update(data)
        return SystemUtils.encodeBase64(signature.sign())
    }

    /*
  -----------------------------------------------------------------------------------------------
     Работа с хранилищем ключей
  -----------------------------------------------------------------------------------------------
  */
    fun privateKey(alias: String, password: String): Boolean {
        Timber.d(" password = $password")
        currentPrivateKey = loadPrivateKey(alias, password)
        //Timber.d("PK alias", alias)
        Timber.d("currentPrivateKey = ${currentPrivateKey.toString()}")
        return currentPrivateKey != null
    }

    fun loadPrivateKey(alias: String, password: String): PrivateKey? {
        val entry: JCPPrivateKeyEntry
        val params = JCPProtectionParameter(password.toCharArray(), true)
        Timber.d("alias = $alias password = $password")
        entry = try {
            val keyStore: KeyStore = getKeyStore()
            Timber.d("keyStore = $keyStore")
            keyStore.getEntry(alias, params) as JCPPrivateKeyEntry
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            //      Crashlytics.logException(e);
            return null
        }
        return entry.privateKey
    }


    fun decryptEphemeralSessionKey(
        encryptedSessionKey: EncryptedSessionKey
    ): DecryptedSessionKey? {
        val pk: PrivateKey? = currentPrivateKey

        Timber.d( "Decrypt Session ephemeral key")
        var result: DecryptedSessionKey? = null

            val bKey = Base64.decode(encryptedSessionKey.encryptedKey, Base64.NO_WRAP)
        Timber.d(
                "Transport key bytes: length = " + bKey.size + " ; bytes = " + Arrays.toString(bKey)
            )

            //Основной принцип - в 4х байтах сперва задается размер блоба, который идет следом
            var from = 0
            var to = 4
            val ephemeralBlobLengthBytes =
                SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, from, to))
            val ephemeralBlobLength = ByteBuffer.wrap(ephemeralBlobLengthBytes).int
        Timber.d(
                "Ephemeral public key length: length = " + ephemeralBlobLengthBytes.size + " ; bytes = " + Arrays.toString(
                    ephemeralBlobLengthBytes
                )
            )
            from += 4
            to += ephemeralBlobLength
            val ephemeralBlob = Arrays.copyOfRange(bKey, from, to)
        Timber.d(
                "Ephemeral public key: length = " + ephemeralBlob.size + " ; bytes = " + Arrays.toString(
                    ephemeralBlob
                )
            )
            from += ephemeralBlobLength
            to += 4
            val encryptedKeyBlobLengthBytes =
                SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, from, to))
            val encryptedKeyBlobLength = ByteBuffer.wrap(encryptedKeyBlobLengthBytes).int
        Timber.d(
                "Encrypted key length: length = " + encryptedKeyBlobLengthBytes.size + " ; bytes = " + Arrays.toString(
                    encryptedKeyBlobLengthBytes
                )
            )
            from += 4
            to += encryptedKeyBlobLength
            val encryptedKeyBlob = Arrays.copyOfRange(bKey, from, to)
        Timber.d(
                "Encrypted key: length = " + encryptedKeyBlob.size + " ; bytes = " + Arrays.toString(
                    encryptedKeyBlob
                )
            )
            from += encryptedKeyBlobLength
            to += 4
            val ivLenghtBytes = SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, from, to))
            val ivLenght = ByteBuffer.wrap(ivLenghtBytes).int
        Timber.d(
                "IV length: length = " + ivLenghtBytes.size + " ; bytes = " + Arrays.toString(
                    ivLenghtBytes
                )
            )
            from += 4
            to += ivLenght
            val ivBlob = Arrays.copyOfRange(bKey, from, to)
        Timber.d( "IV: length = " + ivBlob.size + " ; bytes = " + Arrays.toString(ivBlob))
            var imitaBlob: ByteArray? = null
            if (bKey.size > to) {
                from += ivLenght
                to += 4
                val imitaLengthBytes =
                    SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, from, to))
                val imitaLength = ByteBuffer.wrap(imitaLengthBytes).int
                Timber.d(
                    "Imita length: length = " + imitaLengthBytes.size + " ; bytes = " + Arrays.toString(
                        imitaLengthBytes
                    )
                )
                from += 4
                to += imitaLength
                imitaBlob = Arrays.copyOfRange(bKey, from, to)
                Timber.d(
                    "Imita length: length = " + imitaBlob.size + " ; bytes = " + Arrays.toString(
                        imitaBlob
                    )
                )
            }

            /*Asn1BerEncodeBuffer encoder = new Asn1BerEncodeBuffer();
      Gost28147_89_EncryptedKey encryptedKey = new Gost28147_89_EncryptedKey(
              new Gost28147_89_Key(encryptedKeyBlob),
              null, imitaBlob != null ? new Gost28147_89_MAC(imitaBlob) : null);
      encryptedKey.encode(encoder);
      byte[] wrapped = encoder.getMsgCopy();
      Log.i(LOG_TAG, "Wrapped key: length = " + wrapped.length + " ; bytes = " + Arrays.toString(wrapped));*/

            // Декодирование открытого ключа.
            val buf = Asn1BerDecodeBuffer(ephemeralBlob)
            val keyInfo = SubjectPublicKeyInfo()
            try {
                keyInfo.decode(buf)
            } catch (e: Asn1Exception) {
                val ex = IOException("Not GOST DH public key")
                ex.initCause(e)
                throw ex
            }
            buf.reset()
            val keySpec = X509EncodedKeySpec(ephemeralBlob)
            val kf = KeyFactory.getInstance(pk?.algorithm, JCSP.PROVIDER_NAME)
            val publicKey = kf.generatePublic(keySpec)
        Timber.d(
                "Public ephemeral key: length = " + publicKey.encoded + " ; bytes = " + Arrays.toString(
                    publicKey.encoded
                )
            )

            // Параметры шифрования.
            val ivParameterSpec = IvParameterSpec(ivBlob)
            val gostCipherSpec = getCipherSpec(ivBlob)

            // Выработка ключа согласования.
            val ka = KeyAgreement.getInstance(pk?.algorithm, JCSP.PROVIDER_NAME)
            ka.init(pk)
            ka.doPhase(publicKey, true)
            val dh: Key =
                ka.generateSecret(CIPHER_ALG) // dh получит параметры из recipientKey, т.е., по идее, transportParametersOid

            // Расшифрование сессионного ключа.
            val cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_KEY_ALG_PARAMS, JCSP.PROVIDER_NAME)
            cipher.init(Cipher.UNWRAP_MODE, dh, gostCipherSpec)
            val key_ = cipher.unwrap(encryptedKeyBlob, null, Cipher.SECRET_KEY) as SecretKey
            result = DecryptedSessionKey(key_, ivParameterSpec.iv)

        return result
    }

    fun decryptSessionKey(
        encryptedSessionKey: EncryptedSessionKey
    ): DecryptedSessionKey {
        val pk: PrivateKey? = currentPrivateKey


        val sizeLength = 4
        val bKey: ByteArray =
            Base64.decode(encryptedSessionKey.encryptedKey, Base64.NO_WRAP)
        Timber.d("bKey = $bKey")

        // Разбираем Simple Key BLOB
        // byte[] bSimpleBlob = {
        //    // Начало Header (16 байт)
        //    0x01, // bType = SIMPLEBLOB
        //    0x20, // bVersion = 0x20
        //    0x00, 0x00, // reserved
        //    0x23, 0x2e, 0x00, 0x00, // KeyAlg = ALG_SID_GR3410EL
        //    0x4d, 0x41, 0x47, 0x31, // Magic = GR3410_1_MAGIC
        //    0x1e, 0x66, 0x00, 0x00, // EncryptKeyAlgId = CALG_G28147
        //    // Конец Header
        //
        //    0x76, 0xee, 0xb4, 0x6b, 0x1b, 0x10, 0x36, 0xeb, // bUKM (8 байт)
        //    // pbEncryptedKey (32 байта)
        //    0x5e, 0x70, 0x73, 0x5f, 0x36, 0x98, 0xb4, 0x35,
        //    0x5b, 0x45, 0x03, 0x7f, 0xa7, 0xce, 0x00, 0x97,
        //    0x11, 0x5e, 0x45, 0xc6, 0x58, 0x59, 0x94, 0x72,
        //    0x66, 0x42, 0x06, 0x3f, 0x72, 0x3a, 0xb4, 0x9e,
        //    0x8c, 0x86, 0x08, 0x84, // pbMacKey (4 байта)
        //    0x30, 0x09, 0x06, 0x07, 0x2a, 0x85, 0x03, 0x02, 0x02, 0x1f, 0x01 // bEncryptionParamSet (13 байт)
        //  };
        val bBlobLength = SystemUtils.reverseByteArray(Arrays.copyOfRange(bKey, 0, sizeLength))
        val blobLength = ByteBuffer.wrap(bBlobLength).int
        val bSimpleBlob = Arrays.copyOfRange(bKey, sizeLength, sizeLength + blobLength)
        //val bBlobHeader = Arrays.copyOfRange(bSimpleBlob, 0, 16)
        val sv = Arrays.copyOfRange(bSimpleBlob, 16, 24)
        val bEncryptedKey = Arrays.copyOfRange(bSimpleBlob, 24, 24 + 32)
        val bMacKey = Arrays.copyOfRange(bSimpleBlob, 56, 56 + 4)
        //val bEncryptedParams = Arrays.copyOfRange(bSimpleBlob, 60, blobLength)

        // Получаем IV вектор
        val IVStart = sizeLength + blobLength
        val bIVLength = SystemUtils.reverseByteArray(
            Arrays.copyOfRange(
                bKey,
                IVStart,
                IVStart + sizeLength
            )
        )
        val IVLength = ByteBuffer.wrap(bIVLength).int
        var iv: ByteArray =
            Arrays.copyOfRange(bKey, IVStart + sizeLength, IVStart + sizeLength + IVLength)
        Timber.d("iv = $iv")

        // Получаем зашифрованный ключ
        val ek = Gost28147_89_EncryptedKey()

        ek.encryptedKey = Gost28147_89_Key(bEncryptedKey)
        Timber.d("Gost28147_89_Key ek = ${ek.encryptedKey}")

        ek.macKey = Gost28147_89_MAC(bMacKey)
        Timber.d("Gost28147_89_Key macKey = ${ek.macKey}")

        val ebuf = Asn1BerEncodeBuffer()
        ek.encode(ebuf)
        Timber.d("encode ebuf = $ebuf")
        val wrap = ebuf.msgCopy
        Timber.d("wrap = ${wrap.size}")

        // Генерируем ключ согласования
        val responderAgree: SecretKey =
            generateKeyAgreement(pk!!, encryptedSessionKey.certificate, sv)
                ?: throw Exception("Key agreement is null")
        Timber.d(" responderAgree = ${responderAgree.algorithm} ${responderAgree.isDestroyed}")

        // Расшифровываем ключ

        val cipher = Cipher.getInstance(CIPHER_ALG + CIPHER_KEY_ALG_PARAMS, JCSP.PROVIDER_NAME)


        cipher.init(Cipher.UNWRAP_MODE, responderAgree, IvParameterSpec(sv))
        Timber.d("ciphere = ${cipher.algorithm} iv = ${cipher.iv.size} provider = ${cipher.provider} ")


        var key_ = cipher.unwrap(wrap, null, Cipher.SECRET_KEY)
        Timber.d("key_ = $key_")
        val decryptedSessionKey = DecryptedSessionKey(key_ as SecretKey, iv)
        Timber.d("decryptedSessionKey = $decryptedSessionKey")

        return decryptedSessionKey
    }


    private fun generateKeyAgreement(
        pk: PrivateKey,
        partnerCert: X509Certificate,
        sv: ByteArray
    ): SecretKey {
        val ivspec = IvParameterSpec(sv)
        val keyAgree = KeyAgreement.getInstance(pk.algorithm, JCSP.PROVIDER_NAME)
        keyAgree.init(pk, ivspec, null)
        keyAgree.doPhase(partnerCert.publicKey, true)
        return keyAgree.generateSecret(CIPHER_ALG)
    }

    private fun generateKeyAgreement(
        pk: PrivateKey,
        partnerPublicKey: PublicKey,
        sv: ByteArray
    ): SecretKey {
        val ivspec = IvParameterSpec(sv)
        val keyAgree = KeyAgreement.getInstance(pk.algorithm, JCSP.PROVIDER_NAME)
        keyAgree.init(pk, ivspec, null)
        keyAgree.doPhase(partnerPublicKey, true)
        return keyAgree.generateSecret(CIPHER_ALG)
    }

    private fun getCipherSpec(iv: ByteArray): GostCipherSpec {
        val pp = CryptParamsSpec.getInstance(CryptParamsSpec.Rosstandart_TC26_Z)
        val ivParameterSpec = IvParameterSpec(iv)
        return GostCipherSpec(ivParameterSpec, pp)
    }


    ///////////////

    fun encryptMessage(
        cid: String,
        message: String,
        encryptedSessionKey: EncryptedSessionKey
    ): EncryptedMessage {
        val decryptedSessionKey: DecryptedSessionKey =
            decryptSessionKey(encryptedSessionKey)
        //Timber.d("DecryptedSessionKey = ${decryptedSessionKey}")
        return encryptMessage(cid, message, decryptedSessionKey)
    }

    fun encryptMessage(
        cid: String,
        message: String,
        decryptedSessionKey: DecryptedSessionKey
    ): EncryptedMessage {
        val bEncryptedMessage: ByteArray = cipherData(
            message.toByteArray(),
            decryptedSessionKey
        )
        //Timber.d("bEncryptedMessage = ${bEncryptedMessage}")
        val encryptedMessage: String = SystemUtils.encodeBase64(bEncryptedMessage)
        //Timber.d("encryptedMessage = ${encryptedMessage}")
        val signature: String = sign(currentPrivateKey!!, message.toByteArray())
        //Timber.d("signature = ${signature}")
        return EncryptedMessage(cid, encryptedMessage, signature)
    }


    fun encryptFile(
        filePath: String,
        cid: String,
        certificate: X509Certificate,
        encryptedSessionKey: EncryptedSessionKey
    ): EncryptedFile {
        val file = File(filePath)
        val data = Array.readFile(file)
        val certs = arrayOf<Certificate>(certificate)
        val keys = arrayOf(currentPrivateKey!!)
        val encodedCMS: String = createCMSSign(data, certs, keys, true)
        val sessionKey: DecryptedSessionKey =
            decryptSessionKey(encryptedSessionKey)
        val encryptedName: EncryptedMessage =
            encryptMessage(cid, file.name, sessionKey)
        val encryptedFile = SystemUtils.encodeBase64(
            cipherData(
                data,
                sessionKey
            )
        )
        return EncryptedFile(
            cid,
            encryptedName.message,
            encryptedName.sign,
            encryptedFile,
            encodedCMS
        )

    }

    fun decryptFile(
        encryptedFile: String,
        privateKey: PrivateKey,
        sessionKey: DecryptedSessionKey
    ): ByteArray {
        val bEncryptedData = SystemUtils.decodeBase64(encryptedFile)
        return decipherData(bEncryptedData, sessionKey)
    }

    fun decryptString(
        pk: PrivateKey,
        encryptedMessage: String,
        sessionKey: DecryptedSessionKey
    ): String {
        val bDecodedMessage = SystemUtils.decodeBase64(encryptedMessage)
        val bDecr: ByteArray = decipherData(bDecodedMessage, sessionKey)
        return String(bDecr)
    }


    fun decryptMessage(
        encryptedMessage: Message,
        sessionKey: DecryptedSessionKey
    ): String {
        val message: String = encryptedMessage.text ?: ""
        val bDecodedMessage = SystemUtils.decodeBase64(message)
        val bDecr: ByteArray = decipherData(bDecodedMessage, sessionKey)
        return String(bDecr)
    }


    private fun cipherData(data: ByteArray, sessionKey: DecryptedSessionKey): ByteArray {
        val ss: GostCipherSpec =
            getCipherSpec(sessionKey.IV)
        return try {
            val cipher = Cipher.getInstance(
                CIPHER_ALG + CIPHER_DATA_ALG_PARAMS,
                JCSP.PROVIDER_NAME
            )
            cipher.init(Cipher.ENCRYPT_MODE, sessionKey.key, ss, null)
            cipher.doFinal(data)
        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }

    private fun decipherData(data: ByteArray, sessionKey: DecryptedSessionKey): ByteArray {
        val ss: GostCipherSpec =
            getCipherSpec(sessionKey.IV)
        return try {
            val cipher = Cipher.getInstance(
                CIPHER_ALG + CIPHER_DATA_ALG_PARAMS,
                JCSP.PROVIDER_NAME
            )
            cipher.init(Cipher.DECRYPT_MODE, sessionKey.key, ss, null)
            cipher.doFinal(data)
        } catch (e: java.lang.Exception) {
            throw Exception(e)
        }
    }

    fun createCMSSign(
        data: ByteArray,
        certs: kotlin.Array<Certificate>,
        keys: kotlin.Array<PrivateKey>,
        detached: Boolean
    ): String {
        val all = ContentInfo()
        all.contentType = Asn1ObjectIdentifier(
            OID(STR_CMS_OID_SIGNED).value
        )
        val cms = SignedData()
        all.content = cms
        cms.version = CMSVersion(1)
        cms.digestAlgorithms = DigestAlgorithmIdentifiers(1)
        val a = DigestAlgorithmIdentifier(
            OID(DIGEST_OID).value
        )
        a.parameters = Asn1Null()
        cms.digestAlgorithms.elements[0] = a
        if (detached) {
            // Открепленная подпись
            cms.encapContentInfo = EncapsulatedContentInfo(
                Asn1ObjectIdentifier(
                    OID(STR_CMS_OID_DATA).value
                ), null
            )
        } else {
            // Прикрепленная подпись
            cms.encapContentInfo = EncapsulatedContentInfo(
                Asn1ObjectIdentifier(OID(STR_CMS_OID_DATA).value),
                Asn1OctetString(data)
            )
        }

        // Сертификаты.
        // Enumerate certificates
        val nCerts = certs.size
        cms.certificates = CertificateSet(nCerts)
        cms.certificates.elements = arrayOfNulls(nCerts)
        for (i in cms.certificates.elements.indices) {
            val certificate = ru.CryptoPro.JCP.ASN.PKIX1Explicit88.Certificate()
            val decodeBuffer = Asn1BerDecodeBuffer(certs[i].encoded)
            certificate.decode(decodeBuffer)
            cms.certificates.elements[i] = CertificateChoices()
            cms.certificates.elements[i].set_certificate(certificate)
        }
        val signature = Signature.getInstance(
            CMS_SIGNATURE_ALGORITHM,
            JCSP.PROVIDER_NAME
        )
        var sign: ByteArray

        // Подписанты (те кто подписывает).
        val nSigners = keys.size
        cms.signerInfos = SignerInfos(nSigners)
        for (i in cms.signerInfos.elements.indices) {

            // Create signer info
            cms.signerInfos.elements[i] = SignerInfo()
            cms.signerInfos.elements[i].version = CMSVersion(1)
            cms.signerInfos.elements[i].sid = SignerIdentifier()

            // Add certificate info
            val encodedName = (certs[i] as X509Certificate)
                .issuerX500Principal.encoded
            val nameBuf = Asn1BerDecodeBuffer(encodedName)
            val name = Name()
            name.decode(nameBuf)
            val num = CertificateSerialNumber(
                (certs[i] as X509Certificate).serialNumber
            )
            cms.signerInfos.elements[i].sid.set_issuerAndSerialNumber(
                IssuerAndSerialNumber(name, num)
            )
            cms.signerInfos.elements[i].digestAlgorithm = DigestAlgorithmIdentifier(
                OID(DIGEST_OID).value
            )
            cms.signerInfos.elements[i].digestAlgorithm.parameters = Asn1Null()
            val keyAlgOid = AlgorithmUtility.keyAlgToKeyAlgorithmOid(
                keys[0].algorithm
            )
            cms.signerInfos.elements[i].signatureAlgorithm =
                SignatureAlgorithmIdentifier(OID(keyAlgOid).value)
            cms.signerInfos.elements[i].signatureAlgorithm.parameters = Asn1Null()
            signature.initSign(keys[i])
            signature.update(data)
            sign = signature.sign()
            cms.signerInfos.elements[i].signature = SignatureValue(sign)
        }
        val asnBuf = Asn1BerEncodeBuffer()
        all.encode(asnBuf, true)
        return SystemUtils.encodeBase64(asnBuf.msgCopy)
    }

}