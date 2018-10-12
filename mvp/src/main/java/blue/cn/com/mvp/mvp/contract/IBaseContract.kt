package blue.cn.com.mvp.mvp.contract

import android.view.View
import com.trello.rxlifecycle2.LifecycleTransformer


/**
 * Description :
 * @author  heping
 * @date 2018/10/9  下午2:50
 * 								 - generate by MvpAutoCodePlus plugin.
 */

interface IBaseContract {
    fun <T> bindToLife(): LifecycleTransformer<T>
    /**
     * 显示正在加载
     */
    fun showLoading()

    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示网络异常
     */
    fun showNetError(onClickListener: View.OnClickListener)

    /**
     * 显示空界面
     */
    fun showEmpty(onClickListener: View.OnClickListener)
    interface IView : IBaseContract

}
