package pe.edu.upeu.calidadservupeu.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pe.edu.upeu.calidadservupeu.R
import pe.edu.upeu.calidadservupeu.databinding.ActivityDetailsBinding
import pe.edu.upeu.calidadservupeu.ui.base.BaseActivity

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailsActivity : BaseActivity<ProductDetailsViewModel, ActivityDetailsBinding>() {

    override val mViewModel: ProductDetailsViewModel by viewModels()

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

    }

    override fun getViewBinding(): ActivityDetailsBinding = ActivityDetailsBinding.inflate(layoutInflater)

    companion object{
        const val PRODUCT_ID="productId"
    }
}