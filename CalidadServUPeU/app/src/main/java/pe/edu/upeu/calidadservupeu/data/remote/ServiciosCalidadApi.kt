package pe.edu.upeu.calidadservupeu.data.remote

import pe.edu.upeu.calidadservupeu.model.Producto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiciosCalidadApi {

    @GET("/producto/lista")
    suspend fun getProducto():Response<List<Producto>>

    @GET("/producto/detail/{id}")
    suspend fun getProductoId(@Query("id") id: Int): Call<Producto>

    companion object{
        const val SERVICIO_CALIDAD_API_URL="http://172.22.90.1/"
    }
}