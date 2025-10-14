package com.example.myapplication.ui.irregular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.databinding.FragmentIrregularVerbsBinding

class IrregularVerbsFragment : Fragment() {

    private var _binding: FragmentIrregularVerbsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIrregularVerbsBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application))
            .get(IrregularVerbsViewModel::class.java)

        val adapter = IrregularVerbsAdapter()
        binding.recyclerViewVerbs.adapter = adapter
        binding.recyclerViewVerbs.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        viewModel.verbs.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}