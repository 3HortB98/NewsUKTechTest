package com.example.newsuktechtest


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsuktechtest.data.NetworkClient
import com.example.newsuktechtest.data.models.Coins
import com.example.newsuktechtest.ui.coins.CoinsUiState
import com.example.newsuktechtest.ui.coins.CoinsViewModel
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NetworkTest {
    private val mockGetRepository:NetworkClient = mockk(relaxed = true)
    private lateinit var vm: CoinsViewModel
    private lateinit var mockCoins: Coins
    private val expectedResponseData = Coins(emptyList())
    private val dispatcher = StandardTestDispatcher()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mockKRule = MockKRule(this)

    @ExperimentalCoroutinesApi
    @Before
    fun setup(){
        Dispatchers.setMain(dispatcher)
        mockCoins = expectedResponseData
        coEvery { mockGetRepository.getCoins() } returns Result.success(mockCoins)
        vm = CoinsViewModel(mockGetRepository,TestDispatcherProvider())
    }

    @After
    fun close(){
        Dispatchers.resetMain()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test for getting data and return is successful`() = runTest{

        var stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Loading,stateValue)
        backgroundScope.launch{
            vm.getData()
        }
        dispatcher.scheduler.advanceUntilIdle()
        stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Success(expectedResponseData),stateValue)

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test for getting data and return is failure`() = runTest {
        var stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Loading,stateValue)
        coEvery { mockGetRepository.getCoins() } returns Result.failure(Exception("Network Error"))
        backgroundScope.launch{
            vm.getData()
        }
        dispatcher.scheduler.advanceUntilIdle()
        stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Error(Result.failure<Exception>(Exception("Network Error")).toString()),stateValue)
    }
}
