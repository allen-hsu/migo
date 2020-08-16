package com.allen.migo.ext

import java.lang.ref.WeakReference

fun <T> T.weakSelf(): WeakReference<T> {
    return WeakReference(this)
}