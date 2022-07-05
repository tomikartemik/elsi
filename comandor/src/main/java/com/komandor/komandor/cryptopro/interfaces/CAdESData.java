/**
 * $RCSfileCAdESData.java,v $
 * version $Revision: 36379 $
 * created 05.12.2014 15:37 by Yevgeniy
 * last modified $Date: 2012-05-30 12:19:27 +0400 (Ср, 30 май 2012) $ by $Author: afevma $
 *
 * Copyright 2004-2014 Crypto-Pro. All rights reserved.
 * Программный код, содержащийся в этом файле, предназначен
 * для целей обучения. Может быть скопирован или модифицирован
 * при условии сохранения абзацев с указанием авторства и прав.
 *
 * Данный код не может быть непосредственно использован
 * для защиты информации. Компания Крипто-Про не несет никакой
 * ответственности за функционирование этого кода.
 */
package com.komandor.komandor.cryptopro.interfaces;

/**
 * Служебный интерфейс CAdESData предназначен для
 * релизации метода проверки существования сертификатов
 * в хранилище.
 *
 * @author Copyright 2004-2014 Crypto-Pro. All rights reserved.
 * @.Version
 */
public interface CAdESData extends HashData {

    /**
     * Проверка, присутствуют ли корневые сертификаты в хранилище.
     *
     * @return true, если сертификаты присутствуют.
     * @throws Exception
     */
    boolean isAlreadyInstalled() throws Exception;

}
