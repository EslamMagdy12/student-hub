package org.example

sealed class Action {
    data class AddStudent(val student: Student) : Action()
    class ViewAllStudents() : Action()
    data class FilterByGrade(val grade: Double) : Action()
    data class FilterByStatus(val status: String) : Action()
    data class FilterByName(val name: String) : Action()
    data class UpdateStudent(val id: Int) : Action()
    data class RemoveStudent(val id: Int) : Action()

}