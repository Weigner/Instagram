package com.weigner.instagram.register.photo

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.weigner.instagram.ImageCropperFragment
import com.weigner.instagram.ImageCropperFragment.Companion.KEY_URI
import com.weigner.instagram.R
import com.weigner.instagram.util.CustomDialog
import com.weigner.instagram.databinding.FragmentRegisterUploadPhotoBinding
import com.weigner.instagram.di.DependencyInjector
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RegisterUploadPhotoFragment : Fragment(), RegisterPhoto.View {

    private var _binding: FragmentRegisterUploadPhotoBinding? = null
    private val binding get() = _binding!!

    override lateinit var presenter: RegisterPhoto.Presenter

    private lateinit var currentPhotoUri: Uri

    private val getCamera = registerForActivityResult(ActivityResultContracts.TakePicture()) { saved ->
        if (saved) {
            goToCropImage(currentPhotoUri)
        }
    }

    private val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        Log.d("Teste imagem", uri.toString())
        if (uri != null) {
            goToCropImage(uri)
        }
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

        val repository = DependencyInjector.registerEmailRepository()

        presenter = RegisterPhotoPresenter(this, repository)

        binding.btNext.isEnabled = true
        binding.btNext.setOnClickListener {
           showDialog()
        }

        binding.btJump.setOnClickListener {
            goToHome()
        }
    }

    override fun onDestroy() {
        _binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btNext.showProgress(enabled)
    }

    override fun onUpdateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onUpdateSuccess() {
        goToHome()
    }

    private fun showDialog() {
        val customDialog = CustomDialog(requireContext())
        customDialog.addButton(R.string.add_photo, R.string.gallery) {
            when(it.id) {
                R.string.add_photo -> {
                    goToCamera()
                }
                R.string.gallery -> {
                    //(activity as MainActivity).goToGallery()
                    goToGallery()
                }
            }
        }
        customDialog.show()
    }

    private fun goToCropImage(uri: Uri) {
        val args = Bundle().apply {
            putParcelable(KEY_URI, uri)
        }
        findNavController().navigate(R.id.action_nav_register_upload_photo_to_imageCropperFragment, args)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_nav_register_upload_photo_to_nav_home)
        //findNavController().navigateUp()
    }

    private fun goToCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (e: IOException) {
                Log.w("UploadPhotoFragment", e.message, e)
                null
            }

            photoFile?.also {
                val photoUri = FileProvider.getUriForFile(requireContext(), "com.weigner.instagram.fileprovider", it)
                currentPhotoUri = photoUri

                getCamera.launch(photoUri)
            }
        }
    }

    private fun goToGallery() {
        getContent.launch("image/*")
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val dir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}_", ".jpg", dir)
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
        presenter.updateUser(currentPhotoUri)
    }
}