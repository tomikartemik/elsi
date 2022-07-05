package com.komandor.komandor.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.komandor.komandor.R
import com.komandor.komandor.cryptopro.base.EncryptDecryptData
import com.komandor.komandor.cryptopro.utils.DialogUtil
import com.komandor.komandor.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import org.apache.xml.security.utils.resolver.ResourceResolver
import ru.CryptoPro.AdES.AdESConfig
import ru.CryptoPro.JCPxml.XmlInit
import ru.CryptoPro.JCPxml.dsig.internal.dom.XMLDSigRI
import ru.CryptoPro.JCSP.CSPConfig
import ru.CryptoPro.JCSP.JCSP
import ru.CryptoPro.JCSP.support.BKSTrustStore
import ru.CryptoPro.reprov.RevCheck
import ru.CryptoPro.ssl.util.cpSSLConfig
import ru.cprocsp.ACSP.tools.common.CSPTool
import ru.cprocsp.ACSP.tools.common.Constants
import timber.log.Timber
import java.io.File
import java.security.Provider
import java.security.Security

@AndroidEntryPoint
class MainTestCryproProActivity : AppCompatActivity(){

    lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
       // setContentView(R.layout.activity_main)
        //throw RuntimeException("Test Crash") // Force a crash
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { f1, destination, arguments ->
            Timber.d("destination = ${destination.displayName}")
            binding.tvTitle.text = destination.label
        }

        // 1. Инициализация провайдеров: CSP и java-провайдеров
        // (Обязательная часть).
        if (!initCSPProviders()) {
            Timber.d( "Couldn't initialize CSP.")
            return
        } // if


        initJavaProviders()

        // 2. Копирование тестовых контейнеров для подписи,
        // проверки подписи, шифрования и TLS (Примеры и вывод
        // в лог).


        // 2. Копирование тестовых контейнеров для подписи,
        // проверки подписи, шифрования и TLS (Примеры и вывод
        // в лог).
        installContainers()

        // 3. Инициируем объект для управления выбором типа
        // контейнера (Настройки).


        // 3. Инициируем объект для управления выбором типа
        // контейнера (Настройки).
        //KeyStoreType.init(this)

        // 4. Инициируем объект для управления выбором типа
        // провайдера (Настройки).


        // 4. Инициируем объект для управления выбором типа
        // провайдера (Настройки).
        //ProviderType.init(this)

        // 5. Вывод информации о тестовых контейнерах.


        // 5. Вывод информации о тестовых контейнерах.
        logTestContainers()

        // 6. Вывод информации о провайдере и контейнерах
        // (Пример).


        // 6. Вывод информации о провайдере и контейнерах
        // (Пример).
        logJCspServices()


    }


    /************************ Инициализация провайдера ************************/

    /************************ Инициализация провайдера  */
    /**
     * Инициализация CSP провайдера.
     *
     * @return True в случае успешной инициализации.
     */
    private fun initCSPProviders(): Boolean {

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
        val initCode = CSPConfig.init(this)
        val initOk = initCode == CSPConfig.CSP_INIT_OK

        // Если инициализация не удалась, то сообщим об ошибке.
        if (!initOk) {
            when (initCode) {
                CSPConfig.CSP_INIT_CONTEXT -> DialogUtil.errorMessage(
                    this,
                    "Couldn't initialize context."
                )
                CSPConfig.CSP_INIT_CREATE_INFRASTRUCTURE -> DialogUtil.errorMessage(
                    this,
                    "Couldn't create CSP infrastructure."
                )
                CSPConfig.CSP_INIT_COPY_RESOURCES -> DialogUtil.errorMessage(
                    this,
                    "Couldn't copy CSP resources."
                )
                CSPConfig.CSP_INIT_CHANGE_WORK_DIR -> DialogUtil.errorMessage(
                    this,
                    "Couldn't change CSP working directory."
                )
                CSPConfig.CSP_INIT_INVALID_LICENSE -> DialogUtil.errorMessage(
                    this,
                    "Invalid CSP serial number."
                )
                CSPConfig.CSP_TRUST_STORE_FAILED -> DialogUtil.errorMessage(
                    this,
                    "Couldn't create trust store for CAdES API."
                )
                CSPConfig.CSP_STORE_LIBRARY_PATH -> DialogUtil.errorMessage(
                    this,
                    "Couldn't store native library path to config."
                )
                CSPConfig.CSP_INIT_INVALID_INTEGRITY -> DialogUtil.errorMessage(
                    this,
                    "Integrity control failure."
                )
            }
        } // if
        return initOk
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
    private fun initJavaProviders() {

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
        val trustStorePath = applicationInfo.dataDir + File.separator +
                BKSTrustStore.STORAGE_DIRECTORY + File.separator + BKSTrustStore.STORAGE_FILE_TRUST
        val trustStorePassword = String(BKSTrustStore.STORAGE_PASSWORD)
        Log.d(
            Constants.APP_LOGGER_TAG,
            "Default trust store: $trustStorePath"
        )
        System.setProperty("javax.net.ssl.trustStoreType", BKSTrustStore.STORAGE_TYPE)
        System.setProperty("javax.net.ssl.trustStore", trustStorePath)
        System.setProperty("javax.net.ssl.trustStorePassword", trustStorePassword)
    }

    /**
     * Копирование тестовых контейнеров для подписи,
     * шифрования, обмена по TLS из архива в папку
     * keys приложения.
     *
     */
    private fun installContainers() {
        val cspTool = CSPTool(this)
        cspTool.appInfrastructure.copyContainerFromArchive(R.raw.srv2021n)

    }

    /************************** Служебные функции ****************************/

    /************************** Служебные функции  */
    /**
     * Вывод списка поддерживаемых алгоритмов.
     *
     */
    private fun logJCspServices() {
        //ProviderServiceInfo.logServiceInfo(KeyStoreUtil.DEFAULT_PROVIDER)
    }

    /**
     * Информация о тестовых контейнерах.
     *
     */
    private fun logTestContainers() {

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
        val format = getString(R.string.ContainerAboutTestContainer)
        Timber.d("$$$ About test containers $$$")
        for (i in aliases.indices) {
            val aboutTestContainer = String.format(
                format,
                aliases[i], passwords[i].toString()
            )
            Timber.d("** $i) $aboutTestContainer")
        } // for
    }

    fun showProgress(){
        binding.cvLoading.visibility = View.VISIBLE
    }

    fun hideProgress(){
        binding.cvLoading.visibility = View.GONE
    }
}