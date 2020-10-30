package pe.edu.upeu.calidadservupeu.data.remote

import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.model.remote.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ServiciosCalidadApi {

    @GET("/CalidadServApi/producto/lista")
    suspend fun getProducto():Response<List<Producto>>

    @GET("/CalidadServApi/producto/detail/{id}")
    suspend fun getProductoId(@Query("id") id: Int): Call<Producto>

    @POST("/CalidadServApi/producto/create")
    suspend fun addProduct(@Header("Authorization") token:String, @Body producto: Producto):Response<Void>

    @DELETE("/CalidadServApi/producto/delete/{id}")
    suspend fun deleteProductId(@Path("id") id:Int):Response<Void>

    @PUT("/CalidadServApi/producto/update/{id}")
    suspend fun updateProductId(@Path("id") id:Int,  @Body product:Producto):Response<Void>

    @POST("/CalidadServApi/auth/login")
    suspend fun login(@Body user: User):User

    companion object{
        const val SERVICIO_CALIDAD_API_URL="http://ec2-35-168-111-51.compute-1.amazonaws.com:8080"
    }
}