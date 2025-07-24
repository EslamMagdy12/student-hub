package org.example

data class Student(
    val id: Int,
    val name: String,
    val gpa: Double? = null,
    val grade: String? = null,
    val status: String? = null,
    val notes: MutableList<String>? = null
)
