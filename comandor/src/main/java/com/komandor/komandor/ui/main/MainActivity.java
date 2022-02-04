package com.komandor.komandor.ui.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.komandor.komandor.R;
import com.komandor.komandor.common.BottomNavigationActivity;
import com.komandor.komandor.ui.auth.AuthActivity;
import com.komandor.komandor.ui.contact_info.ContactInfoActivity;
import com.komandor.komandor.ui.main.chats.ChatsFragment;
import com.komandor.komandor.ui.main.contacts.ContactsAdapter;
import com.komandor.komandor.ui.main.contacts.ContactsFragment;
import com.komandor.komandor.ui.main.profile.ProfileFragment;
import com.komandor.komandor.utils.Constants;
import com.komandor.komandor.utils.KomandorException;
import com.komandor.komandor.utils.PermissionUtils;
import com.komandor.komandor.utils.SystemUtils;
import com.komandor.komandor.widgets.PickImageDialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

public class MainActivity extends BottomNavigationActivity implements
    ProfileFragment.OnLogOutListener,
    ProfileFragment.OnLoadPhotoClickListener,
    PickImageDialog.OnOptionClickDialogListener {
  private MutableLiveData<Boolean> hasPermissions = new MutableLiveData<>(false);
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    if (savedInstanceState == null) {
      setStartFragmentIndex(2);
      addFragmentToNavigation(R.id.navigation_chats, ChatsFragment.newInstance()); // 0
      addFragmentToNavigation(R.id.navigation_contacts, ContactsFragment.newInstance()); // 1
      addFragmentToNavigation(R.id.navigation_profile, ProfileFragment.newInstance()); // 2
    }
  }
  
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (data != null) {
      if (requestCode == Constants.CAMERA_ACTIVITY_RESULT && resultCode == RESULT_OK) {
        Bundle extras = data.getExtras();
        if (extras != null) {
          Bitmap bitmap = (Bitmap) extras.get("data");
          
          ProfileFragment profileFragment = (ProfileFragment) getFragmentByTag(ProfileFragment.class.getName());
          profileFragment.setPhoto(bitmap);
        }
      } else if (requestCode == Constants.GALLERY_ACTIVITY_RESULT && resultCode == RESULT_OK) {
        Uri contentURI = data.getData();
        try {
          ProfileFragment profileFragment = (ProfileFragment) getFragmentByTag(ProfileFragment.class.getName());
          profileFragment.setPhoto(contentURI);
        } catch (Exception e) {
          new KomandorException(e).printStackTrace();
        }
      }
    }
  }
  
  @Override
  protected void onNavigationItemSelect(MenuItem item) {
    Fragment fragment = getCurrentFragment();
    switch (item.getItemId()) {
      case R.id.navigation_chats:
        setTitle(R.string.nav_bar_title_chats);
        if (fragment instanceof ChatsFragment) {
          ((ChatsFragment) fragment).checkPermissions();
        }
        break;
      case R.id.navigation_contacts:
        setTitle(R.string.nav_bar_title_contacts);
        if (fragment instanceof ContactsFragment) {
          ((ContactsFragment) fragment).checkPermissions();
        }
        break;
      case R.id.navigation_profile:
        setTitle(R.string.nav_bar_title_profile);
        break;
    }
  }
  
  private void requestCameraPermissions() {
    String[] permissions = {Manifest.permission.CAMERA};
    ActivityCompat.requestPermissions(MainActivity.this, permissions, Constants.CAMERA_PERMISSIONS_RESULT);
  }
  
  @Override
  public void onLogOut() {
    Intent intent = new Intent(this, AuthActivity.class);
    startActivity(intent);
    finish();
  }
  
  @Override
  public void onLoadPhotoClick() {
    DialogFragment dialog = new PickImageDialog();
    dialog.show(getSupportFragmentManager(), PickImageDialog.TAG);
  }
  
  @Override
  public void onGetPhotoClick() {
    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    
    startActivityForResult(galleryIntent, Constants.GALLERY_ACTIVITY_RESULT);
  }
  
  @Override
  public void onMakePhotoClick() {
    if (!SystemUtils.isCameraHardware(getApplicationContext())) {
      return;
    }
    
    if (!PermissionUtils.hasCameraPermissions(getApplicationContext())) {
      requestCameraPermissions();
      return;
    }
    
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
      startActivityForResult(takePictureIntent, Constants.CAMERA_ACTIVITY_RESULT);
    }
  }
}
