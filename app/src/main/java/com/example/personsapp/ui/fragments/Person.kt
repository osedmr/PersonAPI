package com.example.personsapp.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.airbnb.lottie.LottieDrawable
import com.example.personsapp.R
import com.example.personsapp.data.entity.Kisiler

import com.example.personsapp.databinding.FragmentPersonBinding
import com.example.personsapp.ui.adapter.KisilerAdapter
import com.example.personsapp.ui.viewmodels.PersonViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Person : Fragment() {
    private lateinit var binding:FragmentPersonBinding
    private lateinit var viewModel: PersonViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View{

        binding= FragmentPersonBinding.inflate(inflater,container,false)

        binding.personRV.layoutManager=LinearLayoutManager(requireContext())

        lottieIcon()

        viewModel.kisilerListesi.observe(viewLifecycleOwner){
            val kisilerAdapter=KisilerAdapter(requireContext(), it.toMutableList(),viewModel)
            binding.personRV.adapter=kisilerAdapter
        }

        binding.addButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.personToRegisterPerson)

        }
        binding.searchView.setOnQueryTextListener(object :OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.search(newText)
                }
                return true
            }
        })

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel:PersonViewModel by viewModels()
        viewModel=tempViewModel
    }

    fun lottieIcon(){
        binding.addButton.apply {
            setAnimation(R.raw.fab)
            playAnimation()
            repeatCount= LottieDrawable.INFINITE
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.kisileriYukle()
    }
}