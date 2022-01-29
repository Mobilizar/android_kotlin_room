package com.mobilizar.payproof.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mobilizar.payproof.R
import com.mobilizar.payproof.databinding.FragmentUpdateBinding
import com.mobilizar.payproof.model.Debit
import com.mobilizar.payproof.viewmodel.DebitViewModel


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args: UpdateFragmentArgs by navArgs()
    private lateinit var mDebitViewModel: DebitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mDebitViewModel = ViewModelProvider(this)[DebitViewModel::class.java]

        //Add menu bar
        setHasOptionsMenu(true)

        binding.updateDescriptionEt.setText(args.currentDebit.description)

        binding.updateButton.setOnClickListener() {
            updateDebit()
        }

        return binding.root
    }

    private fun updateDebit() {
        val description = binding.updateDescriptionEt.text.toString()

        if (inputCheck(description)) {
            //Update Debit Object
            val debit = Debit(args.currentDebit.id, description, false)
            // Update Current Debit
            mDebitViewModel.updateDebit(debit)

            Toast.makeText(requireContext(), "Atualizado com sucesso!", Toast.LENGTH_LONG).show()

            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Preencha os campos", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(description: String): Boolean {
        return !(TextUtils.isEmpty(description))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteDebit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteDebit() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mDebitViewModel.deleteDebit(args.currentDebit)
            Toast.makeText(
                requireContext(),
                "Removido com sucesso: ${args.currentDebit.description}",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("Não") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentDebit.description}?")
        builder.setMessage("Tem certeza de que deseja excluir ${args.currentDebit.description}?")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}