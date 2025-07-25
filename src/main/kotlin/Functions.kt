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

fun FilterByGrade(grade: String) {
    val filterdGrade = students.filter { it.grade == grade }
    println("students with grade $grade: ")
    displayAll(filterdGrade)
}

fun FilterByName(name: String) {
    val searchedName = students.filter { it.name.lowercase().contains(name.lowercase()) }
    println("students with name $name: ")
    displayAll(searchedName)
}

fun FilterByStatus(status: String) {
    val filterdStatues = students.filter { it.status?.lowercase() == status.lowercase() }
    println("students with status $status: ")
    displayAll(filterdStatues)
}

fun filterGPAranges() {
    val gpa3_5to4 = students.filter { it.gpa != null && it.gpa!! in 3.5..4.0 }
    val gpa3to3_5 = students.filter { it.gpa != null && it.gpa!! in 3.0..3.5 }
    val gpa2_5to3 = students.filter { it.gpa != null && it.gpa!! in 2.5..3.0 }
    val gpa2to2_5 = students.filter { it.gpa != null && it.gpa!! in 2.0..2.5 }
    val gpaBelow2 = students.filter { it.gpa != null && it.gpa!! < 2.0 }

    println("students with gpa 3.5 to 4: ")
    displayAll(gpa3_5to4)

    println("students with gpa 3.0 to 3.5: ")
    displayAll(gpa3to3_5)

    println("students with gpa 2.5 to 3.0: ")
    displayAll(gpa2_5to3)

    println("students with gpa 2.0 to 2.5: ")
    displayAll(gpa2to2_5)

    println("Students with GPA below 2.0:")
    displayAll(gpaBelow2)

}

fun extractData(students: MutableList<Student>) {
    val file = File("students.csv")
    file.printWriter().use { out ->
        out.println("ID,Name,GPA,Grade,Status,Notes") // header
        students.forEach {
            out.println("${it.id},${it.name},${it.gpa},${it.grade},${it.status},${it.notes}")
        }
    }
    println("A file named \"${file.name}\" is created!")
}

fun RemoveStudent(id: Int) {
    val student = students.find { it.id == id }
    when (student) {
        null -> println("No Student with id $id")
        else -> {
            students.remove(student)
            println("Student ${student.name} removed")
        }
    }
}

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
        println("Notes: ${if (student.notes.isNullOrEmpty()) "No notes" else student.notes?.joinToString(", ")}")
        println("-------------------")
    }
}

fun adminLogin(): Boolean {
    println("Admin Login -> Temporary hint: \n\tUsername: admin\n\tPassword: password123")
    val adminUsername = "admin"
    val adminPassword = "password123"
    var username: String?
    print("Admin Username: ")
    username = readlnOrNull()
    while (username.isNullOrEmpty()) {
        println("Username cannot be empty. Please enter a valid username.")
        print("Admin Username: ")
        username = readlnOrNull()
    }

    var password: String?
    print("Admin Password: ")
    password = readlnOrNull()
    while (password.isNullOrEmpty()) {
        println("Password cannot be empty. Please enter a valid password.")
        print("Admin Password: ")
        password = readlnOrNull()
    }
    return username == adminUsername && password == adminPassword
}