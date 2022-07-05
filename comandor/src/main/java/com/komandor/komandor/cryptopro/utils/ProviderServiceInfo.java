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

import java.security.Provider;
import java.util.Iterator;
import java.util.Set;

import timber.log.Timber;

/**
 * Служебный класс ProviderServiceInfo предназначен
 * для вывода различной информации о провайдере: сервисах,
 * алгоритмах, доступных контейнерах.
 *
 * 27/08/2013
 *
 */
public class ProviderServiceInfo {

    /**
     * Вывод списка доступных сервисов и алгоритмов.
     *
     * @param provider Изучаемый провайдер.
     */
    public static void logServiceInfo(Provider provider) {

        Timber.d("\n *** Provider: " +
            provider.getName() + " ***\n");

        Set<Provider.Service> services = provider.getServices();
        Iterator<Provider.Service> serviceIterator = services.iterator();

        while (serviceIterator.hasNext()) {
            Provider.Service service = serviceIterator.next();
            Timber.d("\ttype: " + service.getType() +
                ", algorithm: " + service.getAlgorithm());
        } // while

    }

    /**
     * Вывод списка доступных контейнеров для данного
     * провайдера.
     *
     * @param provider Изучаемый провайдер.
     */
    /*
    public static void logKeyStoreInfo(Provider provider) {

        if (provider == null) {
            provider = KeyStoreUtil.DEFAULT_PROVIDER;
        } // if

        Logger.log("\n *** Provider: " +
            provider.getName() + " ***");

        try {

            // Тип контейнера по умолчанию.

            String keyStoreType = KeyStoreType.currentType();
            Logger.log("\n\tDefault container type: " + keyStoreType);

            KeyStore keyStore = KeyStore.getInstance(keyStoreType, provider);
            keyStore.load(null, null);

            Logger.log("\n\tLoaded containers:\n");
            Enumeration<String> aliases = keyStore.aliases();

            while (aliases.hasMoreElements()) {
                Logger.log("\t\t" + aliases.nextElement());
            } // while

        } catch (Exception e) {
            ;
        }

    }
    */

}
