package com.gwp.advertisement.banner

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class BannerAdTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun bannerAd_whenIsVisibleIsTrue_displaysAd() {
        val isVisible = mutableStateOf(true)

        composeTestRule.setContent {
            BannerAd(
                isVisible = isVisible,
                type = BannerType.INLINE_ADAPTIVE
            )
        }

        composeTestRule
            .onNodeWithTag("banner_view")
            .assertExists()
    }

    @Test
    fun bannerAd_whenIsVisibleIsFalse_hidesAd() {
        val isVisible = mutableStateOf(false)

        composeTestRule.setContent {
            BannerAd(
                isVisible = isVisible,
                type = BannerType.INLINE_ADAPTIVE
            )
        }

        composeTestRule
            .onNodeWithTag("banner_view")
            .assertDoesNotExist()
    }

    @Test
    fun bannerAd_visibilityChanges_updatesDisplay() {
        val isVisible = mutableStateOf(false)

        composeTestRule.setContent {
            BannerAd(
                isVisible = isVisible,
                type = BannerType.INLINE_ADAPTIVE
            )
        }

        // Initially not displayed
        composeTestRule
            .onNodeWithTag("banner_view")
            .assertDoesNotExist()

        // Change visibility to true
        isVisible.value = true

        // Now it should be displayed
        composeTestRule
            .onNodeWithTag("banner_view")
            .assertExists()

        // Change visibility back to false
        isVisible.value = false

        // Should not be displayed again
        composeTestRule
            .onNodeWithTag("banner_view")
            .assertDoesNotExist()
    }
}
