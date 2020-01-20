package com.example.jeff.noteskotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes_table")
data class Notes(
    val name: String?,
    val description: String?,
    val completed: Boolean = false,
    val dateEdited: String = Calendar.getInstance().time.toString()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}