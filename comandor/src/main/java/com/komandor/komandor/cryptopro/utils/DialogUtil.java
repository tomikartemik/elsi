package com.komandor.komandor.cryptopro.utils;

import android.app.Activity;
import android.app.AlertDialog;


public class DialogUtil {

    /**
     * Отображение окна с сообщением.
     *
     * @param activity Рабочая форма.
     * @param message Сообщение.
     */
    public static void errorMessage(final Activity activity, String message) {
        errorMessage(activity, message, false, true);
    }

    /**
     * Отображение окна с сообщением.
     *
     * @param activity Рабочая форма.
     * @param message Сообщение.
     * @param finish True, если следует закрыть окно.
     */
    public static void errorMessage(final Activity activity,
                                    String message, boolean cancellable, final boolean finish) {

        // Окно с сообщением.
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setMessage(message);
        dialog.setCancelable(cancellable);

        // Закрытие окна.
        dialog.setPositiveButton(android.R.string.ok,
                (dialog1, whichButton) -> {
                    if (finish) {
                        activity.finish();
                    } // if
                });

        dialog.show();

    }

}
