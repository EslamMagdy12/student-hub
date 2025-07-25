package org.example

data class Student(
    var name: String,
    var gpa: Double ,
    var status: String? = null,
    var notes: MutableList<String>? = null
) {
    val id: Int = generateId()
    var grade: String? = calculateGrade(gpa)

    companion object {
        private var id = 0
        fun generateId(): Int {
            return ++id
        }
        fun calculateGrade(gpa: Double): String? {
            when {
                gpa >= 3.5 -> return "A"
                gpa >= 3 -> return "B"
                gpa >= 2.5 -> return "C"
                gpa >= 2 -> return "D"
                gpa == 0.0 -> return "Undefined"
                else -> return "F"
            }
        }
    }
}
