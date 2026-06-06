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
import id.hanifalfaqih.todoapp.di.AppModule
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.presentation.auth.register.RegisterViewModel
import id.hanifalfaqih.todoapp.presentation.common.Event
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels {
        AppModule.provideViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val etUsername = view.findViewById<EditText>(R.id.et_username)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)

        view.findViewById<View>(R.id.btn_register).setOnClickListener {
            val username = etUsername.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            // We pass username as 'name' as well per user request
            viewModel.register(username, username, email, password)
        }

        view.findViewById<TextView>(R.id.tv_login_link).setOnClickListener {
            findNavController().popBackStack()
        }

        observeViewModel()

        return view
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is Event.NavigateToLogin -> {
                            findNavController().popBackStack()
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