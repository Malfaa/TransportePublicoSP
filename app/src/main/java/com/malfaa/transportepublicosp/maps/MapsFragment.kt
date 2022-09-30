package com.malfaa.transportepublicosp.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs

import com.google.android.gms.maps.CameraUpdateFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.malfaa.transportepublicosp.R
import com.malfaa.transportepublicosp.databinding.FragmentMapsBinding
import org.koin.androidx.compose.viewModel

class MapsFragment : Fragment() {

    companion object{
        private val TAG = "SelectLocationFragment"
        private const val REQUEST_LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val TURN_DEVICE_LOCATION_ON_REQUEST_CODE = 29
    }
    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        setPoiClick(googleMap)//map

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    private lateinit var binding: FragmentMapsBinding
    private val viewModel: MapsViewModel by viewModel()
    private val args : MapsFragmentArgs by navArgs()
    private lateinit var map: GoogleMap
    private var location : Marker? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = binding.map.getFragment() as SupportMapFragment? //childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        viewModel.args.value = args  //onStarted

        mapFragment?.getMapAsync(callback)

    }


    private fun setPoiClick(map: GoogleMap) {
        for (onibus in viewModel.onibus.value!!){
            val latLng = LatLng(onibus.py,onibus.px)
            val poiMarker = map.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title(onibus.c)
            )
            location?.remove()
            location = poiMarker
        }

    }
 /*

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        setPoiClick(map)

        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

*/
    // Checks that users have given permission
    private fun isPermissionGranted(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R){
            val foregroundCheck =
                ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)
            return foregroundCheck == PackageManager.PERMISSION_GRANTED
        }
        return false
    }

    // Checks if users have given their location and sets location enabled if so.
    @SuppressLint("MissingPermission")
    private fun enableMyLocation() {
        if (isPermissionGranted()) {
            map.isMyLocationEnabled = true
        }
        else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String>,
//        grantResults: IntArray) {
//        // Check if location permissions are granted and if so enable the
//        // location data layer.
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == REQUEST_LOCATION_PERMISSION_REQUEST_CODE) {
//            if (grantResults.isNotEmpty() &&
//                grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//                enableMyLocation()
//
//            } else {
////            viewModel.showSnackBarInt.value = R.string.permission_denied_explanation
//            }
//        }
//    }

}