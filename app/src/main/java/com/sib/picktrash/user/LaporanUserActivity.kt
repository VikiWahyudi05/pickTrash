package com.sib.picktrash.user

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.sib.picktrash.databinding.ActivityLaporanUserBinding
import java.util.*
import kotlin.collections.HashMap

class LaporanUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USER = "extra_user"
        const val EXTRA_LATITUDE = "extra_latitude"
        const val EXTRA_LONGITUDE = "extra_longitude"
    }

    private lateinit var binding: ActivityLaporanUserBinding
    private lateinit var imageUri: Uri

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaporanUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_USER)

        binding.detailName.setText(name)

        binding.btnKirim.setOnClickListener {
            createReport();
        }

        binding.btnUpload.setOnClickListener {
            selectImage()
        }

    }


    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            imageUri = data?.data!!
            binding.imgSampah.setImageURI(imageUri)
        }
    }

    private fun createReport() {
        binding.apply {
            val name = detailName.text.toString()
            val description = detailDescription.text.toString()
            val alamat = detailAlamat.text.toString()

            if (name.isEmpty()) detailName.error = "Nama tidak boleh kosong"
            if (description.isEmpty()) detailDescription.error = "Deskripsi tidak boleh kosong"
            if (alamat.isEmpty()) detailAlamat.error = "Alamat tidak boleh kosong"
            else {
                saveReport()
            }
        }
    }

    private fun saveReport() {

        val db = FirebaseFirestore.getInstance()

        val latitude = intent.getDoubleExtra(EXTRA_LATITUDE, 0.0)
        val longitude = intent.getDoubleExtra(EXTRA_LONGITUDE, 0.0)

        val fileName = UUID.randomUUID().toString() + ".jpg"

        auth = FirebaseAuth.getInstance()
        val fUser = auth.currentUser

        val refStorage = FirebaseStorage.getInstance().reference.child("images/$fileName")

        refStorage.putFile(imageUri)
            .addOnSuccessListener(
                OnSuccessListener<UploadTask.TaskSnapshot> { taskSnapshot ->
                    taskSnapshot.storage.downloadUrl.addOnSuccessListener {
                        val imageUrl = it.toString()

                        val hashMap: HashMap<String, Any> = HashMap()

                        binding.apply {
                            hashMap["id"] = fUser!!.uid
                            hashMap["name"] = detailName.text.toString()
                            hashMap["description"] = detailDescription.text.toString()
                            hashMap["latitude"] = latitude
                            hashMap["longitude"] = longitude
                            hashMap["imageUrl"] = imageUrl
                            hashMap["alamat"] = detailAlamat.text.toString()
                            hashMap["status"] = "0"
                        }
                        db.collection("report")
                            .add(hashMap)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Laporan Terkirim", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener {
                                Toast.makeText(this, "Laporan Gagal Terkirim", Toast.LENGTH_SHORT)
                                    .show()
                            }


                    }
                })

            .addOnFailureListener(OnFailureListener { e ->
                print(e.message)
            })
    }

}