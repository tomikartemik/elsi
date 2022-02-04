package com.komandor.komandor.common;

import android.text.Editable;
import android.text.TextWatcher;

public class MaskWatcher implements TextWatcher {

  private final String mask;

  private boolean isRunning = false;
  private boolean isDeleting = false;

  public MaskWatcher(String mask) {
    this.mask = mask;
  }
  
  @Override
  public void beforeTextChanged
          (CharSequence charSequence,
           int start,
           int count,
           int after) {
    isDeleting = count > after;
  }
  
  @Override
  public void onTextChanged
          (CharSequence charSequence,
           int start,
           int before,
           int count) {
  }
  
  @Override
  public void afterTextChanged(Editable editable) {
    if (isRunning || isDeleting) {
      return;
    }
    isRunning = true;
    
    int editableLength = editable.length();
    if (editableLength < mask.length()) {
      if (editableLength == 0 && mask.charAt(editableLength) != '#') {
        editable.append(mask.charAt(editableLength));
        isRunning = false;
        return;
      }
      
      String inputChar = Character
              .toString
                      (editable
                              .toString()
                              .charAt(editableLength - 1));
      String currentMaskChar = Character
              .toString
                      (mask
                              .charAt(editableLength - 1));
      String nextMaskChar = Character
              .toString
                      (mask.charAt(editableLength));
      if (inputChar.equals(currentMaskChar)) {
        isRunning = false;
        return;
      }
      
      if (!"0123456789".contains(inputChar)) {
        editable.delete(editableLength - 1, editableLength);
        isRunning = false;
        return;
      }
      
      if (!currentMaskChar.equals("#")) {
        if (!nextMaskChar.equals("#")) {
          isRunning = false;
        }
        editable
                .insert(
                        editableLength - 1,
                        mask,
                        editableLength - 1,
                        editableLength);
      }
    }
    
    isRunning = false;
  }
}

