package id.hanifalfaqih.todoapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R
import id.hanifalfaqih.todoapp.di.AppModule
import id.hanifalfaqih.todoapp.di.ViewModelFactory
import id.hanifalfaqih.todoapp.presentation.profile.ProfileViewModel
import java.io.File
import java.io.FileOutputStream

class EditProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels {
        AppModule.provideViewModelFactory(requireContext())
    }

    private lateinit var ivProfile: ImageView

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            ivProfile.setImageURI(it)
            saveUriToFile(it)?.let { file ->
                viewModel.updatePhoto(file)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        
        ivProfile = view.findViewById(R.id.iv_edit_profile_img)
        val etName = view.findViewById<EditText>(R.id.et_edit_name)
        val etEmail = view.findViewById<EditText>(R.id.et_edit_email)
        val etOldPass = view.findViewById<EditText>(R.id.et_old_password)
        val etNewPass = view.findViewById<EditText>(R.id.et_new_password)
        
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        val btnChangePhoto = view.findViewById<View>(R.id.btn_change_photo_edit)
        val btnSave = view.findViewById<View>(R.id.btn_save_profile)
        val btnDeleteAccount = view.findViewById<View>(R.id.btn_delete_account)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        
        btnChangePhoto.setOnClickListener {
            getImage.launch("image/*")
        }

        ivProfile.setOnLongClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Remove Photo")
                .setMessage("Delete profile photo?")
                .setPositiveButton("Delete") { _, _ ->
                    ivProfile.setImageResource(R.drawable.ic_person_24)
                    viewModel.deletePhoto()
                }
                .setNegativeButton("Cancel", null)
                .show()
            true
        }
        
        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val oldPass = etOldPass.text.toString()
            val newPass = etNewPass.text.toString()

            if (name.isNotBlank() && email.isNotBlank()) {
                viewModel.updateProfile(name, email)
            }

            if (oldPass.isNotBlank() && newPass.isNotBlank()) {
                viewModel.changePassword(oldPass, newPass)
            }

            findNavController().popBackStack()
        }

        btnDeleteAccount.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Delete Account")
                .setMessage("Permanently delete your account?")
                .setPositiveButton("Delete") { _, _ ->
                    viewModel.deleteAccount()
                    findNavController().navigate(R.id.navigation_login)
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
        
        return view
    }

    private fun saveUriToFile(uri: Uri): File? {
        return try {
            val inputStream = requireContext().contentResolver.openInputStream(uri)
            val file = File(requireContext().cacheDir, "profile_temp.jpg")
            val outputStream = FileOutputStream(file)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            file
        } catch (e: Exception) {
            null
        }
    }
}