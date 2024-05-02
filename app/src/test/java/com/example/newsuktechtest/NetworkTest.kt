package com.example.newsuktechtest


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsuktechtest.data.NetworkClient
import com.example.newsuktechtest.data.models.Coin
import com.example.newsuktechtest.data.models.CoinDetails
import com.example.newsuktechtest.data.models.Coins
import com.example.newsuktechtest.data.models.Whitepaper
import com.example.newsuktechtest.ui.coins.CoinDetailsUiState
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
    private lateinit var mockCoinDetails: Coin
    private val coinID = "coin"

    private val expectedResponseDataDetails = Coin(
        name = "Coin",
        type = "token",
        id = coinID,
        coinDetails = CoinDetails(
            symbol = "C",
            logo = "https://static.coinpaprika.com/coin/btc-bitcoin/logo.png",
            isActive = true,
            openSource = false,
            description = "some test of some sort. some more words to make it a description",
            developmentStatus = "working product",
            startedAt = "2009-01-03T00:00:00Z",
            whitepaper = Whitepaper(
                link = "link",
                thumbnail = "thumbnail"
            )
        )
    )
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
        mockCoinDetails = expectedResponseDataDetails
        coEvery { mockGetRepository.getCoins() } returns Result.success(mockCoins)
        coEvery { mockGetRepository.getCoinDetail(coinID) } returns Result.success(mockCoinDetails)
        vm = CoinsViewModel(mockGetRepository,TestDispatcherProvider())

    }

    @After
    fun close(){
        Dispatchers.resetMain()
    }

    @Test
    fun `test for getting data and return is successful`() = runTest{


        launch{
            vm.getData()
        }
        dispatcher.scheduler.advanceUntilIdle()
        val stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Success(expectedResponseData),stateValue)

    }


    @Test
    fun `test for getting data and return is failure`() = runTest {

        coEvery { mockGetRepository.getCoins() } returns Result.failure(Exception("Network Error"))
        launch{
            vm.getData()
        }
        dispatcher.scheduler.advanceUntilIdle()
        val stateValue = vm.uiState.value
        assertEquals(CoinsUiState.Error(Result.failure<Exception>(Exception("Network Error")).toString()),stateValue)
    }

    @Test
       fun `test for getting details of coin and return is successful`() = runTest{

           val startStateValue = vm.uiDetailsState.value
           assertEquals(CoinDetailsUiState.Hidden,startStateValue)
           launch{
               vm.getCoinDetail(coinID)
           }
           dispatcher.scheduler.advanceUntilIdle()
           val stateValue = vm.uiDetailsState.value
           assertEquals(CoinDetailsUiState.Success(expectedResponseDataDetails),stateValue)

       }

    @Test
    fun `test for getting details of coin and return is failure`() = runTest {
        val startStateValue = vm.uiDetailsState.value
        assertEquals(CoinDetailsUiState.Hidden,startStateValue)
        coEvery { mockGetRepository.getCoinDetail(coinID) } returns Result.failure(Exception("Network Error"))
        launch{
            vm.getCoinDetail(coinID)
        }
        dispatcher.scheduler.advanceUntilIdle()
        val stateValue = vm.uiDetailsState.value
        assertEquals(CoinDetailsUiState.Error(Result.failure<Exception>(Exception("Network Error")).toString()),stateValue)
    }
}
