package com.komandor.komandor.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.komandor.komandor.R
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.ui.model.ListCertificateItem
import com.komandor.komandor.databinding.FrCertificateValidationBinding
import com.komandor.komandor.ui.adapter.CertificateAdapter
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.CertificateValidationViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CertificateValidationFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fr_certificate_validation

    private val viewModel by viewModels<CertificateValidationViewModel>()
    private val binding by viewBinding<FrCertificateValidationBinding>()
    private var certificateAdapter: CertificateAdapter? = null

    override fun initUI() {
        initStateWatcher(viewModel.state)
        viewModel.isCurrentCertificate()
        //viewModel.initLocalCertificates()
    }

    override fun afterError(exception: Throwable) {
        toScreen(R.id.startInfoFragment)
    }

    override fun onComplete(data: Any?) {
        if (data != null) {
            if (data is LocalCertificate) {
                toScreen(R.id.chatsFragment)

            } else if (data is List<*>) {
                if (data.isNotEmpty()) {
                    if (data.size == 1) {
                        toAuth((data as List<ListCertificateItem>).first())
                    } else {
                        binding.tvCertListEmpty.visibility = View.GONE
                        binding.rvList.visibility = View.VISIBLE
                        initAdapter(dataList = data as List<ListCertificateItem>)
                    }
                } else {
                    binding.rvList.visibility = View.GONE
                    binding.tvCertListEmpty.visibility = View.VISIBLE
                }
            }
        } else {
            viewModel.initLocalCertificates()
        }
    }

    private fun initAdapter(dataList: List<ListCertificateItem>) {
        certificateAdapter = CertificateAdapter(
            dataList,
            onClick = { coupon, position -> onItemClick(coupon, position) },
        )

        with(binding.rvList) {
            layoutManager = LinearLayoutManager(
                context, RecyclerView.VERTICAL, false
            )
            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                RecyclerView.VERTICAL
            )

            dividerItemDecoration.setDrawable(
                resources.getDrawable(
                    R.drawable.divider_drawable,
                    null
                )
            )
            //addItemDecoration(HItemDecoration(16))
            addItemDecoration(dividerItemDecoration)
            setHasFixedSize(true)
            adapter = certificateAdapter
        }
    }

    private fun onItemClick(listCertificateItem: ListCertificateItem, position: Int) {
        toAuth(listCertificateItem)
    }

    private fun toAuth(listCertificateItem: ListCertificateItem) {
        Timber.d("listCertificateItem = ${listCertificateItem.clientName}")
        //currentCertificate = listCertificateItem
        val args = Bundle()
        args.putString("key", listCertificateItem.alias)

        toScreen(R.id.authFragment, args)
    }

    override fun back() {
        viewModel.deleteCertificate(requireContext())
        super.back()
    }
}