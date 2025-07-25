package org.example  // Match other files

object StudentViewer {
    fun displayAll(students: List<Student>) {
        if (students.isEmpty()) {
            println("No students found.")
            return
        }

        println("\n=== STUDENT LIST ===")
        students.forEach { student ->
            println("ID: ${student.id}")
            println("Name: ${student.name}")
            println("GPA: ${student.gpa ?: "N/A"}")
            println("Grade: ${student.grade ?: "N/A"}")
            println("Status: ${student.status ?: "N/A"}")
            println("Notes: ${student.notes?.joinToString() ?: "None"}")
            println("-------------------")
        }
    }
}
