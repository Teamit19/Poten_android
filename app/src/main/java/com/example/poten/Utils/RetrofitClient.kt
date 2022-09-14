package com.example.poten.Utils

import android.util.Log
import android.webkit.CookieManager
import com.example.poten.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        const val BASE_URL = BuildConfig.BASE_URL // 주소


        class LoginInterceptor constructor(private val tokenFromCookieManager: String) : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
                Log.d("interceptor Session", tokenFromCookieManager)
                val newRequest = request().newBuilder()
                    .addHeader("login", tokenFromCookieManager)
                    .build()
                proceed(newRequest)
            }
        }

        // 클라이언트에 인터셉터 탑재하기
        private fun provideOkHttpClient(interceptor: LoginInterceptor): OkHttpClient
                = OkHttpClient.Builder().run {
            addInterceptor(interceptor)
            build()
        }

        // 이건 그냥 디폴트 생성자
        fun <T> create(apiService : Class<T>) : T {
            val gson = GsonBuilder().setLenient().create()
            //timeout 세팅
            var okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(apiService)
        }

        // create 오버로딩 : 세션값을 넣으면 해당 세션값을 인터셉트(리퀘스트 헤더에 자동으로 넣어주는) 해주는 인터셉터를 달아줌
        fun <T> create(api : Class<T>, token : String) : T {
            val gson = GsonBuilder().setLenient().create()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(provideOkHttpClient(LoginInterceptor(token)))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(api)
        }

        // 쿠키매니저에서 쿠키정보 가져오는 편의 메소드
        fun getAuth() : String {
//            val cm: CookieManager = CookieManager.getInstance()
//            cm.removeAllCookies(null)
//            cm.setCookie(RetrofitClient.BASE_URL, "B04EF590F45AB9AE74BAB490BE76DCCA")
            return CookieManager.getInstance().getCookie(BASE_URL) ?: ""

        }

        fun clearCookieManager() {
            CookieManager.getInstance().removeAllCookies(null)
        }
    }


}