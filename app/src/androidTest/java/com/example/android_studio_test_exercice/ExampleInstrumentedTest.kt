package com.example.android_studio_test_exercice

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android_studio_test_exercice.view.MainView
import com.example.android_studio_test_exercice.viewmodel.MainViewModel

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
        composeTestRule.setContent {
            MainView(myViewModel = viewModel)
        }
    }

    @Test
    fun checkInitialComposableValues() {
        composeTestRule.onNodeWithTag("wifi_switch_id").assertIsOn()
        composeTestRule.onNodeWithTag("carnivor_checkbox_id").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextEquals("")
    }

    @Test
    fun checkWifiSwitchClick() {
        composeTestRule.onNodeWithTag("wifi_switch_id").assertIsOn()
        composeTestRule.onNodeWithTag("wifi_switch_id").performClick()
        composeTestRule.onNodeWithTag("wifi_switch_id").assertIsOff()
    }

    @Test
    fun checkOutlinedTextFieldAndSearchButton() {
        composeTestRule.onNodeWithTag("outlinedTextField_id").performTextInput("Buscant text")
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextContains("Buscant text", substring = false)
        composeTestRule.onNodeWithTag("search_button_id").performClick()
        composeTestRule.onNodeWithTag("outlinedTextField_id").assertTextEquals("")
    }

    @Test
    fun checkToggleButtonClick() {
        composeTestRule.onNodeWithTag("toggle_button_id").performScrollTo()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Desactivat")
        composeTestRule.onNodeWithTag("toggle_button_id").performClick()
        composeTestRule.onNodeWithTag("toggle_button_id").assertTextEquals("Activat")
    }

    @Test
    fun checkCheckboxesEnabledAndDisabledStates() {
        composeTestRule.onNodeWithTag("carnivor_checkbox_id").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("vegetaria_checkbox_id").assertIsEnabled()
        composeTestRule.onNodeWithTag("vegetaria_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("vegetaria_checkbox_id").assertIsOn()
    }

    @Test
    fun checkRadioButtonsFlow() {
        composeTestRule.onNodeWithTag("radio_Vinicius_id").assertIsNotEnabled()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal_id").assertIsNotSelected()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal_id").performClick()
        composeTestRule.onNodeWithTag("radio_Lamine_Yamal_id").assertIsSelected()
    }

    @Test
    fun checkSliderInteractionUpdatesText() {
        composeTestRule.onNodeWithTag("slider_id").performScrollTo()
        composeTestRule.onNodeWithText("Volum: 0%").assertExists()
        composeTestRule.onNodeWithTag("slider_id")
            .performSemanticsAction(SemanticsActions.SetProgress) { it(67f) }

        composeTestRule.onNodeWithText("Volum: 67%").assertExists()
    }

    @Test
    fun checkTriStateCheckboxInteraction() {
        composeTestRule.onNodeWithTag("carnivor_checkbox_id").performScrollTo()

        composeTestRule.onNodeWithTag("tristate_checkbox_id").assertExists()

        composeTestRule.onNodeWithTag("tristate_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tristate_checkbox_id").performClick()
        composeTestRule.onNodeWithTag("tristate_checkbox_id").performClick()

        composeTestRule.onNodeWithTag("tristate_checkbox_id").assertIsOff()
    }
}