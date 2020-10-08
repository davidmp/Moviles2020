package pe.edu.upeu.calidadservupeu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import pe.edu.upeu.calidadservupeu.model.Producto

@Dao
interface ProductoDao {
    @Query("SELECT * FROM producto")
    fun getAllProducto(): Flow<List<Producto>>

    @Query("SELECT * FROM producto where id=:productoId")
    fun getProductoById(productoId:Int): Flow<Producto>

    @Query("delete from producto")
    suspend fun deleteAllProductos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductos(producto:List<Producto>)

}