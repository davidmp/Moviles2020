package pe.edu.upeu.drawerqrgps.ui.mapbox

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.edu.upeu.drawerqrgps.R

class MapboxFragment : Fragment() {

    companion object {
        fun newInstance() = MapboxFragment()
    }

    private lateinit var viewModel: MapboxViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.mapbox_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MapboxViewModel::class.java)
        // TODO: Use the ViewModel
    }

}