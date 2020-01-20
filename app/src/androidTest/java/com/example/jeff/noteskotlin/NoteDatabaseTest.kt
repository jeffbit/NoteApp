package com.example.jeff.noteskotlin

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.example.jeff.noteskotlin.model.NoteDao
import com.example.jeff.noteskotlin.model.NoteDatabase
import com.example.jeff.noteskotlin.model.Notes
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4ClassRunner::class)
class NoteDatabaseTest {

    lateinit var db: NoteDatabase
    private var noteDao: NoteDao? = null

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(context, NoteDatabase::class.java).build()

        noteDao = db.noteDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun enterNoteAndReadNote_returnSize() {
        val note = Notes(
            "Test",
            "description",
            false
        )
        noteDao?.insertNote(note)
        val result = noteDao?.selectNotesDesc()?.getOrAwaitValue()?.size
        Assert.assertEquals(1, result)


    }

    @Test
    fun enterNoteAndReadNote_returnNote() {
        val note = Notes(
            "Test",
            "description",
            false
        )
        noteDao?.insertNote(note)
        val result = noteDao?.selectNotesDesc()?.getOrAwaitValue()
        Assert.assertEquals(note, result?.get(0))
    }


    @Test
    fun enterNote_returnListNoteNull() {
        val note = Notes(
            "Test",
            "description",
            false
        )
        noteDao?.insertNote(note)
        val result = noteDao?.selectNotesDesc()?.getOrAwaitValue()
        Assert.assertNotNull(result)
    }


    @Test
    fun enterNotesAndDeleteNotes_returnEmptyList() {
        val note = Notes(
            "Test",
            "description",
            false
        )
        noteDao?.insertNote(note)
        noteDao?.deleteAllNotes()
        val result = noteDao?.selectNotesDesc()?.getOrAwaitValue()
        val notes: List<Notes> = arrayListOf()
        Assert.assertEquals(notes, result)

    }

    @Test
    fun readEmptyNotes_returnEmptyList() {
        val notes: List<Notes> = arrayListOf()
        Assert.assertEquals(notes, noteDao?.selectNotesDesc()?.getOrAwaitValue())
    }


    fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }

        this.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data as T
    }

}