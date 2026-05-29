package id.hanifalfaqih.todoapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R

class ProfileFragment : Fragment() {

    private lateinit var ivProfile: ImageView

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            ivProfile.setImageURI(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        ivProfile = view.findViewById(R.id.iv_profile)
        val btnChangePhoto = view.findViewById<View>(R.id.btn_edit_photo)
        val btnEditProfile = view.findViewById<Button>(R.id.btn_edit_profile)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        btnChangePhoto.setOnClickListener {
            getImage.launch("image/*")
        }

        btnEditProfile.setOnClickListener {
            findNavController().navigate(R.id.action_profile_to_editProfile)
        }

        btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Logout") { _, _ ->
                    Toast.makeText(context, "Logged out", Toast.LENGTH_SHORT).show()
                    // Add actual logout logic here (e.g., clear preferences, navigate to login)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        return view
    }
}