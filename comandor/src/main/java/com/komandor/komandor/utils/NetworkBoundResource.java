package com.komandor.komandor.utils;

import com.komandor.komandor.data.api.model.ResponseModel;

import java.util.concurrent.TimeUnit;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType, RequestType> {
  
  private final Flowable<Resource<ResultType>> result;
  
  public NetworkBoundResource() {
    Flowable<ResultType> diskObservable = Flowable.defer(() ->
        loadData()
            .subscribeOn(Schedulers.computation())
            .doOnError(this::onFetchFailed)
    );
    
    Flowable<ResultType> networkObservable = Flowable.defer(() ->
        createCall()
            .subscribeOn(Schedulers.io())
            .doOnNext((request) -> {
              if (request.isSuccessful()) {
                saveCallResult(processResponse(request));
              }
            })
            .flatMap(r -> loadData())
            .observeOn(Schedulers.computation())
            .doOnError(this::onFetchFailed)
    );
    
    if (shouldFetch()) {
      result = networkObservable
          .debounce(400, TimeUnit.MILLISECONDS)
          .map(Resource::success)
          .onErrorReturn(throwable -> Resource.error(throwable.getMessage(), null))
          .observeOn(AndroidSchedulers.mainThread())
          .startWith(Resource.loading(null));
    } else {
      result = diskObservable
          .debounce(400, TimeUnit.MILLISECONDS)
          .map(Resource::success)
          .onErrorReturn(throwable -> Resource.error(throwable.getMessage(), null))
          .observeOn(AndroidSchedulers.mainThread())
          .startWith(Resource.loading(null));
    }
  
  }
  
  protected void onFetchFailed(Throwable t) {
    new KomandorException(t).printStackTrace();
  }
  
  public Flowable<Resource<ResultType>> asFlowable() {
    return result;
  }
  
  @WorkerThread
  protected RequestType processResponse(ResponseModel<RequestType> response) {
    return response.getData();
  }
  
  protected Flowable<ResponseModel<RequestType>> checkError(ResponseModel<RequestType> resp) {
    if(!resp.isSuccessful()) {
      throw new KomandorException(resp.getError());
    }
  
    return Flowable.just(resp);
  }
  
  @WorkerThread
  protected abstract void saveCallResult(@NonNull RequestType item);
  
  @MainThread
  protected abstract boolean shouldFetch();
  
  @NonNull
  @MainThread
  protected abstract Flowable<ResultType> loadData();
  
  @NonNull
  @MainThread
  protected abstract Flowable<ResponseModel<RequestType>> createCall();
}
