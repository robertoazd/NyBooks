//package com.example.nybooks.presentation.books
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.example.nybooks.data.BooksResult
//import com.example.nybooks.data.model.Book
//import com.example.nybooks.data.repository.BooksRepository
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class BooksViewModelTest {
//
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    @Mock
//    private lateinit var booksLiveDataObserver: androidx.lifecycle.Observer<List<Book>>
//
//    @Mock
//    private lateinit var viewFlipperLiveDataObserver: androidx.lifecycle.Observer<Pair<Int, Int?>>
//
//    private lateinit var viewModel: BooksViewModel
//
//    // para inicializar o test uma forma
//    /*@Before
//    fun setup() {
//        MockitoAnnotations.initMocks(this)
//    }*/
//
//    @Test
//    fun `when view model getBooks get success then sets booksliveData`() {
//        // Arrange
//        val books = listOf(
//            Book("Title 1", "Author 1", "Description 1")
//        )
//
//        val resultSuccess = MockRepository(BooksResult.Success(books))
//        viewModel = BooksViewModel(resultSuccess)
//        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
//        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)
//
//        // Act
//        viewModel.getBooks()
//
//        // Assert
//        verify(booksLiveDataObserver).onChanged(books)
//        verify(viewFlipperLiveDataObserver).onChanged(1, null)
//    }
//}
//
//class MockRepository(private val result: BooksResult) : BooksRepository {
//    override fun getBooks(booksResultCallback: (result: BooksResult) -> Unit) {
//        booksResultCallback(result)
//    }
//}