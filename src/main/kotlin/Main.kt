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
        
        when (readLine()?.toIntOrNull()) {
            1 -> doAction(Action.AddStudent)
            2 -> doAction(Action.ViewAllStudents)
            3 -> {
                print("Enter grade to filter: ")
                doAction(Action.FilterByGrade(readLine() ?: ""))
            }
            4 -> {
                print("Enter status to filter: ")
                doAction(Action.FilterByStatus(readLine() ?: ""))
            }
            5 -> {
                print("Enter name to filter: ")
                doAction(Action.FilterByName(readLine() ?: ""))
            }
            6 -> {
                print("Enter student ID to update: ")
                doAction(Action.UpdateStudent(readLine()?.toIntOrNull() ?: -1))
            }
            7 -> {
                print("Enter student ID to remove: ")
                doAction(Action.RemoveStudent(readLine()?.toIntOrNull() ?: -1))
            }
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
            // ... (keep your existing AddStudent implementation)
        }

        is Action.ViewAllStudents -> {
            StudentViewer.displayAll(students)
        }

        is Action.FilterByGrade -> {
            val filtered = students.filter { it.grade.equals(action.grade, ignoreCase = true) }
            StudentViewer.displayAll(filtered)
        }

        is Action.FilterByStatus -> {
            val filtered = students.filter { it.status.equals(action.status, ignoreCase = true) }
            StudentViewer.displayAll(filtered)
        }

        is Action.FilterByName -> {
            val filtered = students.filter { it.name.contains(action.name, ignoreCase = true) }
            StudentViewer.displayAll(filtered)
        }

        is Action.UpdateStudent -> {
            println("Update functionality coming soon!")
        }

        is Action.RemoveStudent -> {
            if (students.removeIf { it.id == action.id }) {
                println("Student removed successfully!")
            } else {
                println("Student not found!")
            }
        }
    }
}
