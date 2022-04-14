package com.picpay.desafio.android.ui.users_list_activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.api.model.User
import com.picpay.desafio.android.databinding.ActivityUsersListBinding
import com.picpay.desafio.android.interactor.GetUsersInteractor
import com.picpay.desafio.android.ui.users_list_activity.recyclerview.UserListAdapter
import com.picpay.desafio.android.ui.users_list_activity.viewmodel.UsersCommand
import com.picpay.desafio.android.ui.users_list_activity.viewmodel.UsersViewModel
import com.picpay.desafio.android.ui.utils.ToastNotification
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UsersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersListBinding
    private lateinit var userListAdapter: UserListAdapter

    private val viewModel: UsersViewModel by viewModel {
        parametersOf(GetUsersInteractor())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        val viewBinding = binding.root
        setContentView(viewBinding)

        setupRecyclerView()
        setupProgressBar()
        observe()

        viewModel.getUsers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observe() {
        viewModel.usersMutableLiveData.observe(this, Observer { users ->
            users?.let {
                userListAdapter.notifyDataSetChanged()
                when (it) {
                    is UsersCommand.RenderUsers -> renderUsers(it.userList)
                    is UsersCommand.ShowRequestError -> showRequestError()
                }
            }
        })
    }

    private fun setupProgressBar() = with(binding) {
        userListProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() = with(binding) {
        userListAdapter = UserListAdapter()

        with(recyclerView) {
            adapter = userListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun renderUsers(usersList: List<User>) = with(binding) {
        userListProgressBar.visibility = View.GONE
        userListAdapter.users = usersList
    }

    private fun showRequestError() = with(binding) {
        userListProgressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        ToastNotification().notification(
            this@UsersListActivity,
            getString(R.string.error)
        )
    }

}
