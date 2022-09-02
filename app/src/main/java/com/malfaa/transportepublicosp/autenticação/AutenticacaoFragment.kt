package com.malfaa.transportepublicosp.autenticação

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.malfaa.transportepublicosp.R

class AutenticacaoFragment : Fragment() {

    companion object {
        fun newInstance() = AutenticacaoFragment()
    }

    private lateinit var viewModel: AutenticacaoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_autenticacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}