package com.gwp.advertisement

import android.app.Activity
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun Activity.initializeAds(
    onComplete: () -> Unit = {},
) = CoroutineScope(Dispatchers.IO).launch {
    MobileAds.initialize(this@initializeAds) {
        onComplete()
    }
}
