package com.malfaa.transportepublicosp.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.malfaa.transportepublicosp.R
import com.malfaa.transportepublicosp.databinding.FragmentMapsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MapsFragment : Fragment() {

    companion object{
        private const val REQUEST_LOCATION_PERMISSION_REQUEST_CODE = 1
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
        setMapStyle(googleMap)

        Handler(Looper.getMainLooper()).postDelayed({
            try{
                viewModel.fileLeituraTrips(requireContext(),args.linha.lt, args.linha.tl, args.linha.sl).apply {
                    shapeid = this
                }
            }catch (e:Exception){
                Log.e("Error Callback",e.toString())
            }finally {
                viewModel.retornaCoordenadasRota(requireContext(),shapeid).run {
                    googleMap.addPolyline(
                        PolylineOptions()
                            .addAll(this)
                            .color(Color.RED)
                    )
                }
            }

            setOnibusMarkers(googleMap)
            setLargadaChegada(googleMap, viewModel.markersLargadaChegada)
            movimentoCamera(googleMap)

            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                enableMyLocation(googleMap)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_LOCATION_PERMISSION_REQUEST_CODE
                )
                enableMyLocation(googleMap)
            }
        },1500)
        map = googleMap
    }

    private lateinit var binding: FragmentMapsBinding
    private val viewModel: MapsViewModel by viewModel()
    private val args : MapsFragmentArgs by navArgs()
    private lateinit var shapeid: String
    private lateinit var map: GoogleMap
    val markers = mutableListOf<Marker>()

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
        val mapFragment =
            binding.map.getFragment() as SupportMapFragment?

        viewModel.args.value = args  //onStarted

        mapFragment?.getMapAsync(callback)

        binding.refresher.setOnClickListener {
            viewModel.refreshOnibus()
            binding.refresher.isEnabled = false
            viewModel.testeSeAlteraAsPosicoes.value = false

            Handler(Looper.getMainLooper()).postDelayed({
                deletarOnibusMarkers()
                binding.refresher.isEnabled = true
                viewModel.testeSeAlteraAsPosicoes.value = true
            }, 1500)
        }

        binding.posicionamento.setOnClickListener {
            movimentoCamera(map)

        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.refreshOnibus()
    }

    private fun setOnibusMarkers(map: GoogleMap) {
        viewModel.testeSeAlteraAsPosicoes.observe(viewLifecycleOwner){
                valor ->
            if (valor) {
                for (onibus in viewModel.onibus) {
                    val latLng = LatLng(onibus.py, onibus.px)
                    markers.add(
                        map.addMarker(MarkerOptions().position(latLng).icon(
                            viewModel.bitmapDescriptorFromVector(
                                requireContext(),
                                R.drawable.ic_onibus
                            )
                        ))!!
                    )
                }
            }
        }
    }
    private fun deletarOnibusMarkers(){
        for(i in markers){
            i.remove()
        }
        markers.clear()
    }

    private fun setLargadaChegada(map: GoogleMap, markers: List<LatLng>){
        map.addMarker(
            MarkerOptions()
                .position(markers[0])
                .icon(viewModel.bitmapDescriptorFromVector(requireContext(), R.drawable.ic_largada)) //largada
        )
        map.addMarker(
            MarkerOptions()
                .position(markers[1])
                .icon(viewModel.bitmapDescriptorFromVector(requireContext(), R.drawable.ic_chegada)) //chegada
        )
    }

    private fun movimentoCamera(map: GoogleMap){
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(viewModel.camera, 13f))
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.deletarDBDesatualizado()
    }

    // Checks if users have given their location and sets location enabled if so.
    @SuppressLint("MissingPermission")
    private fun enableMyLocation(map: GoogleMap) {
        val foregroundCheck =
            ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION)

        if (foregroundCheck == PackageManager.PERMISSION_GRANTED) {
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

    private fun setMapStyle(map: GoogleMap) {
        try {
            val success = map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    requireContext(),
                    R.raw.map_style
                )
            )

            if (!success) {
                Log.e( "Failed Parsing Style", "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Log.e("Error Parsing Style", "Can't find style. Error: ", e)
        }
    }
}