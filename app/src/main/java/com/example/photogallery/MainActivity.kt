package com.example.photogallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.photogallery.ui.theme.PhotoGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoGalleryTheme {
                PhotoGalleryScreen()
            }
        }
    }
}

@Composable
fun PhotoGalleryScreen(
    modifier: Modifier = Modifier
) {
    var currentPhoto by remember {
        mutableIntStateOf(0)
    }
    var isNextButton by remember {
        mutableStateOf(true)
    }
    var isPreviousButton by remember {
        mutableStateOf(false)
    }

    val photoCollection = arrayOf(
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(30.dp)
            .verticalScroll(rememberScrollState())
    ) {

        when (currentPhoto) {
            0 -> DisplayPhotoInformation(
                drawablePhotoId = R.drawable.img_1,
                photographerName = R.string.img1_photographer_name,
                imageViewerCount = R.string.img1_views,
                totalDownloads = R.string.img1_downloads,
                photoDescription = R.string.img1_description
            )

            1 -> DisplayPhotoInformation(
                drawablePhotoId = R.drawable.img_2,
                photographerName = R.string.img2_photographer_name,
                imageViewerCount = R.string.img2_views,
                totalDownloads = R.string.img2_downloads,
                photoDescription = R.string.img2_description
            )

            2 -> DisplayPhotoInformation(
                drawablePhotoId = R.drawable.img_3,
                photographerName = R.string.img3_photographer_name,
                imageViewerCount = R.string.img3_views,
                totalDownloads = R.string.img3_downloads,
                photoDescription = R.string.img3_description
            )

            3 -> DisplayPhotoInformation(
                drawablePhotoId = R.drawable.img_4,
                photographerName = R.string.img4_photographer_name,
                imageViewerCount = R.string.img4_views,
                totalDownloads = R.string.img4_downloads,
                photoDescription = R.string.img4_description
            )

            4 -> DisplayPhotoInformation(
                drawablePhotoId = R.drawable.img_5,
                photographerName = R.string.img5_photographer_name,
                imageViewerCount = R.string.img5_views,
                totalDownloads = R.string.img5_downloads,
                photoDescription = R.string.img5_description
            )
        }

        Spacer(modifier = modifier.height(30.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {

            when{
                currentPhoto == 0 -> {
                    isPreviousButton = false
                    isNextButton = true
                }

                currentPhoto == photoCollection.size - 1 -> {
                    isPreviousButton = true
                    isNextButton = false
                }

                currentPhoto > 0 && currentPhoto < photoCollection.size - 1 -> {
                    isPreviousButton = true
                    isNextButton = true
                }
            }


            when {
                isPreviousButton -> PreviousButton(
                    onPreviousButtonClick = {
                        currentPhoto--
                    }
                )

            }

            when{
                isNextButton -> NextButton(
                    onNextButtonClick = {
                        currentPhoto++
                    }
                )
            }


        }
    }
}

@Composable
fun DisplayPhotoInformation(
    @DrawableRes drawablePhotoId: Int,
    @StringRes photographerName: Int,
    @StringRes imageViewerCount: Int,
    @StringRes totalDownloads: Int,
    @StringRes photoDescription: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Box {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .border(width = 2.dp, color = Color.Black)
                    .padding(10.dp)
            ) {
                Image(
                    painter = painterResource(id = drawablePhotoId),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1.5f)
                )
            }
        }

        Spacer(modifier = modifier.height(50.dp))

        Box(
            modifier = modifier
                .background(color = Color(236, 235, 244))
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Photo by: ${stringResource(id = photographerName)}",
                    fontSize = 24.sp,
                )

                Spacer(modifier = modifier.height(10.dp))

                Text(
                    text = "Views: ${stringResource(id = imageViewerCount)}",
                    fontSize = 18.sp
                )

                Spacer(modifier = modifier.height(5.dp))

                Text(
                    text = "Downloads: ${stringResource(id = totalDownloads)}",
                    fontSize = 18.sp
                )

                Spacer(modifier = modifier.height(5.dp))

                BasicText(
                    text = "Description: ${stringResource(id = photoDescription)}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 30.sp
                    )
                )
            }
        }
    }
}

@Composable
fun PreviousButton(
    onPreviousButtonClick: () -> Unit,
) {
    Button(onClick = onPreviousButtonClick) {
        Text(text = "Previous")
    }
}

@Composable
fun NextButton(
    onNextButtonClick: () -> Unit,
) {
    Button(onClick = onNextButtonClick) {
        Text(text = "Next")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhotoGalleryTheme {
        PhotoGalleryScreen()
    }
}