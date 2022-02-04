package com.komandor.komandor.utils;

import android.os.Environment;

public class Constants {
  public static final int STORAGE_PERMISSIONS_RESULT = 10000;
  public static final int CAMERA_PERMISSIONS_RESULT = 10001;
  public static final int CAMERA_ACTIVITY_RESULT = 10002;
  public static final int GALLERY_ACTIVITY_RESULT = 10003;
  public static final int CONTACTS_PERMISSIONS_RESULT = 10004;
  public static final int OPEN_FILE_RESULT = 10006;
  public static final int FINISH_START_ACTIVITY = 11000;
  public static final int FINISH_CHAT_ACTIVITY = 11001;
  
  public static final int NO_TYPE = 0; // Нет типа контакта
  public static final int NP = 1; // Физ. лицо (natural person)
  public static final int SP = 2; // ИП
  public static final int LP = 3; // Юр. лицо (legal person)
  
  public static final String HEADER_CACHE_CONTROL = "Cache-Control";
  public static final String HEADER_PRAGMA = "Pragma";

  public static final String DEFAULT_CONTAINERS_STORAGE_DIRECTORY = "Komandor";
  public static final String DOWNLOADS_FOLDER = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/Komandor";
  public static final long cacheSize = 10 * 1024 * 1024;
  
  public static final String MESSAGE_TRANSITION_NAME = "MESSAGE_TN_";
  
  public static final String INTENT_EXTRA_CONTACT_ID = "INTENT_EXTRA_CONTACT_ID";
  public static final String INTENT_EXTRA_CHAT_ID = "INTENT_EXTRA_CHAT_ID";
  public static final String INTENT_EXTRA_MESSAGE = "INTENT_EXTRA_MESSAGE";
  public static final String INTENT_EXTRA_TRANSITION_NAME = "INTENT_EXTRA_TRANSITION_NAME";
  public static final String INTENT_EXTRA_TO_CHATS = "INTENT_EXTRA_TO_CHATS";
  public static final String INTENT_EXTRA_TO_PROFILE = "INTENT_EXTRA_TO_PROFILE";
  public static final String INTENT_EXTRA_TO_CONTACTS = "INTENT_EXTRA_TO_CONTACTS";
  public static final String INTENT_EXTRA_AFTER_RESTORATION = "INTENT_EXTRA_AFTER_RESTORATION";
  
  public static final String PREFERENCE_PASSWORD = "PREFERENCE_PASSWORD";
  public static final String PREFERENCE_ALIAS = "PREFERENCE_ALIAS";
  public static final String PREFERENCE_NEED_REGISTER = "PREFERENCE_NEED_REGISTER";
  public static final String PREFERENCE_TOKEN = "PREFERENCE_TOKEN";
  
  public static final int DATABASE_FALSE = 0;
  public static final int DATABASE_TRUE = 1;
}
