package com.example.poten.Login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import androidx.appcompat.app.AppCompatActivity
import com.example.poten.MainActivity
import com.example.poten.MyPage.ClubMyPageActivity
import com.example.poten.Board.HomeActivity
import com.example.poten.Board.model.UserResponse
import com.example.poten.Utils.RetrofitClient
import com.example.poten.databinding.ActivityLoginBinding
import com.example.poten.dto.SessionResponse
import com.example.poten.interfaces.UserApi
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    var retrofit = RetrofitClient.create(UserApi::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val keyHash = Utility.getKeyHash(this)
//        Log.d("Hash", keyHash)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    Log.e("LOGIN", "카카오계정으로 로그인 실패", error)
                } else if (token != null) {
                    Log.i("LOGIN", "카카오계정으로 로그인 성공 callback ${token.accessToken}")
                    retrofit.getKakao(token.accessToken).enqueue(object: Callback<SessionResponse> {
                        override fun onResponse(call: Call<SessionResponse>, response: Response<SessionResponse>) {
                            val sessionId = response.body()?.sessionId.toString()
                            setSession(sessionId)
                            Log.i("LOGIN", "api 성공}"+response.body())
                        }

                        override fun onFailure(call: Call<SessionResponse>, t: Throwable) {
                            Log.i("LOGIN", "api 실패"+t.message.toString())
                        }

                    })
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
                UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                    if (error != null) {
                        Log.e("LOGIN", "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                    } else if (token != null) {
                        Log.i("LOGIN", "카카오톡으로 로그인 성공 ${token}")

                        retrofit.getKakao(token.accessToken).enqueue(object: Callback<SessionResponse> {
                            override fun onResponse(call: Call<SessionResponse>, response: Response<SessionResponse>) {
                                Log.i("LOGIN", "api 성공}"+response.body())
                                val sessionId = response.body()?.sessionId.toString()
                                setSession(sessionId)
                                checkSignup()
                            }

                            override fun onFailure(call: Call<SessionResponse>, t: Throwable) {
                                Log.i("LOGIN", "api 실패"+t.message.toString())
                            }

                        })                        
//                        val intent = Intent(this, SelectAreaActivity::class.java)
//                        //val intent = Intent(this, ClubMyPageActivity::class.java)
//                        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                        finish()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
            }
        }
    }


    private fun setSession(sessionId: String) {
        val cm: CookieManager = CookieManager.getInstance()
        cm.removeAllCookies(null)
        cm.setCookie(RetrofitClient.BASE_URL, sessionId)
        Log.d("sessionId From Cm", cm.getCookie(RetrofitClient.BASE_URL))
        Log.d("sessionId From Server", sessionId)
    }


}