package com.sample.credentialmanager.ui.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.sample.credentialmanager.R

@Composable
fun HomeScreen(
    profilePictureUrl: String,
    name: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SubcomposeAsyncImage(
            model = profilePictureUrl,
            contentDescription = "profilePicture",
            modifier = Modifier.size(120.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        ) {
            when (painter.state) {
                AsyncImagePainter.State.Empty -> {}
                is AsyncImagePainter.State.Error -> {}
                is AsyncImagePainter.State.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp), color = Color.Gray)
                }
                is AsyncImagePainter.State.Success -> SubcomposeAsyncImageContent()
            }
        }

        Spacer(Modifier.height(16.dp))

        Text(stringResource(R.string.name, name))

        Spacer(Modifier.height(16.dp))

        Box(Modifier.background(Color.Blue).clip(RoundedCornerShape(16.dp)).padding(6.dp), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.logout))
        }
    }
}
