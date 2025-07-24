package org.example

fun main() {
    while (true) {
        println(
            """
            |Welcome to the Student Management System
            |Please select an action:
            |1. Add Student
            |2. View All Students
            |3. Filter Students by Grade
            |4. Filter Students by Status
            |5. Filter Students by Name
            |6. Update Student
            |7. Remove Student
            |8. Exit
        """.trimMargin()
        )
        val input = readLine()?.toInt()
        when (input) {
            1 -> TODO()
            2 -> TODO()
            3 -> TODO()
            4 -> TODO()
            5 -> TODO()
            6 -> TODO()
            7 -> TODO()
            8 -> TODO()
            else -> TODO()
        }
    }
}

fun doAction(action: Action) {
    when (action) {
        is Action.AddStudent -> TODO()
        is Action.ViewAllStudents -> TODO()
        is Action.FilterByGrade -> TODO()
        is Action.FilterByStatus -> TODO()
        is Action.FilterByName -> TODO()
        is Action.UpdateStudent -> TODO()
        is Action.RemoveStudent -> TODO()
    }
}
