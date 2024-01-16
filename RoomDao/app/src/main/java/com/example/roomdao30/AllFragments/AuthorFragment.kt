package com.example.roomdao30.AllFragments

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdao30.AllAdapters.AuthorAdapter
import com.example.roomdao30.AllDialogForm.AuthorForm
import com.example.roomdao30.AllDialogForm.ClientForm
import com.example.roomdao30.AllEntitys.Author
import com.example.roomdao30.AllViewModels.AddClientViewModel
import com.example.roomdao30.AllViewModels.AuthorViewModel
import com.example.roomdao30.R
import kotlinx.android.synthetic.main.author_fragment.*
import kotlinx.android.synthetic.main.client_fragment.*

class AuthorFragment:Fragment(R.layout.author_fragment) {
    private val viewModel by viewModels<AuthorViewModel>()
    private var authorAdapter: AuthorAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authorAdapter = AuthorAdapter()
        with(author_list) {
            adapter = authorAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
                setHasFixedSize(true)
            }
        }
        viewModel.loadList()
        viewModel.recordAuthorData.observe(viewLifecycleOwner) { authorList ->
            authorAdapter!!.updateAuthorAdapter(authorList)
        }
        add_author.setOnClickListener {
            val dialogFragmentForAddAuthor = AuthorForm()
            dialogFragmentForAddAuthor.oNSuccessListener = { newAuthor1 ->
                addAuthor(newAuthor1)

            }
            dialogFragmentForAddAuthor.show(childFragmentManager, "authorDialog")

        }
        authorAdapter!!.updateAuthorAdapter(viewModel.recordAuthorData.value.orEmpty())

        show_author_list.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "${viewModel.saveErrorLiveData.value}",
                Toast.LENGTH_LONG
            ).show()
          updateList()
        }

    }
    fun updateList(){
        val transaction: FragmentTransaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, AuthorFragment.newInstance())
        transaction.commit()
    }

    private fun addAuthor(newAuthor: Author) {
        viewModel.save(newAuthor)
        Toast.makeText(
            requireContext(),
            "Добавление данных автора:${newAuthor.nameAuthor}",
            Toast.LENGTH_LONG
        ).show();
    }

    companion object {
        fun newInstance(): AuthorFragment {
            return AuthorFragment()
        }
    }
}