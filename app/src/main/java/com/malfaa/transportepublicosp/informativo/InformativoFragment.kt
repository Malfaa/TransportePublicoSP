package com.malfaa.transportepublicosp.informativo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.malfaa.transportepublicosp.databinding.FragmentInformativoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class InformativoFragment : Fragment() {

    private val viewModel: InformativoViewModel by viewModel()

    private lateinit var binding: FragmentInformativoBinding

    companion object{
        const val EDIT_TEXT_STATE = "EDIT_TEXT_STATE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformativoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState != null){
            binding.busSearch.setText(savedInstanceState.getString(EDIT_TEXT_STATE))
        }

        val adapter = InformativoAdapter(
            InformativoAdapter.LinhaListener { linha ->
                findNavController().navigate(
                    InformativoFragmentDirections.actionInformativoFragmentToMapsFragment(linha) //fazer a conversão da linha para os onibus(posicao)((Onibus))
                )
            }
        )

        binding.recyclerView.adapter = adapter

        viewModel.linhas.observe(viewLifecycleOwner){
                linhas ->
            adapter.submitList(linhas)
        }

        binding.pesquisar.setOnClickListener{
            viewModel.apagarDadosVencidos()
            viewModel.pesquisarLinha()
            binding.busSearch.text.clear()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val item = binding.busSearch.text.toString()
        super.onSaveInstanceState(outState)
        outState.putString(EDIT_TEXT_STATE, item)
    }
}