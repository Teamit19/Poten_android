package com.example.poten

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.poten.Board.HomeActivity
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.databinding.ActivityMainBinding
import com.example.poten.dto.Test2
import com.example.poten.dto.Test
import com.example.poten.interfaces.RetrofitService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.kakao.sdk.common.util.Utility
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class MainActivity : AppCompatActivity() {
    val PERM_Album = 101
    val REQUEST_STORAGE = 80
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    lateinit var button0 : Button
    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var restText : TextView
    lateinit var imageView : ImageView
    lateinit var imageView2: ImageView
    lateinit var gson: Gson
    lateinit var retrofit: Retrofit
    lateinit var service: RetrofitService
    lateinit var uriTest: Uri

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val keyHash = Utility.getKeyHash(this)
        Log.d("Hash", keyHash)
        //The gson builder
        gson =  GsonBuilder().setLenient().create()

        retrofit = Retrofit.Builder().baseUrl("http://192.168.35.193:8080/").addConverterFactory(GsonConverterFactory.create(gson)).build();
        service = retrofit.create(RetrofitService::class.java);


        button0 = findViewById(R.id.button)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        restText = findViewById(R.id.text_rest_api)
        imageView = findViewById(R.id.imageTest)
        imageView2 = findViewById(R.id.imageTest2)

//        var data = HashMap<String, String>()
//        data["abc"] = "TEST"
//
//        service.sendTest(data).enqueue(object: Callback<Test> {
//            override fun onResponse(call: Call<Test>, response: Response<Test>) {
//                if(response.isSuccessful){
//                    var result: Test? = response.body();
//                    println("success" + result.toString() + data);
//                    restText.setText(result.toString());
//                }else{
//                    println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패");
//                }
//            }
//
//            override fun onFailure(call: Call<Test>, t: Throwable) {
//                println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패222");
//                Log.d("YMC", "onFailure 에러: " + t.message.toString());
//            }
//        })

//        service.getTest().enqueue(object: Callback<Test> {
//            override fun onResponse(call: Call<Test>, response: Response<Test>) {
//                if(response.isSuccessful){
//                    var result: Test? = response.body();
//                    println("success" + result.toString());
//                    restText.setText(result.toString());
//                }else{
//                    println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패");
//                }
//            }
//
//            override fun onFailure(call: Call<Test>, t: Throwable) {
//                println("여기ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ 실패222");
//                Log.d("YMC", "onFailure 에러: " + t.message.toString());
//            }
//        })

        button3.setOnClickListener(View.OnClickListener {
            uploadPic(uriTest)
        })


        button2.setOnClickListener(View.OnClickListener {
            requestPermissions(arrayOf(WRITE_EXTERNAL_STORAGE), PERM_Album)

        })
        button0.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, HomeActivity::class.java) //ACTIVITY_NUM = 0

            startActivity(intent1)
        })

        button1.setOnClickListener(View.OnClickListener {
            val intent1 = Intent(this, ClubMyPageActivity::class.java) //ACTIVITY_NUM = 0

            startActivity(intent1)
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


    fun uploadPic(path: Uri){
        println("Fffff" + getRealPathFromURI(this.applicationContext, path))
        val file = File(getRealPathFromURI(this.applicationContext, path))


        var fileName = "test.png"
        var requestBody : RequestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(path)), file)
        var body : MultipartBody.Part = MultipartBody.Part.createFormData("pics",fileName,requestBody)

        var bodylist = listOf(body)
        //var test2 = Test2(bodylist, "mini")
        service.picTest(body, "mini", "fff").enqueue(object: Callback<Test2>{
            override fun onFailure(call: Call<Test2>, t: Throwable) {
                Log.d("레트로핏 결과1", t.message.toString())
            }

            override fun onResponse(call: Call<Test2>, response: Response<Test2>) {
                if (response?.isSuccessful) {
                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully...", Toast.LENGTH_LONG).show();
                    Log.d("레트로핏 결과2",""+response?.body()?.content)
                    Glide.with(this@MainActivity).load("http://192.168.35.193:8080/"+response?.body()?.content).into(imageView2)

                } else {
                    Toast.makeText(getApplicationContext(), "Some error occurred...", Toast.LENGTH_LONG).show();
                }
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_STORAGE -> {
                    data?.data?.let { uri ->
                        imageView.setImageURI(uri)
                        println("갤러리 이미지 uri : " + uri)
                        uriTest = uri
                    }
                }
            }
        }
    }

}