package com.komandor.komandor.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.komandor.komandor.ui.model.ListCertificateItem
import com.komandor.komandor.databinding.LiCertificateBinding

class CertificateViewHolder(val binding: LiCertificateBinding) : RecyclerView.ViewHolder(binding.root) {
    private val TAG = CertificateViewHolder::class.java.simpleName

    fun onBind(certificate: ListCertificateItem,
               onClick: (certificate: ListCertificateItem, position: Int) -> Unit,
    ) {
            //L.d(TAG, "order = ${order}")

            binding.tvClientName.text = certificate.clientName// compnay name
            binding.tvCompanyName.text = certificate.clientCompany// client name

            itemView.setOnClickListener(View.OnClickListener {
                onClick(certificate, absoluteAdapterPosition)
            })

    }

}