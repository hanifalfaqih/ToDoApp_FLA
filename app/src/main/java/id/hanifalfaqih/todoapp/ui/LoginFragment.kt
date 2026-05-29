package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<View>(R.id.btn_login).setOnClickListener {
            // Navigate to Dashboard
            findNavController().navigate(R.id.navigation_notes)
        }

        view.findViewById<TextView>(R.id.tv_register_link).setOnClickListener {
            findNavController().navigate(R.id.navigation_register)
        }

        return view
    }
}