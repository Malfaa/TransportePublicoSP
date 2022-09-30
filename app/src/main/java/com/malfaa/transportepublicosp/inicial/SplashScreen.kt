package com.malfaa.transportepublicosp.inicial

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.malfaa.transportepublicosp.databinding.SplashScreenFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment(){

    private lateinit var binding : SplashScreenFragmentBinding
    private val viewModel: SplashScreenViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = SplashScreenFragmentBinding.inflate(inflater, container, false)

        viewModel.resultado.observe(viewLifecycleOwner){
            resultado ->
            if(resultado){
                Handler(Looper.getMainLooper()).postDelayed({
                    Log.d("Status", viewModel.autenticacao().toString())
                    findNavController().navigate(
                        SplashScreenDirections.actionSplashScreenToInformativoFragment())
                }, 2000)
            }else{
                Handler(Looper.getMainLooper()).postDelayed({
                    viewModel.autenticacao()
                }, 7000)

            }
        }
        return binding.root
    }
}