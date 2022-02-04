package com.komandor.komandor.ui.auth.cert_validation;

import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.api.model.response.AuthResponseModel;
import com.komandor.komandor.data.api.model.response.ConfirmAuthResponseModel;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.ui.main.profile.ProfileRepository;
import com.komandor.komandor.utils.KomandorException;
import com.komandor.komandor.utils.Resource;
import com.komandor.komandor.utils.SystemUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import androidx.annotation.NonNull;
import io.reactivex.BackpressureStrategy;
import io.reactivex.disposables.Disposable;

@Singleton
public class CertValidationRepository {
  
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  CryptoStorage cryptoStorage;
  ProfileRepository profileRepository;
  SocketAPI socketAPI;
  
  private ConfirmAuthResponseModel confirmAuthResponseModel;
  
  @Inject
  public CertValidationRepository(KomandorAPI komandorAPI, TemporaryStorage temporaryStorage, CryptoStorage cryptoStorage, ProfileRepository profileRepository, SocketAPI socketAPI) {
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
    this.cryptoStorage = cryptoStorage;
    this.profileRepository = profileRepository;
    this.socketAPI = socketAPI;
  }
  
  public Flowable<Resource<ConfirmAuthResponseModel>> startAuthentication(String cert, String sign) {
    return new NetworkBoundResource<ConfirmAuthResponseModel, ConfirmAuthResponseModel>() {
      private Disposable disposable;
      
      @Override
      protected void saveCallResult(@NonNull ConfirmAuthResponseModel item) {
        confirmAuthResponseModel = item;
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<ConfirmAuthResponseModel> loadData() {
        if (confirmAuthResponseModel == null) {
          return Flowable.just(new ConfirmAuthResponseModel());
        }
        return Flowable.just(confirmAuthResponseModel);
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<ConfirmAuthResponseModel>> createCall() {
        return komandorAPI.authentication(cert, sign)
            .flatMap(resp -> {
              if (!resp.isSuccessful()) {
                throw new KomandorException(resp.getError());
              }
              
              return Flowable.just(resp);
            })
            .flatMap(resp -> {
              AuthResponseModel body = resp.getData();
              
              String token = body.getToken();
              temporaryStorage.setToken(token);
              
              String nonceSign = cryptoStorage.sign(SystemUtils.decodeBase64(body.getNonce()));
              
              return komandorAPI.confirmAuthentication(token, nonceSign)
                  .flatMap(this::checkError);
            })
            .flatMap(response -> {
              if(response.getData().needRegisterPhone()) {
                return Flowable.just(response);
              }
              
              return Flowable.create(emitter -> {
                disposable = profileRepository
                        .getProfile()
                        .doFinally(() -> {
                          socketAPI.init();
                      
                      emitter.onNext(response);
                      emitter.onComplete();
                      
                      if(disposable != null) {
                        disposable.dispose();
                      }
                    })
                    .subscribe();
              }, BackpressureStrategy.LATEST);
            });
      }
    }.asFlowable();
  }
  
}
