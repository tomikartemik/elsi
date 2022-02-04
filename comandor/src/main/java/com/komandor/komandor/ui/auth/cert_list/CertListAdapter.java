package com.komandor.komandor.ui.auth.cert_list;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.komandor.komandor.databinding.CertificateBinding;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CertListAdapter extends RecyclerView.Adapter<CertificateViewHolder> {
  
  private List<X509Certificate> certificates;
  private List<String> aliases;
  private OnCertificateItemClickListener onCertificateItemClickListener;
  
  public CertListAdapter(Map<String, X509Certificate> certificates, OnCertificateItemClickListener onCertificateItemClickListener) {
    this.certificates = new ArrayList<>(certificates.values());
    this.aliases = new ArrayList<>(certificates.keySet());
    
    this.onCertificateItemClickListener = onCertificateItemClickListener;
  }
  
  @NonNull
  @Override
  public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    CertificateBinding binding = CertificateBinding.inflate(inflater, parent, false);
    return new CertificateViewHolder(binding);
  }
  
  @Override
  public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
    holder.bind(aliases.get(position), certificates.get(position), onCertificateItemClickListener);
  }
  
  @Override
  public int getItemCount() {
    return certificates.size();
  }
  
  public interface OnCertificateItemClickListener {
    void onCertificateItemClick(String alias);
  }
  
}
