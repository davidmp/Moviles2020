package pe.edu.upeu.calidadservupeu.ui.details

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import pe.edu.upeu.calidadservupeu.data.repository.ProductoRepository
import pe.edu.upeu.calidadservupeu.model.Producto

@ExperimentalCoroutinesApi
class ProductDetailsViewModel @ViewModelInject constructor(
    private val productoRepository: ProductoRepository,
    @Assisted private  val savedStateHandle: SavedStateHandle
): ViewModel(){
    fun getProduct(id:Int)=productoRepository.getProductoById(id).asLiveData()

   fun updateProduct(producto: Producto){
       Log.i("LLEGA_UX", "Si actualizara!"+producto.id)
       viewModelScope.launch {
           productoRepository.updateProduct(producto)
           getProduct(producto.id!!)
       }

   }

}