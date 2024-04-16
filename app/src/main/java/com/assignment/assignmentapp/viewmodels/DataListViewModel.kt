package com.assignment.assignmentapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.assignment.assignmentapp.models.Data
import com.assignment.assignmentapp.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataListViewModel
@Inject constructor(private val dataRepository: DataRepository) : ViewModel(){


    var customerList = dataRepository.getData().cachedIn(viewModelScope)

    suspend fun fetchCustomers() :LiveData<PagingData<Data>>  {

        return dataRepository.getData().cachedIn(viewModelScope)
    }

}
