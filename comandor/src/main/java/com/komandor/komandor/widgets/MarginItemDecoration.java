package com.komandor.komandor.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MarginItemDecoration extends RecyclerView.ItemDecoration {
  
  private final int spaceHeight;
  
  public MarginItemDecoration(int spaceHeight) {
    this.spaceHeight = spaceHeight;
  }
  
  @Override
  public void getItemOffsets(@NonNull Rect outRect, View view, RecyclerView parent,
                             RecyclerView.State state) {
    if (parent.getChildAdapterPosition(view) == 0) {
      outRect.bottom = spaceHeight;
    }
    
    outRect.top = spaceHeight;
    outRect.left = spaceHeight;
    outRect.right = spaceHeight;
  }
  
}
