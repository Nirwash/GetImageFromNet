package com.nirwashh.android.getimagefromnet

import android.graphics.Bitmap

interface ImageCallback {
    fun success(bitmap: Bitmap)

    fun failed()
}