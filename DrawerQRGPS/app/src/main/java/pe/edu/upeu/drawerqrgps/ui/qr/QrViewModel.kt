package pe.edu.upeu.drawerqrgps.ui.qr

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QrViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Holas QR"
    }
    val text2: LiveData<String> = _text
}