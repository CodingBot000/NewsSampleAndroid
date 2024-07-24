package com.codingbot.news.ui.component

import android.content.Context
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.codingbot.news.R
import com.codingbot.news.core.utils.deleteImagesInTmpImageByName
import com.codingbot.news.core.utils.downloadAndSaveImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

fun getImageFolder(context: Context) = "${context.filesDir}/tmpImage"

@Composable
fun NetworkAwareAsyncImage(
    isNetworkAvailable: Boolean,
   url: String?,
   title: String,
   fileName: String
) {
    val context = LocalContext.current
    val imageFile = File(getImageFolder(context), fileName)

    val coroutine = rememberCoroutineScope()
    if (isNetworkAvailable) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url)
                .listener(onSuccess = { _, _ ->
                    // 다운로드 및 로컬 저장 로직
                    url ?.let {
                        coroutine.launch(Dispatchers.IO){
                            deleteImagesInTmpImageByName(context, fileName)
                            downloadAndSaveImage(context, url, fileName)
                        }
                    }
                })
                .build(),
            modifier = Modifier
                .width(170.dp)
                .aspectRatio(1f)
                .padding(end = 10.dp),
            contentDescription = "Network_Image_$title",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.load_waiting)
        )
    } else {
        imageFile?.let {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageFile)
                    .build(),
                contentDescription = "Local_Image_$title",
                modifier = Modifier
                    .width(170.dp)
                    .aspectRatio(1f)
                    .padding(end = 10.dp),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.load_waiting)
            )
        }
    }
}


@Composable
fun NetworkAwareAsyncImageForGrid(
    isNetworkAvailable: Boolean,
    url: String?,
    title: String,
    fileName: String
) {
    val context = LocalContext.current
    val imageFile = File(getImageFolder(context), fileName)

    val coroutine = rememberCoroutineScope()
    if (isNetworkAvailable) {
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(url)
                .listener(onSuccess = { _, _ ->
                    url ?.let {
                        coroutine.launch(Dispatchers.IO){
                            deleteImagesInTmpImageByName(context, fileName)
                            downloadAndSaveImage(context, url, fileName)
                        }
                    }
                })
                .build(),
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f),
            contentDescription = "Network_Image_$title",
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.load_waiting)
        )
    } else {
        imageFile?.let {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(imageFile)
                    .build(),
                contentDescription = "Local_Image_$title",
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop,
                error = painterResource(R.drawable.load_waiting)
            )
        }
    }
}

