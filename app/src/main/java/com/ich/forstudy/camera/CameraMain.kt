package com.ich.forstudy.camera

import android.Manifest
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import java.io.File

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraMain(
    getDirectory: () -> File
){
    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            val permissionState = rememberPermissionState(
                permission = Manifest.permission.CAMERA
            )

            Button(
                onClick = {
                    permissionState.launchPermissionRequest()
                }
            ) {
                Text(text = "Permission")
            }
            Spacer(modifier = Modifier.height(30.dp))

            CameraOpen(getDirectory())
        }
    }
}