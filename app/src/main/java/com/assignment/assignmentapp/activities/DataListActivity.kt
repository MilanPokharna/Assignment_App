package com.assignment.assignmentapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.paging.LoadState
import com.assignment.assignmentapp.R
import com.assignment.assignmentapp.adapters.DataListPagingAdapter
import com.assignment.assignmentapp.adapters.LoadAdapter
import com.assignment.assignmentapp.databinding.ActivityDataListBinding
import com.assignment.assignmentapp.utils.NetworkUtils
import com.assignment.assignmentapp.viewmodels.DataListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataListActivity : AppCompatActivity() {

    private val viewModel: DataListViewModel by viewModels()
    private lateinit var adapter : DataListPagingAdapter
    private lateinit var binding: ActivityDataListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.setHasFixedSize(true)
        adapter = DataListPagingAdapter(this)
        adapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE

                // getting the error
                val errorState = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                if(errorState == null){
                    binding.txtError.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.btnRetry.visibility = View.GONE
                }
                else {
                    errorState.let {
                        if (!NetworkUtils.isNetworkAvailable(this)) {
                            binding.txtError.setText(R.string.oops)
                            binding.txtError.visibility = View.VISIBLE
                            binding.btnRetry.visibility = View.VISIBLE
                        } else {
                            binding.txtError.setText(R.string.something_went_wrong)
                            binding.txtError.visibility = View.VISIBLE
                            binding.btnRetry.visibility = View.VISIBLE
                        }
                        Toast.makeText(this, it.error.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.btnRetry.setOnClickListener {
            adapter.retry()
        }


        binding.recyclerview.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoadAdapter { adapter.retry() },
            footer = LoadAdapter { adapter.retry() }
        )

        viewModel.customerList.observe(this) {
            if (it != null) {
                adapter.submitData(lifecycle, it)
                binding.progressBar.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.txtError.visibility = View.GONE
            }
        }

    }

}