package com.example.mockshopeapp.ui.productsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.model.CategoryResponse
import com.example.mockshopeapp.databinding.FragmentProductListBinding
import com.example.mockshopeapp.ui.Adapter.ProductAdapter
import kotlinx.coroutines.launch


class ProductListFragment : Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    private lateinit var productAdapter: ProductAdapter
    private lateinit var viewModel: ProductsListViewModel
    private val jokeList: List<CategoryResponse> = listOf(CategoryResponse())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        setupLiveDataObservers()
    }

    private fun setupLiveDataObservers() {
        viewModel.getProduct()

        lifecycleScope.launch {
            viewModel.categories.collect{
                productAdapter.submitList(it)
                binding.productRecycler.adapter = productAdapter
            }
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductsListViewModel::class.java]
    }
}