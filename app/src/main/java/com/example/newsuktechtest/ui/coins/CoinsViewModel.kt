package com.example.newsuktechtest.ui.coins

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsuktechtest.data.NetworkClient
import com.example.newsuktechtest.utils.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val networkClient: NetworkClient,
    private val dispatcherProvider: DispatcherProvider
):ViewModel(){

    private val _uiState = MutableStateFlow<CoinsUiState>(CoinsUiState.Loading)
    val uiState: StateFlow<CoinsUiState> = _uiState

    private val _uiDetailsState = MutableStateFlow<CoinDetailsUiState>(CoinDetailsUiState.Hidden)
    val uiDetailsState: StateFlow<CoinDetailsUiState> =_uiDetailsState

    init {
        getData()
    }

    fun getData(){
        _uiState.value = CoinsUiState.Loading
        viewModelScope.launch {
            val result = networkClient.getCoins()
            if(result.isSuccess){
                val resultData = result.getOrNull()
                Timber.d("Coins: ",resultData)
                _uiState.value = CoinsUiState.Success(resultData)
            }else{
                _uiState.value = CoinsUiState.Error(result.toString())

            }
        }
    }

    fun getCoinDetail(id: String){
        _uiDetailsState.value = CoinDetailsUiState.Loading
        viewModelScope.launch(dispatcherProvider.main()) {
            val result = networkClient.getCoinDetail(id)
            if(result.isSuccess){
                val resultData = result.getOrNull()
                Timber.i("CoinDetails: ", resultData)
                _uiDetailsState.value = CoinDetailsUiState.Success(resultData)
            }else{
                _uiDetailsState.value = CoinDetailsUiState.Error(result.toString())
            }
        }
    }

    fun closeDetails(){
        _uiDetailsState.value = CoinDetailsUiState.Hidden
    }
}
