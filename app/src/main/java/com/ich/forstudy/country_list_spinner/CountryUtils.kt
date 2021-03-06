package com.ich.forstudy.country_list_spinner

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

fun countryList(context: Context): MutableList<Country>{
    val json = getJsonFromAsset(context = context, "countries.json")
    val type = object: TypeToken<Country>(){}.type
    return Gson().fromJson(json, type)
}

fun getJsonFromAsset(
    context: Context,
    fileName: String
): String? {
    val jsonString: String
    try{
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }catch (e: IOException){
        e.printStackTrace()
        return null
    }

    return jsonString
}

fun localeToEmoji(
    countryCode: String
): String {
    val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
    val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
    return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
}