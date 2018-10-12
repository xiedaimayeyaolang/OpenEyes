package blue.cn.com.mvp.net

import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val urlBuilder = chain.request().url()
                .newBuilder()
                .setEncodedQueryParameter("udid", "d0f6190461864a3a978bdbcb3fe9b48709f1f390")
                .setEncodedQueryParameter("vc", "225")
                .setEncodedQueryParameter("vn", "3.12.0")
                .setEncodedQueryParameter("deviceModel", "Redmi%204")
                .setEncodedQueryParameter("first_channel", "eyepetizer_xiaomi_market")
                .setEncodedQueryParameter("last_channel", "eyepetizer_xiaomi_market")
                .setEncodedQueryParameter("system_version_code", "23")

        val request = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Cookie", "ky_auth=;sdk=23")
                .addHeader("model", "Android")
                .url(urlBuilder.build())
                .build()

        return chain.proceed(request)
    }

}