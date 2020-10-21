package pe.edu.upeu.calidadservupeu.data.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import pe.edu.upeu.calidadservupeu.data.local.dao.ProductoDao
import pe.edu.upeu.calidadservupeu.data.remote.ServiciosCalidadApi
import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.model.State
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class ProductoRepository @Inject constructor(
    private val productoDao:ProductoDao,
    private val servicioProductoApi:ServiciosCalidadApi
){
    fun getAllProductos():Flow<State<List<Producto>>>{
        return object : NetworkBoundRepository<List<Producto>, List<Producto>>(){
            override suspend fun saveRemoteData(response: List<Producto>) = productoDao.insertProductos(response)
            override  fun fetchFromLocal(): Flow<List<Producto>> =productoDao.getAllProducto()
            override suspend fun fetchFromRemote(): Response<List<Producto>> =servicioProductoApi.getProducto()
        }.asFlow()
    }

    @MainThread
    fun getProductoById(productoId:Int):Flow<Producto> =productoDao.getProductoById(productoId)

    @WorkerThread
    suspend fun deleteProducLocalById(producto: Producto)=productoDao.deleteProductById(producto)

    @MainThread
    suspend fun deleteProducRemoteById(productoId: Int)=servicioProductoApi.deleteProductId(productoId)

    suspend fun deleteProducById(producto: Producto){
        deleteProducLocalById(producto)
        deleteProducRemoteById(producto.id!!)
    }
    
}