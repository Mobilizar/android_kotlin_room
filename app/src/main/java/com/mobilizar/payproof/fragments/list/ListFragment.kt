package com.mobilizar.payproof.fragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobilizar.payproof.R
import com.mobilizar.payproof.databinding.FragmentListBinding
import com.mobilizar.payproof.viewmodel.DebitViewModel


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var mDebitViewModel: DebitViewModel






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListBinding.inflate(inflater, container, false)



        //Add menu bar
        setHasOptionsMenu(true)

        //RecyclerView
        val adapter = ListAdapter()
        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //DebitViewModel
        mDebitViewModel = ViewModelProvider(this).get(DebitViewModel::class.java)
        mDebitViewModel.readAllData().observe(viewLifecycleOwner, Observer { debit ->
            adapter.setData(debit)
        })

        binding.floatingActionButton.setOnClickListener() {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                deleteALLDebits()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteALLDebits() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Sim") { _, _ ->
            mDebitViewModel.deleteAllDebits()
            Toast.makeText(
                requireContext(),
                "Removido com sucesso todos os debitos",
                Toast.LENGTH_LONG
            ).show()
        }
        builder.setNegativeButton("Não") { _, _ ->

        }
        builder.setTitle("Deletar tudo?")
        builder.setMessage("Tem certeza que deseja excluir todos os debitos?")
        builder.create().show()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}