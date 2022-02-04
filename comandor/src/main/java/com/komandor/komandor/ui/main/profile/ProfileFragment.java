package com.komandor.komandor.ui.main.profile;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Bitmap;

import com.google.android.material.button.MaterialButton;
import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.databinding.ProfileBinding;
import com.komandor.komandor.utils.CircleTransformation;
import com.komandor.komandor.utils.SystemUtils;
import com.komandor.komandor.viewmodel.ViewModelFactory;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment {
  
  public static final String TAG = "PROFILE";
  
  @Inject
  ViewModelFactory viewModelFactory;
  ProfileViewModel profileViewModel;
  
  private OnLogOutListener onLogOutListener;
  private OnLoadPhotoClickListener onLoadPhotoClickListener;
  private MaterialButton loadPhotoButton;
  private ImageView avatar;
  
  public static ProfileFragment newInstance() {
    return new ProfileFragment();
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    ProfileBinding binding = ProfileBinding.inflate(inflater, container, false);
    binding.setVm(profileViewModel);
    binding.setLifecycleOwner(this);
    return binding.getRoot();
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  
    loadPhotoButton = view.findViewById(R.id.btn_profile_load_photo);
    loadPhotoButton.setOnClickListener(v -> {
      onLoadPhotoClickListener.onLoadPhotoClick();
    });
    avatar = view.findViewById(R.id.iv_profile_avatar);
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
  
    if(context instanceof OnLogOutListener) {
      onLogOutListener = (OnLogOutListener) context;
    }
  
    if(context instanceof OnLoadPhotoClickListener) {
      onLoadPhotoClickListener = (OnLoadPhotoClickListener) context;
    }
    
    profileViewModel = ViewModelProviders.of(this, viewModelFactory).get(ProfileViewModel.class);
    profileViewModel.getIsLogOut().observe(this, event -> {
      if(event.getContentIfNotHandled()) {
        onLogOutListener.onLogOut();
      }
    });
    
    profileViewModel.getPhoto().observe(this, imageUri -> {
      
      Picasso.get().load(imageUri).transform(new CircleTransformation()).into(avatar);
    });
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
  }
  
  @Override
  public void onDetach() {
    super.onDetach();
    
    if(onLogOutListener != null) {
      onLogOutListener = null;
    }
    
    if(onLoadPhotoClickListener != null) {
      onLoadPhotoClickListener = null;
    }
  }
  
  public void setPhoto(Bitmap bitmap) {
    Uri imageUri = SystemUtils.getImageUri(getContext(), bitmap);
    profileViewModel.setPhoto(imageUri);
  }
  
  public void setPhoto(Uri imageUri) {
    profileViewModel.setPhoto(imageUri);
  }
  
  public interface OnLogOutListener {
    void onLogOut();
  }
  
  public interface OnLoadPhotoClickListener {
    void onLoadPhotoClick();
  }
}
