package com.backbase.assignment.mapper

import com.backbase.domain.model.genre.Genre
import org.junit.Test
import kotlin.test.assertEquals

class GenreMapperTest {
    private val genre = Genre(
        id = 1,
        name = "test"
    )

    @Test
    fun `Given a Genre object, then the mapper function transform it into a valid MovieGenreUIMovel object`() {
        val result = genre.toUIModel()
        assertEquals(genre.id, result.id)
        assertEquals(genre.name, result.name)
    }
}