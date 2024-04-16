package com.assignment.assignmentapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.assignment.assignmentapp.api.RetrofitService
import com.assignment.assignmentapp.models.Data

class DataPagingSource(private val retrofitService: RetrofitService,
                       ) : PagingSource<Int, Data>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
            return try {
                val position = params.key ?: 1
                val response = retrofitService.getAllPosts(position)

                return LoadResult.Page(
                    data = response.body()!!,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == 10) null else position + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }
}