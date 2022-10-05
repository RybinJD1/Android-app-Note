package com.mycoding.noteapp.feature_note.domain.use_case

import com.google.common.truth.Truth.assertThat
import com.mycoding.noteapp.feature_note.date.repository.FakeNoteRepository
import com.mycoding.noteapp.feature_note.domain.model.InvalidNoteException
import com.mycoding.noteapp.feature_note.domain.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class AddNoteTest {

    private lateinit var addNote: AddNote
    private lateinit var fakeNoteRepository: FakeNoteRepository

    @Before
    fun setUp() {
        fakeNoteRepository = FakeNoteRepository()
        addNote = AddNote(fakeNoteRepository)
    }

    @Test
    fun `Insert note with empty title`() = runBlocking {
        val exception = assertFailsWith(
            exceptionClass = InvalidNoteException::class,
            block = {
                addNote(
                    Note(
                        title = "",
                        content = "content",
                        timestamp = 1,
                        color = 0
                    )
                )
            }
        )
        assertThat(exception.message).isEqualTo("The title of the note can't be empty")
    }

    @Test
    fun `Insert note with empty content`() = runBlocking {
        val exception = assertFailsWith(
            exceptionClass = InvalidNoteException::class,
            block = {
                addNote(
                    Note(
                        title = "title",
                        content = "",
                        timestamp = 1,
                        color = 0
                    )
                )
            }
        )
        assertThat(exception.message).isEqualTo("The content of the note can't be empty")
    }
}