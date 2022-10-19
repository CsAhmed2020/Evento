package com.example.evento

data class Session(
     val name : String,
     val stage : String,
     val speaker : String,
     val start : String,
     val end : String,
     val day : String,
     var isSelected: Boolean ,
)
