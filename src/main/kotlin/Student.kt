package org.example

data class Student(
    val id: Int,
    var name: String,
    var gpa: Double? = null,
    var grade: String? = null,
    var status: String? = null,
    var notes: MutableList<String>? = null
)
