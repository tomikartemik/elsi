package com.komandor.komandor.di;

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.komandor.komandor.BuildConfig;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.database.chats.ChatsStorage;
import com.komandor.komandor.data.database.files.FileStorage;
import com.komandor.komandor.data.database.messages.MessagesStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;

import java.security.cert.X509Certificate;

import javax.inject.Singleton;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
  
  
  @Provides
  @Singleton
  OkHttpClient provideClient() {
    
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
    
    SSLContext sslContext = null;
    try {
      sslContext = SSLContext.getInstance("TLS");
      sslContext.init(null, trustManagers, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .sslSocketFactory
                    (sslContext
                            .getSocketFactory(),
                            (X509TrustManager)
                                    trustManagers[0]);
//        .addInterceptor(provideOfflineCacheInterceptor())
//        .addNetworkInterceptor(provideCacheInterceptor())
//        .cache(cache)
//         .addNetworkInterceptor(new StethoInterceptor())
    return builder.build();
  }
  
  @Provides
  @Singleton
  Gson provideGson() {
    return new Gson();
  }
  
  @Provides
  @Singleton
  Retrofit provideRetrofit(Gson gson, OkHttpClient client) {
    return new Retrofit
            .Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
  }
  
  @Provides
  @Singleton
  KomandorAPI provideApiService(Retrofit retrofit) {
    return retrofit.create(KomandorAPI.class);
  }
  
  @Provides
  @Singleton
  SocketAPI provideSocketApi(Context context, ChatsStorage chatsStorage, MessagesStorage messagesStorage, FileStorage fileStorage, TemporaryStorage temporaryStorage) {
    return new SocketAPI(context, chatsStorage, messagesStorage, fileStorage, temporaryStorage);
  }
}
