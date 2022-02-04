package com.komandor.komandor.ui.main.profile;

import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.GetUserRequesModel;
import com.komandor.komandor.data.api.model.response.CertificateResponseModel;
import com.komandor.komandor.data.api.model.response.UserResponseModel;
import com.komandor.komandor.data.database.KomandorDatabase;
import com.komandor.komandor.data.database.certificates.Certificate;
import com.komandor.komandor.data.database.certificates.CertificateDao;
import com.komandor.komandor.data.database.users.User;
import com.komandor.komandor.data.database.users.UserDao;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.utils.Resource;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import io.reactivex.Single;
import androidx.annotation.NonNull;
import io.reactivex.BackpressureStrategy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ProfileRepository {
  
  KomandorDatabase komandorDatabase;
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  CryptoStorage cryptoStorage;
  UserDao userDao;
  CertificateDao certificateDao;
  
  @Inject
  public ProfileRepository(
      KomandorDatabase komandorDatabase,
      KomandorAPI komandorAPI,
      TemporaryStorage temporaryStorage,
      CryptoStorage cryptoStorage,
      UserDao userDao,
      CertificateDao certificateDao
  ) {
    this.komandorDatabase = komandorDatabase;
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
    this.cryptoStorage = cryptoStorage;
    this.userDao = userDao;
    this.certificateDao = certificateDao;
  }
  
  
  public Flowable<Resource<User>> getProfile() {
    return new NetworkBoundResource<User, UserResponseModel>() {
      Disposable disposable;
      
      @Override
      protected void saveCallResult(@NonNull UserResponseModel item) {
        temporaryStorage.setUserID(item.getPid());
        cryptoStorage.setUserCID(item.getCid());
        
        
        userDao.insert(new User(item));
        List<CertificateResponseModel> certificates = item.getCertificates();
        
        for (CertificateResponseModel cert : certificates) {
          certificateDao.insert(new Certificate(item.getPid(), cert, true, item.getCid() == cert.getCertID()));
        }
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<User> loadData() {
        return userDao.getActiveUser().toFlowable();
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<UserResponseModel>> createCall() {
        return komandorAPI.getProfile(new GetUserRequesModel(temporaryStorage.getToken()))
            .flatMap(this::checkError)
            .flatMap(userResponse ->
                Flowable.create(emitter -> {
                  disposable = userDao.getActiveUser()
                      .subscribeOn(Schedulers.io())
                      .observeOn(Schedulers.io())
                      .doFinally(() -> {
                        emitter.onNext(userResponse);
                        emitter.onComplete();
                        
                        if (disposable != null) {
                          disposable.dispose();
                        }
                      })
                      .subscribe(user -> {
                        if (user.getPid() != userResponse.getData().getPid()) {
                          komandorDatabase.clearDB();
                        }
                      });
                }, BackpressureStrategy.LATEST));
      }
      
    }.asFlowable();
  }
  
  public Single<Boolean> logOut() {
    return Single
        .fromCallable(() -> {
          temporaryStorage.clearStorage();
          cryptoStorage.clearStorage();
          return true;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
  
}
