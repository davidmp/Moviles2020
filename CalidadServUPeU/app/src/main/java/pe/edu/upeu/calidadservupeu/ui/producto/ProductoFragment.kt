package pe.edu.upeu.calidadservupeu.ui.producto

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_producto.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import pe.edu.upeu.calidadservupeu.R
import pe.edu.upeu.calidadservupeu.databinding.ProductoFragmentBinding
import pe.edu.upeu.calidadservupeu.model.Producto
import pe.edu.upeu.calidadservupeu.model.State
import pe.edu.upeu.calidadservupeu.ui.base.BaseFragment
import pe.edu.upeu.calidadservupeu.ui.details.DetailsActivity
import pe.edu.upeu.calidadservupeu.ui.producto.adapter.ProductoListAdapter
import pe.edu.upeu.calidadservupeu.utils.*

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ProductoFragment : BaseFragment<ProductoViewModel, ProductoFragmentBinding>() {
    public override val mViewModel:ProductoViewModel by viewModels()
    private val mAdapter=ProductoListAdapter(this::onItemClicked)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mViewBinding.productosRecyclerView.adapter=mAdapter
        iniProducts()
        handleNetworkChanges()

        return mViewBinding.root
    }

    private fun handleNetworkChanges(){
        NetworkUtils.getNetworkLiveData(this.requireContext()).observe(this.requireActivity()){
            isConnected->
            if (!isConnected){
                mViewBinding.statusTextView.text=getString(R.string.text_no_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    show()
                    setBackgroundColor(getColorRes(R.color.colorStatusNotConnected))
                }
            }else{
                if (mViewModel.productosLidaData.value is State.Error || mAdapter.itemCount==0 ){
                    getProductos()
                }
                mViewBinding.statusTextView.text=getString(R.string.text_connectivity)
                mViewBinding.networkStatusLayout.apply {
                    setBackgroundColor(getColorRes(R.color.colorStatusConnected))
                    animate().alpha(1f)
                        .setStartDelay(ProductoFragment.ANIMATION_DURATION)
                        .setDuration(ProductoFragment.ANIMATION_DURATION)
                        .setListener(object : AnimatorListenerAdapter(){
                            override fun onAnimationEnd(animation: Animator?) {
                                hide()
                            }
                        })
                }

            }
        }
    }


    override fun getViewBinding(): ProductoFragmentBinding=ProductoFragmentBinding.inflate(layoutInflater)

    private fun onItemClicked(producto: Producto, imageView: ImageView, v:View){
    when(v!!){
        v.btnDelete->{
            deleteProduct(producto)
            Log.i("LLEGA_D", "Si va Eliminar")
        }

        else->{
            val intent=Intent(this.context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.PRODUCT_ID,producto.id)
            val options=ActivityOptionsCompat.makeSceneTransitionAnimation(
                this.requireActivity(), imageView, imageView.transitionName
            )
            startActivity(intent, options.toBundle())
        }
    }
    }

    private fun iniProducts(){
        mViewModel.productosLidaData.observe(this.requireActivity()){
            state->
            when(state){
                is State.Loading->showLoading(true)
                is State.Success->{
                    if (state.data.isNotEmpty()){
                        mAdapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error->{
                    showToast(state.message)
                    showLoading(false)
                }
            }
        }
        mViewBinding.swipeRefreshLayout.setOnRefreshListener { getProductos() }
        if (mViewModel.productosLidaData.value !is State.Success){
            getProductos()
        }

    }

    private fun deleteProduct(producto: Producto){
        mViewModel.deleteProductById(producto)
    }

    private fun getProductos(){
        mViewModel.getProductos()
    }

    private fun showLoading(isLoading:Boolean){
        mViewBinding.swipeRefreshLayout.isRefreshing=isLoading
    }

    companion object{
        const val ANIMATION_DURATION=1000.toLong()
    }
}