package com.komandor.komandor.ui.auth.cert_validation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.databinding.CertValidationBinding;
import com.komandor.komandor.ui.auth.AuthActivity;
import com.komandor.komandor.viewmodel.ViewModelFactory;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class CertValidationFragment extends Fragment {

  private OnLoginListener onLoginListener;
  private TextInputEditText passwordInput;

  @Inject
  ViewModelFactory viewModelFactory;
  CertValidationViewModel certValidationViewModel;
  
  @Inject
  TemporaryStorage temporaryStorage;

  public static CertValidationFragment newInstance() {
    return new CertValidationFragment();
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
  
    if(context instanceof OnLoginListener) {
      onLoginListener = (OnLoginListener) context;
    }
    
    certValidationViewModel = ViewModelProviders.of(this, viewModelFactory).get(CertValidationViewModel.class);
    certValidationViewModel.getPasswordError().observe(this, isError -> {
      if(isError){
        passwordInput.setError(getString(R.string.er_wrong_password));
      }
    });
    
    certValidationViewModel.getNavigateNext().observe(this, event -> {
      onLoginListener.onLogin(event.getContentIfNotHandled());
    });
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    CertValidationBinding binding = CertValidationBinding.inflate(inflater, container, false);
    binding.setVm(certValidationViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    
    passwordInput = view.findViewById(R.id.tiet_cert_validation_password_input);
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    if (getActivity() != null) {
      getActivity().setTitle(R.string.cert_validation_title);
      ((AuthActivity) getActivity()).showHomeButton(true);
    }
  }
  
  public interface OnLoginListener {
    void onLogin(boolean needRegistration);
  }
}
