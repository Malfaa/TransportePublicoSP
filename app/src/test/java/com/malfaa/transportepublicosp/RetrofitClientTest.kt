package com.malfaa.transportepublicosp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malfaa.transportepublicosp.network.SPTransApi
import com.malfaa.transportepublicosp.network.SPTransApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RetrofitClientTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var instancia: SPTransApiService

    @Before
    fun setup(){
        // We initialise the repository with no tasks by providing the Retrofit instance
        instancia = SPTransApi.retrofitService
    }

    @Test
    fun test_autenticacao_com_server()= runTest{
        //Create a new request for our API calling
        val query = BuildConfig.TOKEN
        //Execute the API call
        val response = instancia.autentica(query)
        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val resultadoResposta = response.body()
        assert(resultadoResposta != null)
        assert(response.code() == 200)
    }

    @Test
    fun return_getLinhas()= runTest{
        //Create a new request for our API calling
        val token = BuildConfig.TOKEN
        //Execute the API call
        val autenticacao = instancia.autentica(token)
        //Check for success body
        val cookie = autenticacao.headers().get("Set-Cookie")
        assert(cookie != null)
        val query = "8000"
        val response = instancia.getLinha(cookie!!,query)
        //Check for success body
        val resultadoResposta = response.size
        assert(resultadoResposta > 0 )

    }
}