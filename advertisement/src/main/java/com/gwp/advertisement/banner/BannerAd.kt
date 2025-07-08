package com.gwp.advertisement.banner

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.gwp.advertisement.BuildConfig
import com.gwp.advertisement.R

/**
 * A composable function to display a banner advertisement.
 *
 * @param modifier The modifier to apply to the banner ad.
 * @param isVisible Show or hide banner.
 * @param type Type of the banner [INLINE_ADAPTIVE, COLLAPSIBLE_TOP, COLLAPSIBLE_BOTTOM].
 */
@Composable
fun BannerAd(
    modifier: Modifier = Modifier,
    isVisible: State<Boolean>,
    type: BannerType,
    onAdLoaded: () -> Unit = {},
) {
    val adView = createAdView(type, onAdLoaded)

    // Pause/resume the ad when the lifecycle is paused/resumed.
    LifecycleResumeEffect(adView) {
        adView.resume()
        onPauseOrDispose { adView.pause() }
    }

    // Using another composable to avoid recomposition when visibility changes
    Content(
        modifier = modifier,
        isVisible = isVisible,
        adView = adView,
    )
}

@Composable
private fun Content(
    modifier: Modifier = Modifier,
    isVisible: State<Boolean>,
    adView: AdView,
) {
    AnimatedVisibility(
        visible = isVisible.value,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        if (LocalInspectionMode.current) {
            // Ad load does not work in preview mode because it requires a network connection.
            Text(
                modifier = modifier
                    .border(BorderStroke(1.dp, Color.Gray))
                    .padding(vertical = 16.dp),
                text = stringResource(R.string.banner_preview),
                textAlign = TextAlign.Center,
            )
        } else {
            AndroidView(
                modifier = modifier.wrapContentSize(),
                factory = { adView }
            )
        }
    }
}

@Composable
private fun createAdView(
    type: BannerType,
    onAdLoaded: () -> Unit,
): AdView {
    val context = LocalContext.current

    return AdView(context).apply {
        adUnitId = BuildConfig.AD_UNIT_ID

        val adSize = when (type) {
            BannerType.INLINE_ADAPTIVE -> {
                AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize(context, 320)
            }

            BannerType.COLLAPSIBLE_TOP,
            BannerType.COLLAPSIBLE_BOTTOM -> AdSize.BANNER
        }

        setAdSize(adSize)

        this.adListener = object : AdListener() {
            override fun onAdLoaded() {
                super.onAdLoaded()
                onAdLoaded()
            }
        }

        loadAd(
            AdRequest.Builder()
                .addNetworkExtrasBundle(AdMobAdapter::class.java, type.extras)
                .build()
        )
    }
}
