package com.codingbot.news.core.utils

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.net.URL

suspend fun downloadAndSaveImage(context: Context, imageUrl: String, fileName: String) {

    val storageDir = File(context.filesDir, "tmpImage").apply {
        if (!exists()) mkdirs() // tmpImage 폴더가 없다면 생성
    }
    val imageFile = File(storageDir, fileName)

    try {
        URL(imageUrl).openStream().use { input ->
            FileOutputStream(imageFile).use { output ->
                input.copyTo(output)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun findLocalImageFile(context: Context, imageUrl: String): File? {
    val imageFileName = imageUrl.substringAfterLast('/')
    val storageDir = File(context.filesDir, "tmpImage")
    val imageFile = File(storageDir, imageFileName)
    return if (imageFile.exists()) imageFile else null
}

fun deleteAllImagesInTmpImage(context: Context) {
    val storageDir = File(context.filesDir, "tmpImage")
    if (storageDir.exists()) {
        storageDir.listFiles()?.forEach { if (it.isFile) it.delete() }
    }
}

suspend fun deleteImagesInTmpImageByName(context: Context, fileName: String) {
    val storageDir = File(context.filesDir, "tmpImage")
    if (storageDir.exists()) {
        storageDir.listFiles()?.forEach {
            if (it.isFile && it.name == fileName) {
                it.delete()
            }
        }
    }
}