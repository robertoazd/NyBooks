package com.example.nybooks.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.nybooks.R
import com.example.nybooks.data.APIService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import com.example.nybooks.presentation.books.BooksViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksAPIDataSource : BooksRepository {

    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()
    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
        APIService.service.getBooks().enqueue(object: Callback<BookBodyResponse> {
            override fun onResponse(
                call: Call<BookBodyResponse>,
                response: Response<BookBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val books: MutableList<Book> = mutableListOf()

                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResults) {
                                val book = result.bookDetailsResponse[0].getBookModel()
                                books.add(book)
                            }
                        }
                        booksResultCallback(BooksResult.Success(books))
                    }
                    response.code() == 401 -> {
                        viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_BOOKS, R.string.books_error_401)
                    }
                    else -> {
                        viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_ERROR, R.string.books_error_400_generic)
                    }
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(BooksViewModel.VIEW_FLIPPER_ERROR, R.string.books_error_500_generic)
            }
        })
    }
}
