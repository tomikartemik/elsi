package com.komandor.komandor.ui.base;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public interface IFragmentNav  {

    void handleBackPressed(final AppCompatActivity activity, final Fragment fragmentTwo);

    default void registerOnBackPressedCallback(final AppCompatActivity activity, final Fragment fragmentTwo) {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                handleBackPressed(activity, fragmentTwo);
            }
        };
        fragmentTwo.requireActivity().getOnBackPressedDispatcher().addCallback(fragmentTwo, callback);


    }
}