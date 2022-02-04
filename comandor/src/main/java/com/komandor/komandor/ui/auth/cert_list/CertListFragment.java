package com.komandor.komandor.ui.auth.cert_list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.komandor.komandor.App;
import com.komandor.komandor.R;
import com.komandor.komandor.data.temporary.CryptoStorage;
import com.komandor.komandor.ui.auth.AuthActivity;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CertListFragment extends Fragment {
  
  CertListViewModel certListViewModel;
  
  private RecyclerView recyclerView;
  private CertListAdapter adapter;
  
  private OnChooseCertificateListener onChooseCertificateListener;
  
  @Inject
  CryptoStorage cryptoStorage;
  
  private CertListAdapter.OnCertificateItemClickListener onCertificateItemClickListener = alias -> {
    cryptoStorage.setSelectedAlias(alias);
    onChooseCertificateListener.onChooseCertificate();
  };
  
  public static CertListFragment newInstance() {
    return new CertListFragment();
  }
  
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fr_certificate_list, container, false);
  }
  
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    recyclerView = view.findViewById(R.id.rv_cert_list_certificates_list);
  }
  
  @Override
  public void onAttach(@NonNull Context context) {
    App.getAppComponent().inject(this);
    super.onAttach(context);
    
    if(context instanceof OnChooseCertificateListener) {
      onChooseCertificateListener = (OnChooseCertificateListener) context;
    }
    
    certListViewModel = ViewModelProviders.of(this).get(CertListViewModel.class);
    adapter = new CertListAdapter(certListViewModel.getCertificates(), onCertificateItemClickListener);
  }
  
  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    
    if(getActivity() != null) {
      getActivity().setTitle(R.string.certListTitle);
      ((AuthActivity) getActivity()).showHomeButton(false);
    }
  
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    recyclerView.setAdapter(adapter);
  }
  
  @Override
  public void onDetach() {
    super.onDetach();
    onChooseCertificateListener = null;
  }
  
  public interface OnChooseCertificateListener {
    void onChooseCertificate();
  }
}
