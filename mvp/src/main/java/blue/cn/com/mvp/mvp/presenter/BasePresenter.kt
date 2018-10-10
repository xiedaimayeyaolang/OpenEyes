package blue.cn.com.mvp.mvp.presenter

import blue.cn.com.mvp.net.ApiStores
import javax.inject.Inject

/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午2:50
 * 								 - generate by MvpAutoCodePlus plugin.
 */

open class BasePresenter{
    @Inject
    lateinit var apiStores: ApiStores
}

