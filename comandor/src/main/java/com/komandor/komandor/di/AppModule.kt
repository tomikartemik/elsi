package com.komandor.komandor.di

import android.annotation.SuppressLint
import android.content.Context
import com.atom.data.remote.FlowCallAdapterFactory
import com.google.gson.Gson
import com.komandor.komandor.BuildConfig
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.api.ExceptionMapper
import com.komandor.komandor.data.api.KomandorAPI
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.buildAppDatabase
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideClient(
        @ApplicationContext context: Context, temporaryStorage: TemporaryStorage
    ): OkHttpClient {
        val trustManagers = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        })


        var sslContext: SSLContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustManagers, null)


        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d("OkHttp >> $message")
            }
        })
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .sslSocketFactory(
                sslContext?.socketFactory,
                trustManagers[0] as X509TrustManager
            )
            .addInterceptor(logging)
            .addInterceptor(ChuckInterceptor(context))

            .addInterceptor(Interceptor { chain: Interceptor.Chain ->
                val original = chain.request()
                val request = original.newBuilder()
                Timber.d("temporaryStorage.token = ${temporaryStorage.token}")
                val token = temporaryStorage.token

                token?.let {
                    request.header("Authorization", "Bearer ${it}")
                }
                //request.header("Content-Type", "application/json")
                //request.header("Accept", "application/json")
                chain.proceed(request.build())

            })
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
        exceptionMapper: ExceptionMapper
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(client)
            .addCallAdapterFactory(FlowCallAdapterFactory.create(exceptionMapper))
            //.addCallAdapterFactory(FlowCallAdapterFactory.create())

            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): KomandorAPI {
        return retrofit.create(KomandorAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideTemporaryStorage(): TemporaryStorage = TemporaryStorage()


    @Provides
    @Singleton
    fun provideCryproProManages(@ApplicationContext appContext: Context): CryproProManager =
        CryproProManager(appContext)

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): KomandorDatabase {
        return buildAppDatabase(appContext)
    }

    /*
        @Provides
    @Singleton
    fun contactsStorage(db: KomandorDatabase): ContactsStorage =
        ContactsStorage(db)


    @Provides
    @Singleton
    fun fileStorage(db: KomandorDatabase): FileStorage =
        FileStorage(db)


            @Provides
    @Singleton
    fun messageStorage(db: KomandorDatabase, fileStorage:FileStorage): MessagesStorage =
        MessagesStorage(db, fileStorage)
     */
}