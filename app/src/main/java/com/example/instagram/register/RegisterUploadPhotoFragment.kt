package com.example.instagram.register

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.instagram.ImageCropperFragment
import com.example.instagram.ImageCropperFragment.Companion.KEY_URI
import com.example.instagram.MainActivity
import com.example.instagram.R
import com.example.instagram.util.CustomDialog
import com.example.instagram.databinding.FragmentRegisterUploadPhotoBinding
import com.example.instagram.util.hideKeyboard

class RegisterUploadPhotoFragment : Fragment() {

    private var _binding: FragmentRegisterUploadPhotoBinding? = null
    private val binding get() = _binding!!

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        Log.d("Teste imagem", uri.toString())
        val args = Bundle().apply {
            putParcelable(KEY_URI, uri)
        }
        findNavController().navigate(R.id.action_nav_register_upload_photo_to_imageCropperFragment, args)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFragmentResultListener("cropKey") { requestKey, bundle ->  
            val bitmap = bundle.getParcelable<Bitmap>(ImageCropperFragment.KEY_IMAGE)
            onCropImageResult(bitmap)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterUploadPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btNext.isEnabled = true
        binding.btNext.setOnClickListener {
           showDialog()
        }

        binding.btJump.setOnClickListener {
            goToHome()
        }
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_nav_register_upload_photo_to_nav_home)
        //findNavController().navigateUp()
    }

    private fun showDialog() {
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.add_photo, R.string.gallery) {
            when(it.id) {
                R.string.add_photo -> {

                }
                R.string.gallery -> {
                    //(activity as MainActivity).goToGallery()
                    goToGallery()
                }
            }
        }
        customDialog.show()
    }

    private fun goToGallery() {
        getContent.launch("image/*")
    }

   /* private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
           val bitmap =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            } else {
                MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding.ivProfile.setImageBitmap(bitmap)
        }
    }*/

    private fun onCropImageResult(bitmap: Bitmap?) {
        if (bitmap != null) {
           binding.ivProfile.setImageBitmap(bitmap)
        }
    }
}