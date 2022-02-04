package com.komandor.komandor.ui.loading;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.komandor.komandor.common.SingleFragmentActivity;
import com.komandor.komandor.ui.auth.AuthActivity;
import com.komandor.komandor.ui.startinfo.StartInfoActivity;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LoadingActivity extends SingleFragmentActivity {
  
  LoadingViewModel loadingViewModel;
  
  private LoadingViewModel.OnNavigateListener onNavigateListener = new LoadingViewModel.OnNavigateListener() {
    @Override
    public void navigateToValidationScreen() {
      navigateTo(AuthActivity.class);
    }
  
    @Override
    public void navigateToStartScreen() {
      navigateTo(StartInfoActivity.class);
    }

//  private void navigateToAuthScreen() {
//    Intent intent = new Intent(this, CertificatesListScreen.class);
//    intent.putExtra(Constants.INTENT_EXTRA_FROM_LOADING, true);
//    startActivity(intent);
//    getActivity().finish();
//  }

//  private void navigateToMainScreen() {
//    navigateTo(MainActivity.class);
//  }
  };
  
  @Override
  protected void onResume() {
    super.onResume();
    LoadingViewModelFactory loadingViewModelFactory = new LoadingViewModelFactory(onNavigateListener);
    loadingViewModel = ViewModelProviders.of(this, loadingViewModelFactory).get(LoadingViewModel.class);
  
    // TODO Для обработки отказа реализовать через интерфейс
    if (!PermissionUtils.hasExternalStoragePermissions(getApplicationContext())) {
      String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
      ActivityCompat.requestPermissions(this, permissions, Constants.STORAGE_PERMISSIONS_RESULT);
      return;
    }
  
    loadingViewModel.startApp(getApplicationContext());
  }
  
  @Override
  protected Fragment getFragment() {
    return LoadingFragment.newInstance();
  }
  
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == Constants.STORAGE_PERMISSIONS_RESULT && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      loadingViewModel.startApp(getApplicationContext());
    }
  }
  
  private void navigateTo(Class<?> cls) {
    Intent intent = new Intent(LoadingActivity.this, cls);
    startActivity(intent);
    finish();
  }
}
