package com.komandor.komandor.widgets;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class PickImageDialog extends DialogFragment {
  public static final String TAG = "PICK_IMAGE";
  public static final String GALLERY_RESULT = "GALLERY_RESULT";
  public static final String CAMERA_RESULT = "CAMERA_RESULT";
  
  OnOptionClickDialogListener onOptionClickDialogListener;
  
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
  
    try {
      onOptionClickDialogListener = (OnOptionClickDialogListener) context;
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString()
          + " must implement PickImageDialog.OnOptionClickDialogListener");
    }
  }
  
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
    dialogBuilder.setTitle("Выберите способ получения изображения");
    String[] options = {"Сделать фото", "Выбрать из галереи"};
    dialogBuilder.setItems(options, (dialog, which) -> {
      switch (which) {
        case 0:
          onOptionClickDialogListener.onMakePhotoClick();
          break;
        case 1:
          onOptionClickDialogListener.onGetPhotoClick();
          break;
      }
    });
  
  
    return dialogBuilder.create();
  }
  
  
  public interface OnOptionClickDialogListener {
    void onMakePhotoClick();
    void onGetPhotoClick();
  }
}
