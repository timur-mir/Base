package com.example.roomdao30.AllViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.AllRepositorys.AuthorRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthorViewModel:ViewModel() {
    private val authorRepository = AuthorRepository()
    private val getAuthorMutableLiveData = MutableLiveData<Author?>()

    val updateAuthorLiveData: LiveData<Author?>
        get() = getAuthorMutableLiveData

    private val requestAuthorsForClient = MutableLiveData<List<ClientWithAuthor>>()

    val requestAuthorList: LiveData<List<ClientWithAuthor>>
        get() = requestAuthorsForClient

    private val authorsMutableLiveData = MutableLiveData<List<Author>>()

    val recordAuthorData: LiveData<List<Author>>
        get() = authorsMutableLiveData

    private val saveSuccessLiveEvent = MutableLiveData<Unit>()

    val saveSuccessLiveData: LiveData<Unit>
        get() = saveSuccessLiveEvent

    private val saveErrorLiveEvent = MutableLiveData<Int>()

    val saveErrorLiveData: LiveData<Int>
        get() = saveErrorLiveEvent


    fun requestAuthors() {
        viewModelScope.launch {
            try {
                requestAuthorsForClient.postValue(authorRepository.requestAuthor())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в запросе автора $t ")
            }
        }
    }

    fun loadList() {
        viewModelScope.launch {
            try {
                authorsMutableLiveData.postValue(authorRepository.getAllAuthors())
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка в списке авторов ")
            }
        }
    }

    fun removeAuthorById(author: Int) {
        viewModelScope.launch {
            try {
             authorRepository.removeAuthorById(author)
            } catch (t: Throwable) {
                Timber.e(t)
            }

        }
    }

    fun getAuthorById(id: Int) {
        viewModelScope.launch {
            try {
                val author= authorRepository.getAuthorById(id)
                getAuthorMutableLiveData.postValue(author)
            } catch (t: Throwable) {
                Timber.e(t)
            }
        }
    }

    fun save(author: Author) {
        viewModelScope.launch {
            try {
                if (author.id == 0) {
                    authorRepository.saveAuthor(author)
                } else {
                    authorRepository.updateAuthor(author)
                }
                saveSuccessLiveEvent.postValue(Unit)
            } catch (t: Throwable) {
                Timber.e(t, "Ошибка сохранения автора")
                showError(t)
            }
        }
    }

    fun updateAuthor(author: Author) {
        viewModelScope.launch {
            try {
                authorRepository.updateAuthor(author)
                        } catch (t: Throwable) {
                Timber.e(t, "Ошибка обновления автора")
                showError(t)

            }

        }
    }

    private fun showError(t: Throwable) {
        saveErrorLiveEvent.postValue(2150)
    }
}