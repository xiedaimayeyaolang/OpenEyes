package blue.cn.com.mvp.net

import io.reactivex.Observable
import retrofit2.http.GET

interface ApiStores {



//    @POST(Urls.sendSMS)
//    fun sendSMS(@Body chatToSendSMSRequest: ChatToSendSMSRequest): Observable<ChatToSendSMSResponse>
    /**
     * 首页
     */
    @GET("api/v4/tabs/selected")
    fun getHomeInfo(): Observable<AndyInfo>
}
