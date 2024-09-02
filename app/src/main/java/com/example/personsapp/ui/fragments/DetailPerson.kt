package com.example.personsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.personsapp.databinding.FragmentDetailPersonBinding
import com.example.personsapp.ui.viewmodels.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPerson : Fragment() {

    private lateinit var binding:FragmentDetailPersonBinding
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentDetailPersonBinding.inflate(inflater,container,false)


       val bundle: DetailPersonArgs by navArgs()

        binding.detailName.setText(bundle.kisi.kisi_ad)
        binding.detailPhone.setText(bundle.kisi.kisi_tel)

        binding.updateButton.setOnClickListener {
            val kisi=binding.detailName.text.toString().trim()
            val number=binding.detailPhone.text.toString().trim()
            update(bundle.kisi.kisi_id,kisi,number)
        }
        binding.imageButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    fun update(kisi_id:Int, kisi_ad:String, kisi_tel:String){
        viewModel.update(kisi_id,kisi_ad,kisi_tel)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: DetailViewModel by viewModels()
        viewModel = tempViewModel
    }
}