package pe.edu.upeu.drawerqrgps.ui.mapbox

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import pe.edu.upeu.drawerqrgps.R
import pe.edu.upeu.drawerupeumapbox.utils.CompanionString

class MapboxFragment : Fragment(), OnMapReadyCallback, PermissionsListener {

    private lateinit var map: MapboxMap
    private lateinit var permissionsManager: PermissionsManager
    private lateinit var locationEngine: LocationEngine
    private lateinit var mapboxViewModel: MapboxViewModel
    private lateinit var mapView:MapView

    private lateinit var callback: MapboxFragment.LocationChangeListeningCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Mapbox.getInstance(this.requireActivity(), getString(R.string.mapbox_access_token))
        mapboxViewModel =ViewModelProviders.of(this).get(MapboxViewModel::class.java)
        val root=inflater.inflate(R.layout.mapbox_fragment, container, false)
        mapView=root.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView?.getMapAsync(this)
        return root
    }



    private inner class LocationChangeListeningCallback :
        LocationEngineCallback<LocationEngineResult> {

        override fun onSuccess(result: LocationEngineResult?) {
            result?.lastLocation ?: return //BECAREFULL HERE, IF NAME LOCATION UPDATE DONT USER -> val resLoc = result.lastLocation ?: return
            if (result.lastLocation != null){
                val lat = result.lastLocation?.latitude!!
                val lng = result.lastLocation?.longitude!!
                val latLng = LatLng(lat, lng)

                if (result.lastLocation != null) {
                    map.locationComponent.forceLocationUpdate(result.lastLocation)
                    val position = CameraPosition.Builder()
                        .target(latLng)
                        .zoom(13.0) //disable this for not follow zoom
                        .tilt(10.0)
                        .build()
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(position))
                    Toast.makeText(this@MapboxFragment.context, "Actualización de ubicación: $latLng", Toast.LENGTH_SHORT).show()
                }
            }

        }
        override fun onFailure(exception: Exception) {}
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        map = mapboxMap
        callback = LocationChangeListeningCallback()
        mapboxMap.setStyle(Style.MAPBOX_STREETS) {
            enableLocationComponent(it)
        }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        if (PermissionsManager.areLocationPermissionsGranted(this.requireContext())) {
            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(this.requireContext(), loadedMapStyle)
                .useDefaultLocationEngine(false)
                .build()
            map.locationComponent.apply {
                activateLocationComponent(locationComponentActivationOptions)
                isLocationComponentEnabled = true                       // Enable to make component visible
                cameraMode = CameraMode.TRACKING                        // Set the component's camera mode
                renderMode = RenderMode.COMPASS                         // Set the component's render mode
            }
            initLocationEngine()
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(this.requireActivity())
        }
    }


    @SuppressLint("MissingPermission")
    private fun initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(this.requireContext())
        val request = LocationEngineRequest
            .Builder(CompanionString.DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(CompanionString.DEFAULT_MAX_WAIT_TIME)
            .build()
        locationEngine.requestLocationUpdates(request, callback, Looper.getMainLooper())
        locationEngine.getLastLocation(callback)
    }


    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(this.requireContext(), "Permiso no concedido !!", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            map.getStyle {
                enableLocationComponent(it)
            }
        } else {
            Toast.makeText(this.requireContext(), "Permiso no concedido !! la aplicación será cerrada", Toast.LENGTH_LONG).show()
            Handler().postDelayed({
                this.requireActivity().finish()
            }, 3000)
        }
    }


    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Prevent leaks
        locationEngine.removeLocationUpdates(callback)
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }


    }



