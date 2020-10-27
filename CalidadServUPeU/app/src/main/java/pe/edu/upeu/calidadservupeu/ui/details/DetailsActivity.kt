package pe.edu.upeu.calidadservupeu.ui.details

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import coil.api.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.formcreate_product.*
import kotlinx.android.synthetic.main.formcreate_product.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pe.edu.upeu.calidadservupeu.R
import pe.edu.upeu.calidadservupeu.databinding.ActivityDetailsBinding
import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.ui.base.BaseActivity

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsActivity : BaseActivity<ProductDetailsViewModel, ActivityDetailsBinding>() {

    override val mViewModel: ProductDetailsViewModel by viewModels()
    private  lateinit var producto: Producto
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val productoId=intent.extras?.getInt(PRODUCT_ID)?: throw IllegalArgumentException("ProdictId es nulo")
        Log.i("DMP", "IdProducto:"+productoId)

        iniProducts(productoId)
    }

    private fun iniProducts(productoId:Int){
        mViewModel.getProduct(productoId).observe(this){
            productx->mViewBinding.productContent.apply {
            producto=productx
            productName.text=producto.nombre
            productPreci.text=""+producto.precio
        }
            mViewBinding.imageView.load(producto.nombre){
                placeholder(R.drawable.ic_photo)
                error(R.drawable.ic_broken_image)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                supportFinishAfterTransition()
                return true
            }
            R.id.action_edit->{
                val mDialogView=LayoutInflater.from(this).inflate(R.layout.formcreate_product,null)
                val mBuild=AlertDialog.Builder(this).setView(mDialogView)
                    .setTitle("Formulario")
                val mAlertDialog=mBuild.show()
                mAlertDialog.txtNombre.setText(producto.nombre)
                mAlertDialog.txtPrecio.setText(producto.precio.toString())

                Log.i("LLEGA_U", "Si va Actualizar"+producto.id)
                mDialogView.btnGuardar.setOnClickListener {
                    producto.nombre=mDialogView.txtNombre.text.toString()
                    producto.precio=mDialogView.txtPrecio.text.toString().toFloat()
                    updateProduct(producto)
                    Log.i("DSI", "Si funciona:"+producto.nombre)
                    mAlertDialog.dismiss()
                }
                mDialogView.btnCancelar.setOnClickListener{
                    mAlertDialog.dismiss()
                }
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
    private fun updateProduct(producto: Producto){
        mViewModel.updateProduct(producto)
    }
    override fun getViewBinding(): ActivityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)

    fun actualizarProducto(producto: Producto){
        mViewModel.updateProduct(producto)
    }

    companion object{
        const val PRODUCT_ID="productId"
    }
}