package com.komandor.komandor.ui.auth.registration;

import com.komandor.komandor.utils.NetworkBoundResource;
import com.komandor.komandor.data.api.KomandorAPI;
import com.komandor.komandor.data.api.SocketAPI;
import com.komandor.komandor.data.api.model.ResponseModel;
import com.komandor.komandor.data.api.model.request.ConfirmPhoneRequestModel;
import com.komandor.komandor.data.api.model.response.ConfirmPhoneResponseModel;
import com.komandor.komandor.data.api.model.response.RegistryPhoneResponseModel;
import com.komandor.komandor.data.temporary.TemporaryStorage;
import com.komandor.komandor.ui.main.profile.ProfileRepository;
import com.komandor.komandor.utils.Resource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import androidx.annotation.NonNull;
import io.reactivex.BackpressureStrategy;

import io.reactivex.disposables.Disposable;

@Singleton
public class RegistrationRepository {
  
  KomandorAPI komandorAPI;
  TemporaryStorage temporaryStorage;
  ProfileRepository profileRepository;
  SocketAPI socketAPI;
  
  private RegistryPhoneResponseModel registryPhoneResponse;
  private ConfirmPhoneResponseModel confirmPhoneResponse;
  
  @Inject
  public RegistrationRepository(KomandorAPI komandorAPI, TemporaryStorage temporaryStorage, ProfileRepository profileRepository, SocketAPI socketAPI) {
    this.komandorAPI = komandorAPI;
    this.temporaryStorage = temporaryStorage;
    this.profileRepository = profileRepository;
    this.socketAPI = socketAPI;
  }
  
  public Flowable<Resource<RegistryPhoneResponseModel>> registryPhone(String phone) {
    return new NetworkBoundResource<RegistryPhoneResponseModel, RegistryPhoneResponseModel>() {
      
      @Override
      protected void saveCallResult(@NonNull RegistryPhoneResponseModel item) {
        registryPhoneResponse = item;
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<RegistryPhoneResponseModel> loadData() {
        if (registryPhoneResponse == null) {
          return Flowable.just(new RegistryPhoneResponseModel());
        }
        return Flowable.just(registryPhoneResponse);
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<RegistryPhoneResponseModel>> createCall() {
        return komandorAPI.registryPhone(temporaryStorage.getToken(), phone);
      }
    }.asFlowable();
  }
  
  public Flowable<Resource<ConfirmPhoneResponseModel>> confirmPhone(String code) {
    return new NetworkBoundResource<ConfirmPhoneResponseModel, ConfirmPhoneResponseModel>() {
      private Disposable disposable;
      
      @Override
      protected void saveCallResult(@NonNull ConfirmPhoneResponseModel item) {
        confirmPhoneResponse = item;
      }
      
      @Override
      protected boolean shouldFetch() {
        return true;
      }
      
      @NonNull
      @Override
      protected Flowable<ConfirmPhoneResponseModel> loadData() {
        if (confirmPhoneResponse == null) {
          return Flowable.just(new ConfirmPhoneResponseModel());
        }
        return Flowable.just(confirmPhoneResponse);
      }
      
      @NonNull
      @Override
      protected Flowable<ResponseModel<ConfirmPhoneResponseModel>> createCall() {
        return komandorAPI
                .confirmPhone
                        (new ConfirmPhoneRequestModel
                                (temporaryStorage
                                        .getToken(),
                                        registryPhoneResponse
                                                .getCodeID(),
                                        code))
            .flatMap(this::checkError)
            .flatMap(response -> Flowable
                    .create(emitter -> { disposable = profileRepository.getProfile()
                      .doFinally(() -> {
                        socketAPI.init();
                        
                        emitter.onNext(response);
                        emitter.onComplete();
                        
                        if (disposable != null) {
                          disposable.dispose();
                        }
                      })
                      .subscribe();
                }, BackpressureStrategy.LATEST)
            );
      }
    }.asFlowable();
  }
}
