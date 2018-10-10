package blue.cn.com.mvp.mvp.contract

import com.trello.rxlifecycle2.LifecycleTransformer


/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午2:50
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IBaseContract {
    fun <T> bindToLife(): LifecycleTransformer<T>
    interface IView : IBaseContract

}
