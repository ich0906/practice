package com.ich.forstudy.test

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CollectionText(

){
    var cnt by remember { mutableStateOf(2) }
    var list by remember{ mutableStateOf(listOf<Int>()) }
    
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item{
            Button(
                onClick = {
                    val newList = list.toMutableList().also { lst ->
                        lst.add(cnt++)
                    }
                    list = newList
                }) {
                Text(text = "button")
            }
        }
        
        items(list.size){ idx ->
            Text(text = list[idx].toString())
        }
    }
}