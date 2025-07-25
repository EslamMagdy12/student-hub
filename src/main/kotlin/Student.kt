package org.example

data class Student(
    var name: String,
    var gpa: Double? = null,
    var grade: String? = null,
    var status: String? = null,
    var notes: MutableList<String>? = null
) {
    val id: Int = generateId()

    companion object {
        private var id = 0
        fun generateId(): Int {
            return ++id
        }
    }
}
