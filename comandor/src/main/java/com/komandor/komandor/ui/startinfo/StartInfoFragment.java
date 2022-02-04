package com.komandor.komandor.ui.startinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.komandor.komandor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.logging.Logger;

public class StartInfoFragment extends Fragment {
  
  public static StartInfoFragment newInstance() {
    return new StartInfoFragment();
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fr_start_info, container, false);
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  
    MaterialButton button = view.findViewById(R.id.btn_start_info_get_certificate_button);
    TextView lc = view.findViewById(R.id.lc);

    lc.setOnClickListener((v) ->{
      Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
      intent
              .setType("*/*");
      intent
              .addCategory(Intent.CATEGORY_OPENABLE);
      //intent.putExtra("browseCoa", itemToBrowse);
      //Intent chooser = Intent.createChooser(intent, "Select a File to Upload");
      try {
        //startActivityForResult(chooser, FILE_SELECT_CODE);
        startActivityForResult(Intent
                .createChooser(intent, "Select a File to Upload"),0);
      } catch (Exception ex) {
        System.out.println("browseClick :"+ex);//android.content.ActivityNotFoundException ex
      }
    });
    button.setOnClickListener((v) -> {
      Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ucsouz.ru/"));
      startActivity(myIntent);
    });
  }
  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data){
    if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null){
      Logger log = Logger.getLogger(StartInfoFragment.class.getName());
      log.info("File was selected!");

      Uri uri = data.getData();
      SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
      SharedPreferences.Editor editor = sharedPref.edit();
      assert uri != null;
      editor.putString("Uri", uri.toString());
      editor.apply();
      log.info("From shared preferences" + uri.toString());
    }
  }
}
