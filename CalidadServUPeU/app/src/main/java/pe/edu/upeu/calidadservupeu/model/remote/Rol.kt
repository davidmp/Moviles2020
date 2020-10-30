package pe.edu.upeu.calidadservupeu.model.remote

data class Rol(
    val authority:String
)

data class User(
    val token:String="",
    val bearer:String="",
    val nombreUsuario:String="user",
    val password:String="123456",
    val authorities:List<Rol>?=null
)