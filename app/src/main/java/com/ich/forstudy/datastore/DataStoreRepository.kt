package com.ich.forstudy.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class DataStoreRepository(private val context: Context) {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "login")

    suspend fun setLoginInfo(id: String, pw: String){
        context.dataStore.edit { pref ->
            pref[Keys.ID_KEY] = id
            pref[Keys.PW_KEY] = pw
        }
    }

    suspend fun getLoginInfo(): Pair<String,String> {
        val id = context.dataStore.data.map{ it[Keys.ID_KEY] ?: "" }.first()
        val pw = context.dataStore.data.map{ it[Keys.PW_KEY] ?: "" }.first()

        return Pair(id,pw)
    }
}

//val context = LocalContext.current
//            val scope = rememberCoroutineScope()
//            var id by remember { mutableStateOf("") }
//            var pw by remember { mutableStateOf("") }
//            val repository = remember(context){ DataStoreRepository(context) }
//
//            Column(Modifier.padding(16.dp)) {
//                OutlinedTextField(
//                    modifier = Modifier.fillMaxWidth(),
//                    value = id,
//                    onValueChange = {id = it}
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                OutlinedTextField(
//                    modifier = Modifier.fillMaxWidth(),
//                    value = pw,
//                    onValueChange = {pw = it}
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Button(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = {
//                        scope.launch {
//                            repository.setLoginInfo(id,pw)
//                        }
//                    }
//                ) {
//                    Text(text = "Save")
//                }
//                Spacer(modifier = Modifier.height(8.dp))
//
//                Button(
//                    modifier = Modifier.fillMaxWidth(),
//                    onClick = {
//                        scope.launch {
//                            val info = repository.getLoginInfo()
//
//                            id = info.first
//                            pw = info.second
//                        }
//                    }
//                ) {
//                    Text(text = "Load")
//                }
//            }