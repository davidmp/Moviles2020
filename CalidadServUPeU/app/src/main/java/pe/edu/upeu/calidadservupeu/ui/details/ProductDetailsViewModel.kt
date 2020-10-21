package pe.edu.upeu.calidadservupeu.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pe.edu.upeu.calidadservupeu.data.repository.ProductoRepository

@ExperimentalCoroutinesApi
class ProductDetailsViewModel @ViewModelInject constructor(
    private val productoRepository: ProductoRepository
): ViewModel(){
    fun getProduct(id:Int)=productoRepository.getProductoById(id).asLiveData()
}