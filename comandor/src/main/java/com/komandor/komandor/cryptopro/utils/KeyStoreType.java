/**
 * Copyright 2004-2013 Crypto-Pro. All rights reserved.
 * Программный код, содержащийся в этом файле, предназначен
 * для целей обучения. Может быть скопирован или модифицирован
 * при условии сохранения абзацев с указанием авторства и прав.
 *
 * Данный код не может быть непосредственно использован
 * для защиты информации. Компания Крипто-Про не несет никакой
 * ответственности за функционирование этого кода.
 */
package com.komandor.komandor.cryptopro.utils;

import static com.komandor.komandor.cryptopro.utils.Constants.APP_LOGGER_TAG;
import static com.komandor.komandor.cryptopro.utils.Constants.RUTOKEN;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.security.Provider;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ru.CryptoPro.JCSP.JCSP;
import ru.cprocsp.ACSP.tools.common.CSPTool;
import ru.cprocsp.ACSP.tools.config.Config;

/**
 * Служебный класс KeyStoreType предназначен
 * для загрузки/сохранения номера типа хранилища
 * в файл. Используется только в demo-приложении.
 *
 * 26/08/2013
 *
 */
public final class KeyStoreType extends ArrayResourceSelector {

    /**
     * Название файла для сохранения ресурсов.
     */
    private static final String KEY_TYPE_RESOURCE_NAME = "keyStores";

    /**
     * Объект для управления загрузки/сохранения
     * активного типа контейнера.
     *
     */
    private static KeyStoreType keyStoreType_ = null;

    /**
     * Проверка инициализации.
     */
    private static boolean initiated = false;

    /**
     * Инициализация объекта для работы с типами
     * контейнеров.
     *
     * @param context Контекст приложения.
     */
    public synchronized static void init(Context context) {

        if (!initiated) {

            try {

                Config readerConfig = loadConfig(context);

                boolean rutokenIsActive = true;
                if (readerConfig != null) {
                    String configReaderName = readerConfig.getCurrentReaderName();
                    if (configReaderName != null) {
                        if (!RUTOKEN.equalsIgnoreCase(configReaderName))
                            rutokenIsActive = false;
                    }
                }

                keyStoreType_ = new KeyStoreType(context, rutokenIsActive);
                initiated = true;

            } catch (IOException e) {
                ;
            }

        } // if
    }

    /**
     * Конструктор.
     *
     * @param context Контекст приложения.
     * @throws IOException
     */
    private KeyStoreType(Context context, boolean rutokenIsActive) throws IOException {
        super(context, KEY_TYPE_RESOURCE_NAME, getKeyStoreTypeList(rutokenIsActive));
    }

    public static Config loadConfig(Context context) {

        try {

            // Грузим разный конфиг, если разрядность разная.

            CSPTool tool = new CSPTool(context);

            String configFile = tool.getAppInfrastructure().isIsCspLib64()
                    ? tool.getAppInfrastructure().getConfig64File()
                    : tool.getAppInfrastructure().getConfigFile();

            return new Config(context, configFile, false);

        } catch (Exception e) {
            Log.e(APP_LOGGER_TAG, "Failed to load config.", e);
        }

        return null;

    }

    /**
     * Получение списка поддерживаемых типов контейнеров.
     *
     * @return список типов.
     */
    public static List<String> getKeyStoreTypeList(boolean rutokenIsActive) {

        List<String> keyStoreTypeList = new LinkedList<>();
        Set<Provider.Service> services = KeyStoreUtil.DEFAULT_PROVIDER.getServices();

        // Список типов контейнеров.
        for (Provider.Service service : services) {
            if (service.getType().equalsIgnoreCase("KeyStore")) {
                String algorithm = service.getAlgorithm();
                if (rutokenIsActive) {
                    if (algorithm.toLowerCase().contains(RUTOKEN))
                        keyStoreTypeList.add(algorithm);
                }
                else {
                    if (!algorithm.toLowerCase().contains(RUTOKEN))
                        keyStoreTypeList.add(algorithm);
                }
            } // if
        } // for

        if (!rutokenIsActive) {
            keyStoreTypeList.remove(JCSP.CERT_STORE_NAME); // А это - не тип контейнера.
            keyStoreTypeList.remove(JCSP.PFX_STORE_NAME);  // Пока не поддерживается передача.
            keyStoreTypeList.remove(JCSP.MY_STORE_NAME);
            keyStoreTypeList.remove(JCSP.ROOT_STORE_NAME);
            keyStoreTypeList.remove(JCSP.CA_STORE_NAME);
            keyStoreTypeList.remove(JCSP.ADDRESS_BOOK_STORE_NAME);
            keyStoreTypeList.remove(JCSP.FILE_STORE_NAME);
            keyStoreTypeList.remove(JCSP.SST_STORE_NAME);
            keyStoreTypeList.remove(JCSP.HD_STORE_NAME); // Удалим его, чтобы поставить на 1 место.
        }

        keyStoreTypeList.sort(String::compareToIgnoreCase);
        keyStoreTypeList.add(0, JCSP.HD_STORE_NAME); // поставить на 1 место.

        return keyStoreTypeList;

    }

    /**
     * Получение активного типа хранилища.
     *
     * @return Тип хранилища.
     */
    public static String currentType() {

        if (!initiated) {
            return JCSP.HD_STORE_NAME;
        } // if

        return keyStoreType_.currentValue();
    }

    /**
     * Сохранение типа хранилища в файл.
     *
     * @param type Тип хранилища.
     * @return True в случае успешного сохранения.
     */
    public static boolean saveCurrentType(String type) {

        if (initiated) {
            return keyStoreType_.saveValue(type);
        } // if

        return false;
    }

}
