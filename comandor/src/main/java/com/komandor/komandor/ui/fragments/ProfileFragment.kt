package com.komandor.komandor.ui.fragments

import android.Manifest
import android.graphics.Bitmap
import android.net.Uri
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import com.komandor.komandor.BuildConfig
import com.komandor.komandor.R
import com.komandor.komandor.app.Constants
import com.komandor.komandor.data.database.model.User
import com.komandor.komandor.databinding.FrProfileBinding
import com.komandor.komandor.extension.load
import com.komandor.komandor.extension.toBitmap
import com.komandor.komandor.ui.base.BaseFragment
import com.komandor.komandor.ui.base.IPermission
import com.komandor.komandor.ui.base.viewBinding
import com.komandor.komandor.ui.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.io.File

@AndroidEntryPoint
class ProfileFragment : BaseFragment(), IPermission {
    override val layoutId: Int = R.layout.fr_profile

    private val viewModel by viewModels<ProfileViewModel>()
    private val binding by viewBinding<FrProfileBinding>()

    private var uriTempDocument: Uri? = null

    override val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                showPictureDialog()
            } else {
                Timber.d(" доступ запрещен")
                showWarning("Доступ запрещен, Разрешить")
                // TODO повторный запрос
            }
        }

    private val getCameraImage =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Timber.d("Got image at: $uriTempDocument")
                uriTempDocument?.let {
                    updatePhoto(it)
                }
            }
        }

    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                Timber.d("Got image at: $uri")
                uri?.let {
                    updatePhoto(it)
                }
            }
        }


    override fun initUI() {
        initStateWatcher(viewModel.state)
        viewModel.getProfile()
    }

    private fun fillUserProfile(user: User) {
        user.photo?.let {
            binding.ivProfileAvatar.load(it)
        }

        binding.profileName.text = user.name
        binding.profileSNILS.text = user.snils
        if (user.type == Constants.LP) {
            binding.profileCompany.text = user.company
            binding.profileCompanyLayout.visibility = View.VISIBLE
        } else {
            binding.profileCompanyLayout.visibility = View.GONE
        }
        if (user.type == Constants.NP) {
            binding.profileCompanyLayout.visibility = View.GONE
        } else {
            binding.profileOGRN.text = user.orgn
            binding.profileCompanyLayout.visibility = View.VISIBLE
        }

        binding.profileINN.text = user.inn
        binding.profilePhone.text = user.phone
        binding.profileEmail.text = user.email

        binding.btnProfileLogOut.setOnClickListener {
            viewModel.logout()
            toScreen(R.id.startInfoFragment)
        }

        binding.btnProfileLoadPhoto.setOnClickListener {
            if (!checkPermissions(requireContext(), Manifest.permission.CAMERA)) {
                onRequestPermission(requireActivity(), Manifest.permission.CAMERA)
            } else {
                showPictureDialog()
            }
        }
    }

    override fun onComplete(data: Any?) {
        Timber.d("data = $data")
        if (data != null) {
            if (data is User) {
                fillUserProfile(data)
            } else if (data is Bitmap) {
                binding.ivProfileAvatar.setImageBitmap(data)
            }
        }
    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(requireContext())
        pictureDialog.setTitle("Выберите изображение для вашего профиля")

        //val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        val options = arrayOf<CharSequence>("Сделать фото", "Выбрать из галереи", "Cancel")
        pictureDialog.setItems(
            options
        ) { dialog, which ->
            when (which) {
                0 -> {
                    dialog.dismiss()
                    uriTempDocument = FileProvider.getUriForFile(
                        requireContext(),
                        "${BuildConfig.APPLICATION_ID}.fileprovider",
                        File(requireContext().cacheDir, "avatar.jpg")
                    )
                    Timber.d("uriTempDocument = ${uriTempDocument}")
                    getCameraImage.launch(uriTempDocument)
                }
                1 -> {
                    selectImageFromGalleryResult.launch("image/*")
                }
                2 -> dialog.dismiss()
            }
        }
        pictureDialog.show()
    }

    private fun updatePhoto(uri: Uri) {
        val bm = uri.toBitmap(requireContext())
        bm?.let {
            viewModel.updatePhoto(it)
        }
    }

}