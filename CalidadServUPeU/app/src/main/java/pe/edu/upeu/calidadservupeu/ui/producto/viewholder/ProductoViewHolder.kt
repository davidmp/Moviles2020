package pe.edu.upeu.calidadservupeu.ui.producto.viewholder

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import pe.edu.upeu.calidadservupeu.R
import pe.edu.upeu.calidadservupeu.databinding.ItemProductoBinding
import pe.edu.upeu.calidadservupeu.model.Producto

class ProductoViewHolder(
    private val binding: ItemProductoBinding
) : RecyclerView.ViewHolder(binding.root){
    fun bind(producto: Producto, onItemClicked: (Producto, ImageView)->Unit){
        binding.productoTitle.text=producto.nombre
        binding.productoSubitem.text="S/. "+producto.precio
        binding.imageView.load(producto.nombre){
            placeholder(R.drawable.ic_photo)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(producto,binding.imageView)
        }
    }
}