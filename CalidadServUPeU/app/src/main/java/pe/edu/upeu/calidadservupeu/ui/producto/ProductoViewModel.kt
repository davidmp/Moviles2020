package pe.edu.upeu.calidadservupeu.ui.producto

import android.util.Log
import androidx.hilt.Assisted
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pe.edu.upeu.calidadservupeu.data.repository.ProductoRepository
import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.model.State

@ExperimentalCoroutinesApi
class ProductoViewModel @ViewModelInject constructor(
    private val productoRepository: ProductoRepository,
    @Assisted private  val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private  val _productosLiveData=MutableLiveData<State<List<Producto>>>()
    val productosLidaData:LiveData<State<List<Producto>>>
        get() =_productosLiveData

    fun getProductos(){
        viewModelScope.launch { productoRepository.getAllProductos().collect{
            _productosLiveData.value=it
        } }
    }

    fun deleteProductById(producto: Producto){
        viewModelScope.launch {
           productoRepository.deleteProducById(producto)
           productoRepository.getAllProductos().collect{
                _productosLiveData.value=it
            }
            Log.i("LLEGA_DMP", "Esta llegando"+producto.id)
        }
    }

    fun createProduct(producto: Producto){
        viewModelScope.launch {
            productoRepository.insertProduct(producto)
            productoRepository.getAllProductos().collect{
                _productosLiveData.value=it
            }
        }
    }

}