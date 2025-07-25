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
            |8. Filter Students by GPA Ranges
            |9. Extract Students Data to a csv File
            |10. Exit
        """.trimMargin()
        )
        val input = readLine()?.toInt()
        when (input) {
            1 -> {
                val student = getStudentDetails()
                doAction(Action.AddStudent(student))
            }

            2 -> {
                doAction(Action.ViewAllStudents())
            }
            3 -> {
                print("Enter grade to filter by (A, B, C, D, Undefined): ")
                val grade = readLine()?.lowercase() ?: ""
                doAction(Action.FilterByGrade(grade))
            }

            4 -> {
                print("Enter status to filter by : ")
                val status = readLine()?.lowercase() ?: ""
                doAction(Action.FilterByStatus(status))
            }

            5 -> {
                print("Enter name (or part of it) to filter by: ")
                val name = readLine()?.lowercase() ?: ""
                doAction(Action.FilterByName(name))
            }

            6 -> {
                val id = readLine()?.toIntOrNull()
                if (id != null) {
                    doAction(Action.UpdateStudent(id))
                } else {
                    println("Invalid ID. Please enter a valid integer.")
                }
            }

            7 -> {
                val id = readLine()?.toIntOrNull()
                if (id != null) {
                    doAction(Action.RemoveStudent(id))
                } else {
                    println("Invalid ID. Please enter a valid integer.")
                }
            }

            8 -> {
                doAction(Action.FilterByGPARange())
            }

            9 -> {
                doAction(Action.ExtractDataToCSV())
            }

            10 -> {
                doAction(Action.Exit())
                break
            }

            else -> {
                println("Invalid option. Please select a valid option.")
            }
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

        is Action.ViewAllStudents -> {
            displayAll(students)
        }
        is Action.FilterByGrade -> {
            FilterByGrade(action.grade)
        }

        is Action.FilterByStatus -> {
            FilterByStatus(action.status)
        }

        is Action.FilterByName -> {
            FilterByName(action.name)
        }

        is Action.FilterByGPARange -> {
            filterGPAranges()
        }

        is Action.UpdateStudent -> {
            val student = students.find { it.id == action.id }
            if (student != null) {
                updateStudent(action.id)
            } else {
                println("Student with ID ${action.id} not found.")
            }
        }

        is Action.RemoveStudent -> {
            val student = students.find { it.id == action.id }
            if (student != null) {
                RemoveStudent(action.id)
            } else {
                println("Student with ID ${action.id} not found.")
            }
        }

        is Action.ExtractDataToCSV -> extractData(students)
        is Action.Exit -> {
            println("Exiting the Student Management System...")
        }
    }
}
