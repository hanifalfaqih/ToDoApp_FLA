package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.presentation.auth.login.LoginViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        val etUsername = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)

        view.findViewById<View>(R.id.btn_login).setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            viewModel.login(username, password)
        }

        view.findViewById<TextView>(R.id.tv_register_link).setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is Event.NavigateToHome -> {
                            findNavController().navigate(R.id.action_login_to_notes)
                        }
                        is Event.ShowMessage -> {
                            Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> Unit
                    }
                }
            }
        }
    }
}