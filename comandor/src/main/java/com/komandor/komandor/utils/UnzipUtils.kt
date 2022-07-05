package com.komandor.komandor.utils

import android.content.Context
import androidx.core.content.ContextCompat
import timber.log.Timber
import java.io.*
import java.util.zip.ZipFile


/**
 * UnzipUtils class extracts files and sub-directories of a standard zip file to
 * a destination directory.
 *
 */
object UnzipUtils {
    /**
     * @param zipFilePath
     * @param destDirectory
     * @throws IOException
     */
    @Throws(IOException::class)
    fun unzip2(context: Context, zipFilePath: String, destDirectory: String):File {


        //File root = context.getExternalFilesDir(null);
        //val externalStorageVolumes = ContextCompat.getExternalFilesDirs(context, null)

        //var root: File? = externalStorageVolumes[0]
        //Timber.d("root = ${root?.absolutePath} destDirectory = ${destDirectory}")

        val destDir =  File( destDirectory).apply {
            if (!exists()) {
                this.mkdir()
            }
        }
        //Timber.d("destDir = $destDir  ${destDir.exists()}")

        ZipFile(zipFilePath).use { zip ->

            zip.entries().asSequence().forEach { entry ->

                zip.getInputStream(entry).use { input ->


                    val filePath = File(destDir, entry.name).absolutePath
                    //Timber.d("entry.name = ${entry.name} filePath =  $filePath  ${entry.isDirectory}")

                    if (!entry.isDirectory) {
                        // if the entry is a file, extracts it
                        extractFile(input, filePath)
                    } else {
                        // if the entry is a directory, make the directory
                        val dir = File(filePath)
                        //Timber.d("dir = " + dir.absolutePath + " " + dir.exists())

                        val r = dir.mkdir()
                        //Timber.d("r = $r dir = " + dir.absolutePath + " " + dir.exists())
                    }

                }

            }
        }
        return  destDir
    }

    /**
     * Extracts a zip entry (file entry)
     * @param inputStream
     * @param destFilePath
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun extractFile(inputStream: InputStream, destFilePath: String) {
        //Timber.d("destFilePath =  $destFilePath")


        val bos = BufferedOutputStream(FileOutputStream(destFilePath, false))
        val bytesIn = ByteArray(BUFFER_SIZE)
        var read: Int
        while (inputStream.read(bytesIn).also { read = it } != -1) {
            bos.write(bytesIn, 0, read)
        }
        bos.close()
        val destFile = File(destFilePath)
        //Timber.d("destFile  = ${destFile.absolutePath}  ${destFile.exists()}")

    }

    /**
     * Size of the buffer to read/write data
     */
    private const val BUFFER_SIZE = 4096

}