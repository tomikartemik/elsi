package com.komandor.komandor.ui.auth.registration;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.common.MaskWatcher;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.databinding.RegistrationBinding;
import com.komandor.komandor.ui.auth.AuthActivity;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class RegistrationFragment extends Fragment {
  
  @Inject
  ViewModelFactory viewModelFactory;
  RegistrationViewModel registrationViewModel;
  
  @Inject
  TemporaryStorage temporaryStorage;
  
  private TextInputLayout phoneInputLayout;
  private TextInputEditText phoneInput;
  private TextInputLayout codeInputLayout;
  private TextInputEditText codeInput;
  
  private OnRegistrationListener onRegistrationListener;
  
  public static RegistrationFragment newInstance() {
    return new RegistrationFragment();
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
  
    if(context instanceof OnRegistrationListener) {
      onRegistrationListener = (OnRegistrationListener) context;
    }
    
    registrationViewModel = ViewModelProviders.of(this, viewModelFactory).get(RegistrationViewModel.class);
    registrationViewModel
            .getPhoneError()
            .observe(this, isError -> {
      if(isError){
        phoneInput.setError(getString(R.string.er_wrong_phone_number));
      }
    });
    registrationViewModel
            .getCodeError()
            .observe(this, isError -> {
      if(isError){
        codeInput.setError(getString(R.string.er_wrong_sms_code));
      }
    });
    registrationViewModel
            .getNavigateNext()
            .observe(this, event -> {
      event.getContentIfNotHandled();
      onRegistrationListener.onRegistration();
      
    });
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    RegistrationBinding binding = RegistrationBinding.inflate(inflater, container, false);
    binding.setVm(registrationViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  
    phoneInputLayout = view.findViewById(R.id.til_auth_phone);
    phoneInput = view.findViewById(R.id.tiet_auth_phone);
    phoneInput.setOnFocusChangeListener(onPhoneInputLayoutFocusChangeListener);
    phoneInput.addTextChangedListener(new MaskWatcher("+7 (###) ###-##-##"));
  
    codeInputLayout = view.findViewById(R.id.til_auth_code);
    codeInput = view.findViewById(R.id.tiet_auth_code);
    codeInput.setOnFocusChangeListener(onCodeInputLayoutFocusChangeListener);
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    if (getActivity() != null) {
      getActivity().setTitle(R.string.registration_title);
      ((AuthActivity) getActivity()).showHomeButton(true);
    }
  }
  
  
  private View.OnFocusChangeListener onPhoneInputLayoutFocusChangeListener = (v, hasFocus) -> {
    if (hasFocus) {
      phoneInputLayout.setHint(getString(R.string.authPhoneNumber));
      return;
    }
    
    phoneInputLayout.setHint(getString(R.string.authPhoneNumberHint));
  };
  
  private View.OnFocusChangeListener onCodeInputLayoutFocusChangeListener = (v, hasFocus) -> {
    if (hasFocus) {
      codeInputLayout.setHint(getString(R.string.authCode));
      return;
    }
    
    codeInputLayout.setHint(getString(R.string.authTypeCode));
  };
  
  public interface OnRegistrationListener{
    void onRegistration();
  }
}
