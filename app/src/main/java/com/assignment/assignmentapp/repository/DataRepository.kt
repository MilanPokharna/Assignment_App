package com.assignment.assignmentapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.assignment.assignmentapp.api.RetrofitService
import com.assignment.assignmentapp.paging.DataPagingSource
import javax.inject.Inject


class DataRepository @Inject constructor(private val retrofitService: RetrofitService) {

        fun getData() = Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { DataPagingSource(retrofitService) }
        ).liveData

}