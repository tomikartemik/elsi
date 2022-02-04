package com.komandor.komandor.widgets.binding_adapters;

import android.graphics.drawable.Drawable;
import android.widget.LinearLayout;

import androidx.databinding.BindingAdapter;

public class LinearLayoutBindingAdapter {
  @BindingAdapter({"bind:background"})
  public static void configureLinearLayout(LinearLayout linearLayout, Drawable drawable) {
    linearLayout.setBackground(drawable);
  }
}
