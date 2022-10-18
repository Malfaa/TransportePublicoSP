package com.malfaa.transportepublicosp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malfaa.transportepublicosp.network.models.VeiculosLocalizados
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class RoomDatabaseTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Use a fake repository to be injected into the viewmodel
    private lateinit var repositorio: FakeDataSource


    @Before
    fun setupReminderViewModel(){
        // We initialise the repository with no tasks
        repositorio = FakeDataSource()

    }

    @Test
    fun salvando_linhas_db_custom() = runTest{
        return@runTest repositorio.refreshLinhas(
            VeiculosLocalizados(
                1,
                1,
                true,
                "ta",
                1.0,
                1.0
            )
        )
    }

    @Test
    fun retornando_valores() = runTest {
        repositorio.refreshLinhas(VeiculosLocalizados(
                1,
                1,
                true,
                "ta",
                1.0,
                1.0
            ))
        assertThat(repositorio.onibus(), `is` (true))
    }
}