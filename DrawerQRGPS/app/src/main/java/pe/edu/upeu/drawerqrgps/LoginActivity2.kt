package pe.edu.upeu.drawerqrgps

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.core.app.ActivityCompat

class LoginActivity2 : AppCompatActivity() {
    companion object {
        private const val CAMERA_PERMISSION_REQUEST_CODE = 102
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        Handler().postDelayed({
            checkForPermission()
        }, 2000)
    }
    private fun checkForPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gotToMainActivity()
        } else {
            requestPermission()
            Log.i("PermisoCamara","No tiene Permiso")
        }
    }

    private fun gotToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

}