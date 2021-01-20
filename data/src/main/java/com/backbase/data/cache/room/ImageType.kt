package com.backbase.data.cache.room

internal sealed class ImageType(val id: Int) {
    object Poster : ImageType(1)
    object Backdrop : ImageType(2)
}