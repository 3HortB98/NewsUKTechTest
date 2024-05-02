package com.example.newsuktechtest

import com.example.newsuktechtest.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Inject

@ExperimentalCoroutinesApi
class TestDispatcherProvider @Inject constructor() : DispatcherProvider {

    private val dispatcher = TestCoroutineDispatcher()

    override fun default(): CoroutineDispatcher {
        return dispatcher
    }

    override fun io(): CoroutineDispatcher {
        return dispatcher
    }

    override fun main(): CoroutineDispatcher {
        return dispatcher
    }
}

