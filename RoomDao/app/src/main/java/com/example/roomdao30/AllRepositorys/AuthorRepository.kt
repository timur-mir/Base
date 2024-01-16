package com.example.roomdao30.AllRepositorys

import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllRelation.ClientWithAuthor
import com.example.roomdao30.DB.Database

class AuthorRepository {
    private val authorDao = Database.INSTANCE!!.authorDao()
    private val clientDao = Database.INSTANCE!!.clientDao()

    suspend fun saveAuthor(author: Author) {
        authorDao.insertAuthor(author)
    }

    suspend
    fun updateAuthor(author: Author) {
        authorDao.updateAuthor(author)
    }

    suspend fun removeAuthor(author:Author) {
        authorDao.removeAuthor(author)
    }

    suspend fun removeAuthorById(authorId: Int) {
        authorDao.removeAuthorById(authorId)
    }

    suspend fun getAuthorById(authorId: Int): Author? {
        return authorDao.getAuthorById(authorId)

    }

    suspend fun getAllAuthors(): List<Author> {
        return authorDao.getAllAuthors()
    }
    suspend
    fun requestAuthor():List<ClientWithAuthor> {
        return clientDao.getAllAuthorsWithRelations()
    }

}