package com.weigner.instagram

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.canhub.cropper.BitmapCroppingWorkerJob
import com.weigner.instagram.databinding.FragmentImageCropperBinding
import java.io.File
import java.io.FileOutputStream


class ImageCropperFragment : Fragment() {

    private var _binding: FragmentImageCropperBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageCropperBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uri = arguments?.getParcelable<Uri>(KEY_URI)

        binding.run {
            cropIv.setAspectRatio(1, 1)
            cropIv.setFixedAspectRatio(true)
            cropIv.setImageUriAsync(uri)

            btCancel.setOnClickListener {
                findNavController().navigateUp()
                //findNavController().backQueue.first()
            }

            btSave.setOnClickListener {
                val dir = requireContext().getDir("profile", Context.MODE_PRIVATE)
                if (!dir.exists()) {
                    dir.mkdir()
                }
                val myPath = File(dir, System.currentTimeMillis().toString() + ".jpeg")
                val fos = FileOutputStream(myPath)
                val bitmap = cropIv.croppedImage
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                fos.close()
                cropIv.onImageCroppingAsyncComplete(BitmapCroppingWorkerJob.Result(bitmap, 100))
            }

            /*btSave.setOnClickListener {
                val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                if (dir != null) {
                    val uriSaved = Uri.fromFile(File(dir.path, System.currentTimeMillis().toString() + ".jpeg"))
                    cropIv.setImageUriAsync(uriSaved)
                    cropIv.imageUri
                }
            }*/

            cropIv.setOnCropImageCompleteListener { view, result ->
                Log.d("result uri", result.uriContent.toString())
                setFragmentResult("cropKey", bundleOf(KEY_IMAGE to result.bitmap))

                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val KEY_URI = "key_uri"
        const val KEY_IMAGE = "key_image"
    }
}