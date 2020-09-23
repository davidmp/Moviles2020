package pe.edu.upeu.drawerqrgps.ui.qr

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.qr_fragment.view.*
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import pe.edu.upeu.drawerqrgps.R

class QrFragment : Fragment(), ZBarScannerView.ResultHandler {

    companion object {
        fun newInstance(): QrFragment{return QrFragment()}
    }
    private lateinit var mView: View
    lateinit var scannerView: ZBarScannerView
    private lateinit var viewModel: QrViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =ViewModelProviders.of(this).get(QrViewModel::class.java)
        mView=inflater.inflate(R.layout.qr_fragment, container, false)
        val mensaje=mView.findViewById<TextView>(R.id.tvMsg)
        viewModel.text2.observe(viewLifecycleOwner, Observer {
            mensaje.text = it
        })
        initializeQRCamera()
        onClicks()
        return mView.rootView


    }


    private fun initializeQRCamera() {
        scannerView = ZBarScannerView(context)
        scannerView.setResultHandler(this)
        scannerView.setBackgroundColor(ContextCompat.getColor(this.requireContext()!!, R.color.design_default_color_on_primary))
        scannerView.setBorderColor(ContextCompat.getColor(this.requireContext()!!, R.color.colorPrimaryDark))
        scannerView.setLaserColor(ContextCompat.getColor(this.requireContext()!!, R.color.colorPrimaryDark))
        scannerView.setBorderStrokeWidth(10)
        scannerView.setSquareViewFinder(true)
        scannerView.setupScanner()
        scannerView.setAutoFocus(true)
        startQRCamera()
        mView.containerScanner.addView(scannerView)
    }
    private fun startQRCamera() {
        scannerView.startCamera()
    }

    override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun onDestroy() {
        super.onDestroy()
        scannerView.stopCamera()
    }

    override fun handleResult(rawResult: Result?) {
        Toast.makeText(this.requireContext()!!,"UPeU:"+rawResult?.contents,Toast.LENGTH_LONG).show()
        scannerView.resumeCameraPreview(this)
    }


    private fun onClicks() {
        mView.flashToggle.setOnClickListener {
            if (mView.flashToggle.isSelected) {
                offFlashLight()
            } else {
                onFlashLight()
            }
        }
    }

    private fun onFlashLight() {
        mView.flashToggle.isSelected = true
        scannerView.flash = true
    }

    private fun offFlashLight() {
        mView.flashToggle.isSelected = false
        scannerView.flash = false
    }

}