package com.komandor.komandor.extension

import androidx.documentfile.provider.DocumentFile
import timber.log.Timber
import java.io.*

fun DocumentFile.listFiles(){
    val srcContainer = this.listFiles()

    Timber.d("srcContainer= ${srcContainer.size} ")
    val count = srcContainer.size
    for (i in 0..count - 1) {
        Timber.d("i = $i srcContainer= ${srcContainer[i].name} ")

    }
}


/*
       val testFile = File(destPath,"test")
        val savedFile = saveTextToFile("test", testFile.absolutePath)
        Timber.d("testFile= $testFile savedFile = ${savedFile.absolutePath} ${savedFile.exists()}")
        val txt = readTextFile(savedFile.absolutePath)
        Timber.d("txt= $txt")
 */


fun String.readTextFile(): String {
    val fileEvents = File(this)
    val text = java.lang.StringBuilder()
    try {
        val br = BufferedReader(FileReader(fileEvents))
        var line: String?
        while (br.readLine().also { line = it } != null) {
            text.append(line)
            text.append('\n')
        }
        br.close()
    } catch (e: IOException) {
    }
    return text.toString()
}

fun String.saveTextToFile(txt: String?): File {
    val file = File(this)
    try {
        val fOut = FileOutputStream(file)
        val outputStreamWriter = OutputStreamWriter(fOut)
        outputStreamWriter.append(txt)
        outputStreamWriter.close()
        /*
                    os = new FileOutputStream(file);
                    os.write(txt.getBytes());
                    os.close();
         */fOut.flush()
        fOut.close()
    } catch (e: Exception) {
        Timber.d("e = " + e.message)
        //e.printStackTrace();
    }
    return file
}
