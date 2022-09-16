package com.example.poten.Board

import android.Manifest
import android.content.ContentUris
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.poten.Board.model.BoardResponse
import com.example.poten.R
import com.example.poten.Utils.RetrofitClient
import com.example.poten.databinding.ActivityClubMyPageBinding
import com.example.poten.dto.BoardForm
import com.example.poten.dto.SessionResponse
import com.example.poten.interfaces.BoardApi
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class CreatePostActivity : AppCompatActivity() {
    val PERM_Album = 101
    val REQUEST_STORAGE = 80

    private lateinit var et_postContent : EditText
    private lateinit var et_postClubName : EditText
    private lateinit var btn_postSubmit : Button
    private lateinit var btn_uploadPostFile : Button
    lateinit var iv_uploadImg : ImageView
    lateinit var uploadPicUri: Uri

    // back press
    private lateinit var post_backArroy : ImageView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)

        et_postContent = findViewById(R.id.et_postClubName)
        et_postClubName = findViewById(R.id.et_postClubName)
        btn_postSubmit = findViewById(R.id.btn_postSubmit)
        btn_uploadPostFile = findViewById(R.id.btn_uploadPostFile)
        iv_uploadImg = findViewById(R.id.iv_uploadImg)
        post_backArroy = findViewById(R.id.post_backArroy)


        // 뒤로 가기 버튼
        post_backArroy.setOnClickListener(View.OnClickListener {
            finish()
        })

        // 사진 업로드 버튼 클릭
        btn_uploadPostFile.setOnClickListener(View.OnClickListener {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), PERM_Album)
        })

        // upload 버튼 클릭
        btn_postSubmit.setOnClickListener {
            Log.i("BOARD", "버튼 눌림")
            val content = et_postContent.text.toString()
            val clubName = et_postClubName.text.toString()
            val clubId = 1L;

            Log.i("CREATEBOARD", "edittext : "+ content)

            // 파일 처리
            val file = File(getRealPathFromURI(this.applicationContext, uploadPicUri))
            var fileName = file.name
            var requestBody : RequestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(uploadPicUri)), file)
            var multipartBody : MultipartBody.Part = MultipartBody.Part.createFormData("pics",fileName,requestBody)

//            val request = BoardForm(1L, content)
            saveBoard(multipartBody, clubId, content)
        }
    }

//    private fun saveBoard(multipartBody : MultipartBody.Part, request: BoardForm) {
    private fun saveBoard(multipartBody : MultipartBody.Part, clubId:Long, content:String) {
        var retrofit = RetrofitClient.create(BoardApi::class.java,RetrofitClient.getAuth())

        Log.i("CREATEBOARD", "saveBoard : "+ content)

        retrofit.saveBoard(multipartBody, clubId, content).enqueue(object : Callback<BoardResponse> {
            override fun onResponse(call: Call<BoardResponse>, response: Response<BoardResponse>) {
                Log.i("BOARD", "save board 성공"+ response.body().toString())
                // 팝업창
                val builder = AlertDialog.Builder(this@CreatePostActivity)
                builder
                    .setTitle("피드 업로드")
                    .setMessage("피드 업로드가 완료되었습니다.")
                    .setPositiveButton("확인",
                        DialogInterface.OnClickListener { dialog, id ->
                            // 확인 버튼 선택시 수행
                            finish()
                        })
                builder.create()
                builder.show()
            }

            override fun onFailure(call: Call<BoardResponse>, t: Throwable) {
                Log.e("BOARD", "save board 실패"+t.message.toString())
            }
        })
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            permissionGranted(requestCode)
        } else{
            permissionDenided(requestCode)
        }
    }

    private fun permissionGranted(requestCode: Int){
        when (requestCode) {
            PERM_Album -> openGallery()
        }
    }

    private fun permissionDenided(requestCode: Int){
        when (requestCode) {
            PERM_Album -> Toast.makeText(
                this,
                "저장소 권한을 승인해야 앨범에서 이미지를 불러올 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQUEST_STORAGE)

    }

    fun getRealPathFromURI(context: Context, uri: Uri): String? {

        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                return if ("primary".equals(type, ignoreCase = true)) {
                    (Environment.getExternalStorageDirectory().toString() + "/"
                            + split[1])
                } else {
                    val SDcardpath = getRemovableSDCardPath(context)?.split("/Android".toRegex())!!.toTypedArray()[0]
                    SDcardpath + "/" + split[1]
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id))
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split: Array<String?> = docId.split(":".toRegex()).toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection,
                    selectionArgs)
            }
        } else if (uri != null) {
            if ("content".equals(uri.getScheme(), ignoreCase = true)) {
                // Return the remote address
                return if (isGooglePhotosUri(uri)) uri.getLastPathSegment() else getDataColumn(context, uri, null, null)
            } else if ("file".equals(uri.getScheme(), ignoreCase = true)) {
                return uri.getPath()
            }
        }
        return null
    }


    fun getRemovableSDCardPath(context: Context?): String? {
        val storages = ContextCompat.getExternalFilesDirs(context!!, null)
        return if (storages.size > 1 && storages[0] != null && storages[1] != null) storages[1].toString() else ""
    }


    fun getDataColumn(
        context: Context, uri: Uri?,
        selection: String?, selectionArgs: Array<String?>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri!!, projection,
                selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index: Int = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null) cursor.close()
        }
        return null
    }

    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri
            .authority
    }


    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri
            .authority
    }


    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri
            .authority
    }


    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri
            .authority
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_STORAGE -> {
                    data?.data?.let { uri ->
                        iv_uploadImg.setImageURI(uri)
//                        println("갤러리 이미지 uri : " + uri)
                        Log.i("UPLOAD", "갤러리 이미지 uri : " + uri)
                         uploadPicUri = uri
                    }
                }
            }
        }
    }

}