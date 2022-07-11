package com.jacobchapman.employeedirectory.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.jacobchapman.employeedirectory.models.EmployeeViewData

@Composable
fun EmployeeComponent(viewData: EmployeeViewData) {
    Card(modifier = Modifier.fillMaxWidth(), elevation = 3.dp) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .diskCacheKey(viewData.id)
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .data(viewData.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = viewData.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .requiredHeight(50.dp)
                        .requiredWidth(50.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(start = 10.dp),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        viewData.team,
                        fontSize = 12.sp
                    )
                    Text(
                        viewData.name,
                        color = viewData.nameColor,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}