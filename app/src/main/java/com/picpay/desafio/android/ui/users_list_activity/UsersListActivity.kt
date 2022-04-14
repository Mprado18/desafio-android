package com.picpay.desafio.android.ui.users_list_activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.api.model.User
import com.picpay.desafio.android.interactor.GetUsersInteractor
import com.picpay.desafio.android.ui.users_list_activity.recyclerview.UserListAdapter
import com.picpay.desafio.android.ui.users_list_activity.viewmodel.UsersCommand
import com.picpay.desafio.android.ui.users_list_activity.viewmodel.UsersViewModel
import com.picpay.desafio.android.ui.utils.ToastNotification
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UsersListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter

    private val viewModel: UsersViewModel by viewModel {
        parametersOf(GetUsersInteractor())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_list)

        setupRecyclerView()
        setupProgressBar()

        viewModel.usersMutableLiveData.observe(this, Observer { users ->
            users?.let {
                adapter.notifyDataSetChanged()
                when (it) {
                    is UsersCommand.RenderUsers -> renderUsers(it.userList)
                    is UsersCommand.ShowRequestError -> showRequestError()
                }
            }
        })

        viewModel.getUsers()
    }

    private fun setupProgressBar() {
        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        adapter = UserListAdapter()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun renderUsers(usersAdd: List<User>) {
        progressBar.visibility = View.GONE
        adapter.users = usersAdd
    }

    private fun showRequestError() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.GONE

        ToastNotification().notification(this, getString(R.string.error))
    }

}
