package com.example.domain.model

sealed class MovieSection(val name: String) {
    object Billboard : MovieSection("billboard")
    object Popular: MovieSection("popular")
}