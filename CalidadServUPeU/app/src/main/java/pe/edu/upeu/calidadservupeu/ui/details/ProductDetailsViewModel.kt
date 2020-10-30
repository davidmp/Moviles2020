package pe.edu.upeu.calidadservupeu.ui.details

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import pe.edu.upeu.calidadservupeu.data.repository.ProductoRepository
import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.model.State
import pe.edu.upeu.calidadservupeu.R
import pe.edu.upeu.calidadservupeu.utils.UtilsToken
import pe.edu.upeu.calidadservupeu.model.remote.User

@ExperimentalCoroutinesApi
class ProductDetailsViewModel @ViewModelInject constructor(
    private val productoRepository: ProductoRepository,
    @Assisted private  val savedStateHandle: SavedStateHandle
): ViewModel(){

    var userX:User?=null

    fun getProduct(id:Int)=productoRepository.getProductoById(id).asLiveData()

   fun updateProduct(producto: Producto){
       Log.i("LLEGA_UX", "Si actualizara!"+producto.id)
       viewModelScope.launch {
           productoRepository.updateProduct(producto)
           getProduct(producto.id!!)
       }
   }

    fun login(user: User){
        viewModelScope.launch {
         userX=productoRepository.loginUser(user)
            Log.i("TOKEN", ""+userX!!.token)
            Log.i("TOKEN", ""+userX!!.bearer)
            Log.i("TOKEN", ""+userX!!.authorities?.last()?.authority)

            UtilsToken.TOKEN_CONTENT=userX!!.bearer+" "+userX!!.token
        }

    }

}