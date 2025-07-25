package org.example

sealed class Action {
    object AddStudent : Action()
    object ViewAllStudents : Action()
    data class FilterByGrade(val grade: String) : Action()
    data class FilterByStatus(val status: String) : Action()
    data class FilterByName(val name: String) : Action()
    data class UpdateStudent(val id: Int) : Action()
    data class RemoveStudent(val id: Int) : Action()
}
