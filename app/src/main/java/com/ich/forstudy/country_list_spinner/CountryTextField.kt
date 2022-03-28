package com.ich.forstudy.country_list_spinner

import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun CountryTextField(
    label: String = "",
    isError: Boolean = false,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
    expanded: Boolean = false,
    selectedCountry: Country? = null,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(),
    onExpandChange: () -> Unit
){
    OutlinedTextField(
        value = if(selectedCountry == null) "" else "${selectedCountry.dialCode} ${selectedCountry.name}",
        onValueChange = {},
        modifier = modifier
    )
}