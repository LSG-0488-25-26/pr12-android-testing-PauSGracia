package com.example.android_studio_test_exercice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.state.ToggleableState
import com.example.android_studio_test_exercice.viewmodel.MainViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun checkInitialState() {
        assertEquals(true, viewModel.estatSwitch.value)
        assertEquals(false, viewModel.esVegetaria.value)
        assertEquals(false, viewModel.esVega.value)
        assertEquals(true, viewModel.esCarnivor.value)
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
        assertEquals("Messi", viewModel.selectedOption.value)
        assertEquals(0.0f, viewModel.sliderValue.value)
        assertEquals("", viewModel.searchText.value)
        assertEquals(false, viewModel.toggleState.value)
    }

    @Test
    fun checkToggleEstatSwitch() {
        assertEquals(true, viewModel.estatSwitch.value)

        viewModel.toggleEstatSwitch()
        assertEquals(false, viewModel.estatSwitch.value)

        viewModel.toggleEstatSwitch()
        assertEquals(true, viewModel.estatSwitch.value)
    }

    @Test
    fun checkSetSearchText() {
        viewModel.setSearchText("Buscador de text")
        assertEquals("Buscador de text", viewModel.searchText.value)
    }

    @Test
    fun checkPerformSearch() {
        viewModel.setSearchText("Buscar dades")
        assertEquals("Buscar dades", viewModel.searchText.value)

        viewModel.performSearch()
        assertEquals("", viewModel.searchText.value)
    }

    @Test
    fun checkToggleTriStateStatus() {
        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Indeterminate, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.On, viewModel.triStateStatus.value)

        viewModel.toggleTriStateStatus()
        assertEquals(ToggleableState.Off, viewModel.triStateStatus.value)
    }

    fun checkCheckboxLogic() {
        assertEquals(true, viewModel.esCarnivor.value)
        assertEquals(false, viewModel.esVegetaria.value)

        viewModel.toggleEsVegetaria()

        assertEquals(true, viewModel.esVegetaria.value)
    }

    @Test
    fun checkRadioButtonSelectionLogic() {
        assertEquals("Messi", viewModel.selectedOption.value)
        viewModel.setSelectedOption("Lamine Yamal")
        assertEquals("Lamine Yamal", viewModel.selectedOption.value)
        viewModel.setSelectedOption("Raphina")
        assertEquals("Raphina", viewModel.selectedOption.value)
    }

    @Test
    fun checkSliderVolumeLogic() {
        assertEquals(0.0f, viewModel.sliderValue.value)

        viewModel.setSliderValue(75.5f)
        assertEquals(75.5f, viewModel.sliderValue.value)
    }

    @Test
    fun checkDropdownMenuFlowLogic() {
        assertEquals(false, viewModel.expanded.value)
        assertEquals("", viewModel.selectedItem.value)

        viewModel.setExpanded(true)
        assertEquals(true, viewModel.expanded.value)

        viewModel.setSelectedItem("Opció B")
        viewModel.setExpanded(false)

        assertEquals(false, viewModel.expanded.value)
        assertEquals("Opció B", viewModel.selectedItem.value)
    }
}