package id.hanifalfaqih.todoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.findViewById<ImageButton>(R.id.btn_back_profile).setOnClickListener {
            findNavController().popBackStack()
        }

        view.findViewById<Button>(R.id.btn_edit_profile).setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_editProfile)
        }

        view.findViewById<Button>(R.id.btn_logout).setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Logout") { _, _ ->
                    // Logout logic
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        return view
    }
}