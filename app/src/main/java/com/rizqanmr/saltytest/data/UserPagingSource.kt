package com.rizqanmr.saltytest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rizqanmr.saltytest.data.model.UserItem
import com.rizqanmr.saltytest.data.repository.RemoteRepository

class UserPagingSource(
    private val remoteRepository: RemoteRepository
) : PagingSource<Int, UserItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = remoteRepository.getUsers(nextPageNumber)
            if (response.isSuccess) {
                val users = response.onSuccess { it.data }.getOrNull()
                return LoadResult.Page(
                    data = users!!.data,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (users.data.isEmpty()) null else nextPageNumber + 1
                )
            } else {
                val error = response.onFailure { it }
                return LoadResult.Error(error.exceptionOrNull()!!)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}