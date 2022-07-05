package com.komandor.komandor.ui.viewmodel

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.viewModelScope
import com.atom.ui.viewmodel.BaseViewState
import com.komandor.komandor.cryptopro.CryproProManager
import com.komandor.komandor.data.database.KomandorDatabase
import com.komandor.komandor.data.database.model.LocalCertificate
import com.komandor.komandor.ui.base.BaseViewModel
import com.komandor.komandor.utils.FileUtil
import com.komandor.komandor.utils.UnzipUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import javax.inject.Inject

@HiltViewModel
class StartInfoViewModel @Inject constructor(
    val cryproProManager: CryproProManager,
    val db: KomandorDatabase

) : BaseViewModel(BaseViewState(isLoading = false)) {

    fun checkContainer() = cryproProManager.checkContainer()

    fun testCertificate():Boolean {
       return !cryproProManager.getLocalCertificates().isNullOrEmpty()
    }



    fun copyContainerAsZip(uri: Uri, context: Context, contentResolver: ContentResolver) {
        Timber.d(" uri = ${uri}")
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val fileSrc: File = FileUtil.from(context, uri)
            //val fileSrc = uri.toFile()//File(fileSrcPath)
            val fileSrcPath = fileSrc.absolutePath//getPath(requireContext(),uri)
            //Timber.d("fileSrcPath = ${fileSrcPath} ${fileSrc.exists()}")
            if (fileSrc.exists()) {
                Timber.d("fileSrc name = ${fileSrc.name} extension = ${fileSrc.extension}")
                val appFiles = context.filesDir
                val destFile = File(appFiles.absolutePath, "containers")
                //Timber.d("destPath= $destFile")
                val destDir =
                    UnzipUtils.unzip2(context, fileSrcPath ?: "", destFile.absolutePath)
                //Timber.d("destDir= ${destDir.absolutePath}")
                val destFolder = File(
                    destDir,
                    fileSrc.name.replace(".${fileSrc.extension}", "")
                )

                val containerDirectory = DocumentFile.fromFile(destFolder)
                Timber.d("containerDirectory= ${containerDirectory} name = ${containerDirectory.name}")

                copyContainerFromDir(
                    containerDirectory, contentResolver,
                    StringBuilder()
                )
                // Удаление временной папки
                // TODO проверить баг
                //destFolder.deleteRecursively()
                emitComplete(true)
            } else {
                Timber.d("${fileSrcPath} не найден!")
                emitException(Exception("${fileSrcPath} не найден!"))
            }
        }
    }

    fun copyContainerFromDir(
        documentFile: DocumentFile,
        contentResolver: ContentResolver,
        dstPath: StringBuilder
    ) {
        Timber.d(" documentFile = ${documentFile.name}")
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHanlder) {
            emitProgress()
            val r =
                cryproProManager.installContainersfromFile(documentFile, contentResolver, dstPath)
            emitComplete(r)
        }
    }

    /*
    fun copyContainer(context: Context, documentFile : DocumentFile, contentResolver: ContentResolver, dstPath:StringBuilder){
        val cspTool = CSPTool(context)
        Timber.d("cspTool.appInfrastructure.keysDirectory = ${cspTool.appInfrastructure.keysDirectory}")
        Timber.d("cspTool.appInfrastructure.usersDirectory = ${cspTool.appInfrastructure.usersDirectory}")
        cspTool.appInfrastructure.copyContainerFromArchive(
            R.raw.keys)
        var certificates: Map<String, X509Certificate> = CryptoUtils.getLocalCertificates()
        Timber.d("this.certificates = " + certificates)


        val copied = cspTool.appInfrastructure
            .copyContainerFromDirectory(
                documentFile,
                contentResolver, dstPath
            )
        Timber.d("copied= $copied")

    }

     */
}