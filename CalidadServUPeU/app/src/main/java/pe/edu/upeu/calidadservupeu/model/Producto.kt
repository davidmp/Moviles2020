package pe.edu.upeu.calidadservupeu.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto")
data class Producto(
   @PrimaryKey
   var id:Int?=0,
   var nombre:String?=null,
   var precio:Float?=0.0f
)