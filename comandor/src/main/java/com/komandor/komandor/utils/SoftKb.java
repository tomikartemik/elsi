package com.komandor.komandor.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by cross2000 on 17.05.2017.
 */

public class SoftKb {
    public static void showSoftKeyboard(Context ctx, View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    public static void hideSoftKeyboard(Context ctx, View view){
        InputMethodManager imm =(InputMethodManager)ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
