package com.mobilizar.payproof.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mobilizar.payproof.R
import com.mobilizar.payproof.databinding.FragmentAddBinding
import com.mobilizar.payproof.model.Debit
import com.mobilizar.payproof.viewmodel.DebitViewModel


class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mDebitViewModel: DebitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mDebitViewModel = ViewModelProvider(this)[DebitViewModel::class.java]

        binding.addButton.setOnClickListener() {
            insertDataToDataBase()
        }

        return binding.root
    }

    private fun insertDataToDataBase() {
        val description = binding.addDescriptionEt.text.toString()

        if (inputCheck(description)) {
            //Create Debit Object
            val debit = Debit(0, description, false)
            // Add Data to Database
            mDebitViewModel.addDebit(debit)
            Toast.makeText(requireContext(), "Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(description: String): Boolean {
        return !(TextUtils.isEmpty(description))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}