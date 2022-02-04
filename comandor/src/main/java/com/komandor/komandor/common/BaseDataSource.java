package com.komandor.komandor.common;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

abstract class BaseDataSource<T> extends PositionalDataSource<T> {
  abstract protected int countItems();
  
  abstract protected List<T> loadRangeAtPosition(int position, int size);
  
  @Override
  public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<T> callback) {
    int total = countItems();
    
    if (total == 0) {
      callback
              .onResult
                      (Collections
                              .emptyList(),
                              0,
                              0);
    } else {
      final int position = computeInitialLoadPosition
              (params, total);
      final int size = computeInitialLoadSize
              (params, position, total);
      List<T> list = loadRangeAtPosition
              (position, size);
      
      if (list != null && list.size() == size) {
        callback
                .onResult
                        (list, position, total);
      } else{invalidate();}
    }
  }
  
  @Override
  public void loadRange
          (@NonNull LoadRangeParams params,
           @NonNull LoadRangeCallback<T> callback) {
    List<T> list = loadRangeAtPosition(params.startPosition, params.loadSize);
    
    if (list != null) {
      callback.onResult(list);
    } else {
      invalidate();
    }
  }
}
