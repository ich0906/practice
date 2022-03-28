package com.ich.forstudy.dropdown

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun DropdownFilter(

){
    Column(
        Modifier.fillMaxWidth()
    ) {
        var expanded by remember { mutableStateOf(false) }
        val suggestions = listOf("Item1", "Item2", "Item3")
        var curItem by remember { mutableStateOf(suggestions[0]) }

        Box(
            modifier = Modifier.align(Alignment.End)
        ){
            TextButton(
                modifier = Modifier.padding(top = 8.dp, end = 8.dp),
                onClick = { expanded = !expanded }
            ){
                Icon(
                    imageVector = if (expanded)
                        Icons.Filled.KeyboardArrowUp
                    else
                        Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                )
                Text (curItem)
            }
            DropdownMenu(
                modifier = Modifier.width(85.dp),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                suggestions.forEach { label ->
                    DropdownMenuItem(
                        onClick = {
                            expanded = false
                            curItem = label
                        }) {
                        Text(text = label)
                    }
                }
            }
        }
    }
}