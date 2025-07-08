package com.gwp.advertisement.banner

import android.os.Bundle

private const val COLLAPSIBLE_BANNER = "collapsible"
private const val COLLAPSIBLE_BANNER_TOP = "top"
private const val COLLAPSIBLE_BANNER_BOTTOM = "bottom"

enum class BannerType(
    val extras: Bundle,
) {
    INLINE_ADAPTIVE(
        extras = Bundle(),
    ),
    COLLAPSIBLE_TOP(
        extras = Bundle().apply { putString(COLLAPSIBLE_BANNER, COLLAPSIBLE_BANNER_TOP) },
    ),
    COLLAPSIBLE_BOTTOM(
        extras = Bundle().apply { putString(COLLAPSIBLE_BANNER, COLLAPSIBLE_BANNER_BOTTOM) },
    ),
}
