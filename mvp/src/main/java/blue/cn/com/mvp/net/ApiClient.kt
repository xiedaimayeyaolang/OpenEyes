package blue.cn.com.mvp.net

import android.util.Log
import blue.cn.com.mvp.BuildConfig
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ApiClient {
    //默认URL
    val BASE_URL =  "http://app.meeoh.cn/"
    //支持拦截器拓展接口 默认的两个拦截器，如果需要自定义拦截器，重写b
    var b : Interceptors = object : Interceptors {
        override fun add(): List<Interceptor> {
            //返回空集合，如果返回值定义成可选类型，可返回null,否则会空指针
            return arrayListOf()
        }

    }
    //自定义baseURL,如有修改，重写getBaseUrl方法
    var u : Url = object : Url{
        override fun getBaseUrl(): String {
            return BASE_URL
        }

    }
    var converFactory : ConverFactory? = null
    val TAG = "HttpClient.class"
    @Inject
    lateinit var mRetrofit: Retrofit

    fun retrofit(): Retrofit {
        val builder = builder()
        val okHttpClient = builder.build()
        mRetrofit = Retrofit.Builder()
                .baseUrl(u.getBaseUrl())
                .addConverterFactory(converFactory?.getConverFactory() ?: GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        return mRetrofit
    }

    private fun builder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            val loggingInterceptor = HttpLoggingInterceptor {
                Log.e(TAG, "HttpClientBack = $it")
            }
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor)
        }
        b.add().forEach { builder.addInterceptor(it) }
//        builder.sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//配置
//        builder.hostnameVerifier(SSLSocketClient.getHostnameVerifier())
//        for (i in b.add()){
//
//        }
        return builder
    }
}
interface Interceptors{

    fun add() : List<Interceptor>
}
interface Url{
    fun getBaseUrl() : String
}
interface ConverFactory{
    fun getConverFactory(): Converter.Factory
}