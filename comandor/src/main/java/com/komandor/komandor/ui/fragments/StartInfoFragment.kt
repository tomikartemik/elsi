package com.komandor.komandor.ui.fragments

import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.OpenDocumentTree
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.viewModels
import com.komandor.komandor.R
import com.komandor.komandor.databinding.FrStartInfoBinding
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.IPermission
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.StartInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class StartInfoFragment : BaseFragment(), IPermission {
    override val layoutId: Int = R.layout.fr_start_info

    private val viewModel by viewModels<StartInfoViewModel>()
    private val binding by viewBinding<FrStartInfoBinding>()

    override val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                //selectFolder.launch(Uri.parse("*"))
                getDocument.launch("*/*")

            } else {
                Timber.d(" доступ запрещен")
                showWarning("Доступ запрещен, Разрешить")
                // TODO повторный запрос
            }
        }

    private val selectFolder = registerForActivityResult(
        OpenDocumentTree()
    ) { uri: Uri? ->
        if (uri != null) {
            var documentFile: DocumentFile? = DocumentFile.fromTreeUri(requireActivity(), uri)
            documentFile?.let {
                viewModel.copyContainerFromDir(it, requireActivity().contentResolver, StringBuilder())
            }
        } // if
    }

    private val getDocument =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                viewModel.copyContainerAsZip(it, requireContext(), requireActivity().contentResolver)
            }
        }

    override fun initUI() {
        initStateWatcher(viewModel.state)

        binding.btnLoadSertificateAsZip.setOnClickListener {
            if (!checkPermissions(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                onRequestPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                getDocument.launch("*/*")
            }
        }

        binding.btnLoadSertificateAsFolder.setOnClickListener {
            if (!checkPermissions(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                onRequestPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            } else {
                selectFolder.launch(Uri.parse("*"))
            }
        }
    }

    override fun afterError(exception: Throwable) {
        toScreen(R.id.startInfoFragment)
    }

    override fun onComplete(data: Any?) {
        if (data!=null && data is Boolean){
            val isCertificate = viewModel.testCertificate()
            Timber.d("checkContainer= ${isCertificate} ${viewModel.cryproProManager.currentPrivateKey}")
            //val args = Bundle();
            //args.putString("key", viewModel.cryproProManager.)
            toScreen(if (data && isCertificate) R.id.certificateValidationFragment else R.id.startInfoFragment )
        }
    }

    override fun back() {
        requireActivity().finish()
    }
}