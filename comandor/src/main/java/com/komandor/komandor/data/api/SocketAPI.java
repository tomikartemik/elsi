package com.komandor.komandor.data.api;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.komandor.komandor.BuildConfig;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.response.MessageResponseModel;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.database.messages.Message;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.model.EncryptedFile;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.KomandorException;
import com.komandor.komandor.utils.SystemUtils;

import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.socket.client.IO;
import io.socket.client.Socket;
import okhttp3.OkHttpClient;

public class SocketAPI {
  private Socket socket = null;
  private List<String> connectedChats = new ArrayList<>();
  private Disposable disposable;

  private Context appContext;
  private ChatsStorage chatsStorage;
  private MessagesStorage messagesStorage;
  private FileStorage fileStorage;
  private TemporaryStorage temporaryStorage;


  public SocketAPI(
      Context appContext,
      ChatsStorage chatsStorage,
      MessagesStorage messagesStorage,
      FileStorage fileStorage,
      TemporaryStorage temporaryStorage
  ) {
    this.appContext = appContext;
    this.chatsStorage = chatsStorage;
    this.messagesStorage = messagesStorage;
    this.fileStorage = fileStorage;
    this.temporaryStorage = temporaryStorage;
  }

  public void init() {
    if (socket != null) {
      return;
    }

    OkHttpClient okHttpClient = initSocketOkHttpClient();

    IO.setDefaultOkHttpWebSocketFactory(okHttpClient);
    IO.setDefaultOkHttpCallFactory(okHttpClient);

    IO.Options opts = new IO.Options();
    opts.reconnection = true;
    opts.callFactory = okHttpClient;
    opts.webSocketFactory = okHttpClient;

    try {
      socket = IO.socket(BuildConfig.API_URL);

      socket.on(Socket.EVENT_CONNECT, args -> {
        socket.emit("session", temporaryStorage.getToken());

        disposable = chatsStorage.getChats()
            .flatMap(chats ->
                Flowable.fromIterable(chats)
                    .flatMap(chat -> Flowable.just(chat.getChatID()))
            )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doFinally(() -> disposable.dispose())
            .subscribe(this::subscribeChat);

      }).on(Socket.EVENT_CONNECT_ERROR, args -> {
        new KomandorException("SOCKETS", "Can not connect to sockets").printStackTrace();

        if (!SystemUtils.hasNetworkConnection(appContext)) {
          Log.w("SOCKETS", "No network connection");
        }

      }).on("message", args -> {
        Gson gson = new Gson();
        MessageResponseModel message = gson.fromJson(args[0].toString(), MessageResponseModel.class);
        long localID = messagesStorage.insert(message);

        chatsStorage.setLastMessageID(message.getChatID(), localID);

        if (message.getPid() != temporaryStorage.getUserID()) {
          chatsStorage.increaseUnreadMessages(message.getChatID());
        }
      }).on(Socket.EVENT_DISCONNECT, args -> {
        connectedChats = new ArrayList<>();
      }).on(Socket.EVENT_RECONNECT, args -> {
        Log.i("SOCKETS", "SOCKETS RECONNECTED");
      }).on(Socket.EVENT_RECONNECTING, args -> {
        Log.i("SOCKETS", "SOCKETS RECONNECTING");
      });

      socket.connect();
    } catch (Exception e) {
      if (!(e instanceof KomandorException)) {
        e = new KomandorException(e);
      }

      e.printStackTrace();
    }
  }

  public void sendMessage(long localID, Message message) {
    try {
      JSONObject obj = new JSONObject();
      obj.put("token", temporaryStorage.getToken());
      obj.put("chat", message.getChatID());
      obj.put("text", message.getData());
      obj.put("type", "");
      obj.put("sign", message.getSign());
      obj.put("cid", message.getCid());
      obj.put("localId", Long.toString(localID));

      socket.emit("sendMessage", new Object[]{obj}, args -> {
        Gson gson = new Gson();
        ResponseModel<MessageResponseModel> response = gson.fromJson(args[0].toString(), new TypeToken<ResponseModel<MessageResponseModel>>() {
        }.getType());

        if (!response.isSuccessful()) {
          new KomandorException(response.getError()).printStackTrace();
          return;
        }

        messagesStorage.updateMessage(response.getData());
      });
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }
  }

  public void sendMessageWithFile(String chatID, long localID, long localFileID, EncryptedFile encryptedFile) {
    try {
      JSONObject obj = new JSONObject();
      obj.put("token", temporaryStorage.getToken());
      obj.put("chat", chatID);
      obj.put("text", encryptedFile.getEncryptedName());
      obj.put("sign", encryptedFile.getNameSign());
      obj.put("data", encryptedFile.getEncryptedData());
      obj.put("cms", encryptedFile.getCms());
      obj.put("type", "file");
      obj.put("cid", encryptedFile.getCid());
      obj.put("localId", Long.toString(localID));

      socket.emit("sendMessage", new Object[]{obj}, args -> {
        Gson gson = new Gson();
        ResponseModel<MessageResponseModel> response = gson.fromJson(args[0].toString(), new TypeToken<ResponseModel<MessageResponseModel>>() {
        }.getType());

        if (!response.isSuccessful()) {
          new KomandorException(response.getError()).printStackTrace();
          return;
        }

        messagesStorage.updateMessage(response.getData());
        fileStorage.updateFileID(localFileID, response.getData().getFileID());
      });
    } catch (Exception e) {
      new KomandorException(e).printStackTrace();
    }
  }

  public void subscribeChat(String chatID) {
    if (connectedChats.indexOf(chatID) == -1) {
      socket.emit("subscribe", chatID);
      connectedChats.add(chatID);
    }
  }

  public void unsubscribeChat(String chatID) {
    if (connectedChats.indexOf(chatID) != -1) {
      socket.emit("unsubscribe", chatID);
      connectedChats.remove(chatID);
    }
  }

  private OkHttpClient initSocketOkHttpClient() throws KomandorException {
    try {
      TrustManager[] trustManagers = new TrustManager[]{new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
          return new java.security.cert.X509Certificate[]{};
        }

        @SuppressLint("TrustAllX509TrustManager")
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
      }};


      SSLContext sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, trustManagers, null);


      return new OkHttpClient.Builder()
          .hostnameVerifier((hostname, session) -> BuildConfig.HOST_NAME.equals(hostname))
          .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustManagers[0])
          .build();
    } catch (KeyManagementException | NoSuchAlgorithmException e) {
      throw new KomandorException(e);
    }
  }


}
