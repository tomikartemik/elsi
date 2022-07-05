package com.komandor.komandor.ui.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.komandor.komandor.R
import com.komandor.komandor.ui.model.ListCertificateItem
import com.komandor.komandor.databinding.LiCertificateBinding
import com.komandor.komandor.ui.base.BaseAdapter

class CertificateAdapter(
    dataList: List<ListCertificateItem>,
    val onClick: (certificate: ListCertificateItem, position: Int) -> Unit,
) : BaseAdapter<ListCertificateItem, CertificateViewHolder>() {
    val TAG = CertificateAdapter::class.java.simpleName


    init {
        //L.d(TAG, "menuListItems = " +menuListItems)
        setList(dataList)
    }

    override fun onBindViewHolder(holder: CertificateViewHolder, position: Int) {
        val data = dataList[position]

        when (holder) {
            is CertificateViewHolder -> {
                holder.onBind(data, onClick)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size // one more to add header row
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.li_certificate
    }
    override fun createViewHolder(
        ctx: Context,
        parent: ViewGroup?,
        viewType: Int
    ): CertificateViewHolder {
        val binding = LiCertificateBinding
            .inflate(LayoutInflater.from(ctx), parent, false)
        return CertificateViewHolder(binding)
        //return OrderViewHolder(view)
    }
}