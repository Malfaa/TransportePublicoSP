package com.malfaa.transportepublicosp.informativo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.malfaa.transportepublicosp.R

class InformativoFragment : Fragment() {

//    private val viewModel: InformativoViewModel by viewModel<InformativoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_informativo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
//Models -> Passar os dados da interwebs(api) e popular o adapter com tais infos