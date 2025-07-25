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
            |8. Exit
        """.trimMargin()
        )
        val input = readLine()?.toInt()
        when (input) {
            1 -> doAction(Action.AddStudent)
            2 -> doAction(Action.ViewAllStudents)
            3 -> TODO()
            4 -> TODO()
            5 -> TODO()
            6 -> TODO()
            7 -> TODO()
            8 -> {
                println("Exiting...")
                return
            }
            else -> println("Invalid input. Please try again.")
        }
    }
}

fun doAction(action: Action) {
    when (action) {
        is Action.AddStudent -> {
            val id: Int
            while (true) {
                print("Enter Student ID: ")
                val input = readLine()?.toIntOrNull()
                if (input == null) {
                    println("Please enter a valid ID")
                } else {
                    id = input
                    break
                }
            }

            val name: String
            while (true) {
                print("Enter Student name: ")
                val input = readLine()?.toString() ?: null
                val nameInput = input?.lowercase() ?: null
                if (nameInput == null || nameInput.isEmpty()) {
                    println("Please enter a valid name")
                } else if (nameInput.filter { it >= 'a' && it <= 'z' }.length != nameInput.length) {
                    println("Please enter a valid name")
                } else {
                    name = nameInput
                    break
                }
            }

            val gpa: Double
            while (true) {
                print("Enter Student GPA (for student in first and second semester enter 0.0): ")
                val input = readLine()?.toDoubleOrNull()
                if (input == null || input < 0.0) {
                    println("Please enter a valid GPA")
                } else {
                    gpa = input
                    break
                }
            }

            val grade: String
            val grades = listOf("a", "b", "c", "d", "undefined")
            while (true) {
                println("Enter Student Grade as in (A,B,C,D) & for student in first and second semester enter Undefined : ")
                val input = readLine()?.toString() ?: null
                val gradeInput = input?.lowercase()
                if (gradeInput == null || !grades.contains(gradeInput)) {
                    println("Please enter a valid Grade")
                } else {
                    grade = gradeInput
                    break
                }
            }

            val status: String
            val stats = listOf("active", "inactive")
            while (true) {
                print("Enter Student Status (Active / Inactive) : ")
                val input = readLine()
                val statusInput = input?.lowercase()
                if (statusInput == null || !stats.contains(statusInput)) {
                    println("Please enter a valid Status")
                } else {
                    status = statusInput
                    break
                }
            }

            val notes = mutableListOf<String>()
            while (true) {
                print("Do you want to add notes? (Y/N) : ")
                val answer = readLine()?.lowercase()
                if (answer == "y" || answer == "yes") {
                    val note = readLine()?.lowercase() ?: ""
                    notes.add(note)
                    break
                } else {
                    if (answer == "n" || answer == "no") {
                        break
                    } else {
                        println("Please enter a valid answer as in \"Yes\" or \"No\"")
                    }
                }
            }

            students.add(Student(id = id, name = name, gpa = gpa, grade = grade, status = status, notes = notes))
            println("Student Added!")
        }

        is Action.ViewAllStudents -> {
            if (students.isEmpty()) {
                println("No students found.")
            } else {
                println("\n=== STUDENT LIST ===")
                students.forEach { student ->
                    println("ID: ${student.id}")
                    println("Name: ${student.name}")
                    println("GPA: ${student.gpa}")
                    println("Grade: ${student.grade}")
                    println("Status: ${student.status}")
                    println("Notes: ${student.notes?.joinToString(", ") ?: "None"}")
                    println("-------------------")
                }
            }
        }

        is Action.FilterByGrade -> TODO()
        is Action.FilterByStatus -> TODO()
        is Action.FilterByName -> TODO()
        is Action.UpdateStudent -> TODO()
        is Action.RemoveStudent -> TODO()
    }
}
