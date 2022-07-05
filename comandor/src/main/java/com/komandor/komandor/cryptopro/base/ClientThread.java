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
package com.komandor.komandor.cryptopro.base;

import android.os.Looper;

import com.komandor.komandor.cryptopro.interfaces.ThreadExecuted;
import com.komandor.komandor.cryptopro.utils.ProgressDialogHolder;

import timber.log.Timber;


/**
 * Служебный класс ClientThread выполняет задачу
 * в отдельном потоке.
 *
 * 29/05/2013
 *
 */
public class ClientThread extends Thread {

    /**
     * Выполняемая задача.
     */
    private ThreadExecuted executedTask = null;

    /**
     * Окно ожидания.
     */
    private ProgressDialogHolder progressDialogHolder = null;

    /**
     * Конструктор.
     *
     * @param task Выполняемая задача.
     * @param progressDialogHolder Окно ожидания.
     */
    public ClientThread(ThreadExecuted task, ProgressDialogHolder progressDialogHolder) {
        this.progressDialogHolder = progressDialogHolder;
        this.executedTask = task;
    }

    @Override
    public void interrupt() {

        // Чтобы прервать, надо у каждого примера
        // сделать close()!

        super.interrupt();

    }

    /**
     * Поточная функция. Запускает выполнение
     * задания. В случае ошибки пишет сообщение
     * в лог.
     *
     */
    @Override
    public void run() {

        /**
         * Зададим, т.к. может потребоваться отобразить
         * окна CSP.
         *
         * В новой версии в провайдере есть этот вызов,
         * тут он делается для совместимости со старым
         * Android CSP.
         */

        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        // Выполняем задачу.

        executedTask.execute();

        // Закрываем окно ожидания.

        Timber.d("Client thread finished job.");
        progressDialogHolder.dismiss();

    }

}
