package com.malfaa.transportepublicosp.inicial

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.malfaa.transportepublicosp.R
import com.malfaa.transportepublicosp.databinding.SplashScreenFragmentBinding

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

//        binding.onibus.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.shake_animation)

        Handler(Looper.getMainLooper()).postDelayed({
            Result.success(viewModel.autentica()).run {
                findNavController().navigate(
                    SplashScreenDirections.actionSplashScreenToInformativoFragment())
            }
        }, 1800)

        return binding.root

        //autenticar por aqui, fazer com que faça o post e o resultado dele é boolean, colocar no Result que dará procedência ao informativo fragment
        //fazer a autenticacao
    }
}