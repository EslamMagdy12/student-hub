package org.example

val students: MutableList<Student> = mutableListOf()

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
            |8. Extract Students Data to a csv File
            |9. Exit
        """.trimMargin()
        )
        val input = readLine()?.toInt()
        when (input) {
            1 -> {
                val student = getStudentDetails()
                doAction(Action.AddStudent(student))
            }

            2 -> TODO()
            3 -> TODO()
            4 -> TODO()
            5 -> TODO()
            6 -> {
                val id = readLine()?.toIntOrNull()
                if (id != null) {
                    doAction(Action.UpdateStudent(id))
                } else {
                    println("Invalid ID. Please enter a valid integer.")
                }
            }

            7 -> TODO()
            8 -> {
                extractData(students)
            }
            else -> TODO()
        }
    }
}

fun doAction(action: Action) {
    when (action) {
        is Action.AddStudent -> {
            val student = action.student
            if (students.any { it.id == student.id }) {
                println("Student with ID ${student.id} already exists.")
            } else {
                students.add(student)
                println("Student ${student.name} added successfully.")
            }
        }

        is Action.ViewAllStudents -> TODO()
        is Action.FilterByGrade -> TODO()
        is Action.FilterByStatus -> TODO()
        is Action.FilterByName -> TODO()
        is Action.UpdateStudent -> {
            val student = students.find { it.id == action.id }
            if (student != null) {
                updateStudent(action.id)
            } else {
                println("Student with ID ${action.id} not found.")
            }
        }

        is Action.RemoveStudent -> TODO()
    }
}
