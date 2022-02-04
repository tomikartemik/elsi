package com.komandor.komandor.ui.message_info;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.komandor.komandor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class VerifiedSignDialogFragment extends DialogFragment {
  public static final String TAG = "VERIFIED_SIGN_DIALOG";
  public static final String VERIFIED_KEY = "VERIFIED_KEY";
  
  private VerifiedSignDialogListener verifiedSignDialogListener;
  
  public static VerifiedSignDialogFragment newInstance(boolean verified) {
    
    Bundle args = new Bundle();
    args.putBoolean(VERIFIED_KEY, verified);
    
    VerifiedSignDialogFragment fragment = new VerifiedSignDialogFragment();
    fragment.setArguments(args);
    return fragment;
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    super.onAttach(context);
  }
  
  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    boolean verified = getArguments().getBoolean(VERIFIED_KEY);
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    
    if(verified) {
      builder.setTitle(R.string.sign_is_verified);
      builder.setMessage(R.string.sign_is_verified_description)
          .setPositiveButton(R.string.save, (dialog, id) -> {
            verifiedSignDialogListener.onDialogPositiveClick(this);
          })
          .setNegativeButton(R.string.no_save, (dialog, id) -> {
            verifiedSignDialogListener.onDialogNegativeClick(this);
          });
    } else {
      builder.setTitle(R.string.sign_is_not_verified);
      builder.setMessage(R.string.sign_is_not_verified_description)
          .setPositiveButton(R.string.save, (dialog, id) -> {
            verifiedSignDialogListener.onDialogPositiveClick(this);
          })
          .setNegativeButton(R.string.no_save, (dialog, id) -> {
            verifiedSignDialogListener.onDialogNegativeClick(this);
          });
    }
    return builder.create();
  }
  
  public void setDialogListener(VerifiedSignDialogListener verifiedSignDialogListener) {
    this.verifiedSignDialogListener = verifiedSignDialogListener;
  }
  
  public interface VerifiedSignDialogListener {
    void onDialogPositiveClick(DialogFragment dialog);
    void onDialogNegativeClick(DialogFragment dialog);
  }
}
