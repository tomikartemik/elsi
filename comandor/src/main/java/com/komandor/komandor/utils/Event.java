package com.komandor.komandor.utils;

public class Event<T> {
  
  private T content;
  
  private boolean hasBeenHandled = false;
  
  public Event(T content) {
    this.content = content;
  }
  
  public T getContentIfNotHandled() {
    if(hasBeenHandled) {
      return null;
    } else {
      hasBeenHandled = true;
      return content;
    }
  }
  
  public T getContent() {
    return content;
  }
}
