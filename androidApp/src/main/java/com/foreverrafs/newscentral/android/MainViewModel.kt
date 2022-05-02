package com.foreverrafs.newscentral.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.foreverrafs.newscentral.NewsCentral
import com.foreverrafs.newscentral.Result
import com.foreverrafs.newscentral.create
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val newsCentral: NewsCentral = NewsCentral.create()) : ViewModel() {

    private var resultData: MutableStateFlow<Result?> = MutableStateFlow(null)
    val result: StateFlow<Result?> get() = resultData.asStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() = viewModelScope.launch(Dispatchers.IO) {
        resultData.value = Result.Loading

        try {
            resultData.value = newsCentral.fetchNews(page = 1)
        } catch (e: Exception) {
            resultData.value = Result.Error(e)
        }
    }

}