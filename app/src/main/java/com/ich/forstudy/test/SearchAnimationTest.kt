package com.ich.forstudy.test

import androidx.activity.compose.BackHandler
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SearchAnimationTest() {
    var searchMode by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }

    BackHandler(searchMode) {
        searchMode = false
    }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Login Icon"
            )
        }

        if(!searchMode)
            Spacer(Modifier.weight(1f))

        AnimatedVisibility(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp),
            visible = searchMode,
            enter = scaleIn() + expandHorizontally(),
            exit = scaleOut() + shrinkHorizontally()
        ) {
            CustomTextField(
                value = searchText,
                onValueChange = {searchText = it},
                trailingIcon = {
                    IconButton(
                        modifier = Modifier.offset(x = 10.dp),
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = "",
                            tint = LocalContentColor.current.copy(alpha = 0.5f)
                        )
                    }
                },
                modifier = Modifier
                    .background(
                        Color(0xDDDDDDDD),
                        RoundedCornerShape(10.dp)
                    )
                    .padding(4.dp)
                    .height(36.dp),
                fontSize = 16.sp,
                placeholderText = "재료 추가",
            )
        }

        IconButton(
            onClick = {}
        ) {
            Icon(
                modifier = Modifier.size(36.dp),
                imageVector = Icons.Filled.Notifications,
                contentDescription = "Notification Icon",
                tint = Color.Black
            )
        }
    }

    if(!searchMode) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            TextButton(
                onClick = { searchMode = true }
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )

                Text(
                    text = "이름으로 검색",
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}