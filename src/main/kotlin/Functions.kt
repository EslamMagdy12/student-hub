package org.example
import java.io.File
fun getStudentDetails(): Student {
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
        if (input == null || input < 0.0 || input > 4.0) {
            println("Please enter a valid GPA")
        } else {
            gpa = input
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
                println("Please enter a valid answer as in \"Yes\" or \"no\"")
            }
        }
    }
    val student = Student(
        name = name,
        gpa = gpa,
        status = status,
        notes = notes
    )
    return student
}

fun updateStudent(id: Int) {
    val student = students.find { it.id == id }
    while (true) {
        println(
            """
            |Update Student Details
            |Please select what you want to update:
            |1. Name
            |2. GPA
            |3. Status
            |4. Notes
            |5. Exit
        """.trimMargin()
        )
        val input = readLine()?.toIntOrNull()
        when (input) {
            1 -> {
                print("Enter new name: ")
                val newName = readLine() ?: "Student"
                student?.let { it.name = newName }
            }

            2 -> {
                print("Enter new GPA: ")
                val newGpa = readLine()?.toDoubleOrNull()
                if (newGpa != null && newGpa >= 0.0 && newGpa <= 4.0) {
                    student?.let { it.gpa = newGpa }
                    student?.let { it.grade = Student.calculateGrade(newGpa) }
                } else {
                    println("Invalid GPA. Please try again.")
                }
            }

            3 -> {
                print("Enter new status (Active / Inactive): ")
                val newStatus = readLine()?.lowercase() ?: ""
                if (newStatus in listOf("active", "inactive")) {
                    student?.let { it.status = newStatus }
                } else {
                    println("Invalid status. Please try again.")
                }
            }

            4 -> {
                print("Enter note to add: ")
                val note = readLine() ?: ""
                student?.notes?.add(note)
            }

            5 -> return
            else -> println("Invalid option. Please try again.")
        }
    }

}

fun extractData(students: MutableList<Student>){
    val file = File("students.csv")
    file.printWriter().use { out ->
        out.println("ID,Name,GPA,Grade,Status,Notes") // header
        students.forEach {
            out.println("${it.id},${it.name},${it.gpa},${it.grade},${it.status},${it.notes}")
        }
    }
    println("A file named \"${file.name}\" is created!")
}