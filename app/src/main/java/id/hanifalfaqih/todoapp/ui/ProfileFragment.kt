package id.hanifalfaqih.todoapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.presentation.profile.ProfileViewModel

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

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
        val tvName = view.findViewById<TextView>(R.id.tv_user_name)
        val tvEmail = view.findViewById<TextView>(R.id.tv_user_email)
        val btnEditPhoto = view.findViewById<View>(R.id.btn_edit_photo)
        val btnEditProfile = view.findViewById<Button>(R.id.btn_edit_profile)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back_profile)

        tvName.text = viewModel.getUserName()
        tvEmail.text = viewModel.getUserEmail()

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        btnEditPhoto.setOnClickListener {
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
                    viewModel.logout()
                    findNavController().navigate(R.id.navigation_login)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        return view
    }
}