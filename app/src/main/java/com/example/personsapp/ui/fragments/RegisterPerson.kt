package com.example.personsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.personsapp.R
import com.example.personsapp.databinding.FragmentRegisterPersonBinding
import com.example.personsapp.ui.viewmodels.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterPerson : Fragment() {

   private lateinit var binding:FragmentRegisterPersonBinding
   private lateinit var viewModel: RegisterViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding= FragmentRegisterPersonBinding.inflate(inflater,container,false)

        binding.imageButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.saveButton.setOnClickListener {
            val name=binding.registerName.text.toString().trim()
            val number=binding.registerPhone.text.toString().trim()
            save(name,number)
        }


        return binding.root
    }


    fun save(name:String,num:String){
        if (name.isEmpty() || num.isEmpty()){
            Toast.makeText(requireContext(),"Boş alanları doldurunuz",Toast.LENGTH_SHORT).show()
        }
        if (num.length!=11){
            Toast.makeText(requireContext(),"Numara 11 haneli olmalıdır",Toast.LENGTH_SHORT).show()
        }else{
            viewModel.save(name,num)
            Toast.makeText(requireContext(),"Kişi eklendi",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: RegisterViewModel by viewModels()
        viewModel=tempViewModel
    }
}