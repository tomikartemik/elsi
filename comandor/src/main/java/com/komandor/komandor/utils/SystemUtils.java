package com.komandor.komandor.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Window;
import android.view.WindowManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SystemUtils {
  public static void disableUserActivity(Window window) {
    window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }
  
  public static void enableUserActivity(Window window) {
    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
  }
  
  public static File saveFile(String fullFileName, byte[] data) {
    int dotIdx = fullFileName.lastIndexOf('.');
    String fileName = fullFileName.substring(0, dotIdx);
    String fileExt = fullFileName.substring(dotIdx);
    
    File rootFolder = new File(Constants.DOWNLOADS_FOLDER);
    if (!rootFolder.exists() && !rootFolder.mkdir()) {
      Exception e = new KomandorException("Can not create downloads root folder!");
      e.printStackTrace();
      return null;
    }
    
    File file = new File(Constants.DOWNLOADS_FOLDER, fullFileName);
    if (file.exists()) {
      int dashIdx = fileName.lastIndexOf('_');
      if (dashIdx != -1) {
        String fileIdx = fileName.substring(dashIdx + 1);
        try {
          int num = Integer.parseInt(fileIdx);
          fileName = fileName.substring(0, dashIdx);
          return saveFile(fileName + "_" + (num + 1) + fileExt, data);
        } catch (Exception ignored) {
        }
      }
      return saveFile(fileName + "_1" + fileExt, data);
    }
    
    
    try {
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(data);
      outputStream.close();
      
      return file;
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }
    
    return null;
  }
  
  public static void deleteFile(File fileOrDirectory, Boolean clearDirContentOnly) {
    if (fileOrDirectory.exists() && fileOrDirectory.isDirectory()) {
      for (File child : fileOrDirectory.listFiles()) {
        deleteFile(child, false);
      }
    }
    
    if (!clearDirContentOnly) {
      fileOrDirectory.delete();
    }
  }
  
  public static boolean isCameraHardware(Context context) {
    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
      return true;
    } else {
      return false;
    }
  }
  
  public static String maskPhone(String unmaskedPhone) {
    Pattern pattern = Pattern.compile("(\\d)(\\d{3})(\\d{3})(\\d{2})(\\d{2})");
    Matcher matcher = pattern.matcher(unmaskedPhone);
    matcher.find();
    
    return "+" + matcher.group(1) + " " + matcher.group(2) + " " + matcher.group(3) + "-" + matcher.group(4) + "-" + matcher.group(5);
  }
  
  public static String unmaskPhone(String phone) {
    return phone.replaceAll("[^0-9]", "");
  }
  
  public static String[] parseFIO(String FIO) {
    Pattern p = Pattern.compile("(.*) (.*) (.*)");
    Matcher m = p.matcher(FIO);
    m.find();
    
    return new String[]{
        m.group(1), m.group(2), m.group(3)
    };
  }
  
  public static Boolean hasNetworkConnection(Context context) {
    ConnectivityManager connMgr =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    assert connMgr != null;
    NetworkInfo network = connMgr.getActiveNetworkInfo();
    
    return network != null && network.isConnected();
  }
  
  public static byte[] reverseByteArray(byte[] arr) {
    int arrLength = arr.length;
    int lastElementIndex = arrLength - 1;
    
    byte[] result = new byte[arrLength];
    
    for (int i = lastElementIndex; i >= 0; i -= 1) {
      result[lastElementIndex - i] = arr[i];
    }
    
    return result;
  }
  
  public static boolean isToday(Calendar date) {
    Calendar today = Calendar.getInstance();
    
    return today.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
        today.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR);
  }
  
  public static boolean isYesterday(Calendar date) {
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_YEAR, -1);
    
    return yesterday.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
        yesterday.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR);
  }
  
  public static boolean isLaterThanYesterday(Calendar date) {
    Calendar yesterday = Calendar.getInstance();
    yesterday.add(Calendar.DAY_OF_YEAR, -1);
    
    return yesterday.get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
        yesterday.get(Calendar.DAY_OF_YEAR) > date.get(Calendar.DAY_OF_YEAR);
  }
  
  public static boolean recoverSession(Context context, boolean fromMainScreen) {
//    boolean initialized = CryptoUtils.init(context, true);
//
//    if (!initialized) {
//      return false;
//    }
//
//    if (!AndroidKeyStore.getInstance().hasKey()) {
//      Crashlytics.log(1, "RESTORATION", "NO KEYS");
//      return false;
//    }
//
//    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
//    String encryptedPassword = sharedPreferences.getString(Constants.PREFERENCE_PASSWORD, "");
//    String encryptedAlias = sharedPreferences.getString(Constants.PREFERENCE_ALIAS, "");
//    String encryptedToken = sharedPreferences.getString(Constants.PREFERENCE_TOKEN, "");
//
//    String password = AndroidKeyStore.getInstance().decryptData(encryptedPassword);
//    String alias = AndroidKeyStore.getInstance().decryptData(encryptedAlias);
//    String token = AndroidKeyStore.getInstance().decryptData(encryptedToken);
//
//    if (password == null || alias == null || token == null) {
//      Crashlytics.log(1, "RESTORATION", "Data is null");
//      return false;
//    }
//
//    App.setSelectedAlias(alias);
//    App.setToken(token);
//
//    if (!CryptoUtils.loadPrivateKey(password)) {
//      Crashlytics.log(1, "RESTORATION", "Can not load private key");
//      return false;
//    }
//
//    if (!fromMainScreen) {
//      if (!PermissionUtils.hasContactsPermissions(context)) {
//        return false;
//      }
//
//      ContactsDatabase.initDB(context);
//
//      App.getAccountData().setData(ContactsDatabase.getDB().getAccountsTable().getActiveAccount());
//
//      SocketAPI.getInstance().init(context, new SocketCallback() {
//        @Override
//        public void onConnect() {
//          super.onConnect();
//          ContactsDatabase.getDB().updateAll(context);
//        }
//      });
//    }
    
    return true;
  }
  
  public static boolean recoverSession(Context context) {
    return recoverSession(context, false);
  }
  
  public static String getUriPath(Uri uri) {
    final String docId = DocumentsContract.getDocumentId(uri);
    final String[] split = docId.split(":");
    final String type = split[0];
    
    if ("primary".equalsIgnoreCase(type)) {
      return Environment.getExternalStorageDirectory() + "/" + split[1];
    } else {
      return null;
    }
  }
  
  public static String encodeBase64(byte[] data) throws KomandorException {
    try {
      return new String(Base64.encode(data, Base64.NO_WRAP));
    } catch (Exception e) {
      throw new KomandorException(e);
    }
  }
  
  public static byte[] decodeBase64(String data) {
    try {
      return Base64.decode(data, Base64.NO_WRAP);
    } catch (Exception e) {
      e.printStackTrace();
//      Crashlytics.logException(e);
    }
    return null;
  }
  
  public static Uri getImageUri(Context context, Bitmap image) {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
    String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), image, "image", null);
    return Uri.parse(path);
  }

  public static String formatDateToString(long date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Date(date));
    SimpleDateFormat simpleDateFormat;

    if (SystemUtils.isToday(calendar)) {
      simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
      return simpleDateFormat.format(calendar.getTime());
    } else if (SystemUtils.isYesterday(calendar)) {
      return "Вчера";
    } else {
      simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
      return simpleDateFormat.format(calendar.getTime());
    }
  }
  
  public static String getFileName(String fullFileName) {
    int dotIndex = fullFileName.lastIndexOf('.');
    return fullFileName.substring(0, dotIndex);
  }
}
