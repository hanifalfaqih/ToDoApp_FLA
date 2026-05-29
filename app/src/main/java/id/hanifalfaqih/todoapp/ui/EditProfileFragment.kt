package id.hanifalfaqih.todoapp.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import id.hanifalfaqih.todoapp.R

class EditProfileFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)
        
        ivProfile = view.findViewById(R.id.iv_edit_profile_img)
        val btnBack = view.findViewById<ImageButton>(R.id.btn_back)
        val btnChangePhoto = view.findViewById<View>(R.id.btn_change_photo_edit)
        val btnSave = view.findViewById<View>(R.id.btn_save_profile)

        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        
        btnChangePhoto.setOnClickListener {
            getImage.launch("image/*")
        }
        
        btnSave.setOnClickListener {
            // Logic to save profile changes
            findNavController().popBackStack()
        }
        
        return view
    }
}