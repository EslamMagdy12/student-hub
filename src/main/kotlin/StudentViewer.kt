package com.studenthub

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
            println("Grade: ${student.grade ?: "N/A"}")
            println("Email: ${student.email ?: "N/A"}")
            println("-------------------")
        }
    }
}