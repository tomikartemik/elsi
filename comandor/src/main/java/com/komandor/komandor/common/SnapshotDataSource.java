package com.komandor.komandor.common;

import java.util.Collections;
import java.util.List;

public abstract class SnapshotDataSource<T, PK> extends BaseDataSource<T> {

  private volatile List<PK> keys=null;

  protected abstract List<PK> loadKeys();
  protected abstract List<T> loadForIds(List<PK> pks);

  
  //public int findPositionForKey(PK key) {
  //  if (keys==null) {
  //    throw new IllegalStateException("Attempted to find position for key without having keys loaded");
  //  }
  //
  //  return keys.indexOf(key);
  //}
  
  //public PK findKeyForPosition(int position) {
  //  if (keys==null) {
  //    throw new IllegalStateException("Attempted to find position for key without having keys loaded");
  //  }
  //
  //  return keys.get(position);
  //}
  
  @Override
  protected List<T> loadRangeAtPosition(int position, int size) {
    initKeys();
    
    return loadForIds(keys.subList(position, Math.min(position+size, countItems())));
  }
  
  @Override
  protected int countItems() {
    initKeys();
    
    return keys.size();
  }
  
  synchronized private void initKeys() {
    if (keys==null) {
      keys=Collections.unmodifiableList(loadKeys());
    }
  }
}
