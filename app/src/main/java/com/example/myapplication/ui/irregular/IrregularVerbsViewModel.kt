package com.example.myapplication.ui.irregular

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.IrregularVerb
import org.json.JSONArray

class IrregularVerbsViewModel(application: Application) : AndroidViewModel(application) {

    private val _verbs = MutableLiveData<List<IrregularVerb>>()
    val verbs: LiveData<List<IrregularVerb>> = _verbs

    init {
        loadVerbsFromAssets()
    }

    private fun loadVerbsFromAssets() {
        val context = getApplication<Application>().applicationContext
        val json = context.assets.open("irregular_verbs.json")
            .bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(json)
        val verbsList = mutableListOf<IrregularVerb>()
        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            verbsList.add(
                IrregularVerb(
                    obj.getString("infinitive"),
                    obj.getString("simplePast"),
                    obj.getString("pastParticiple"),
                    obj.getString("translation")
                )
            )
        }
        _verbs.value = verbsList
    }
}