package blue.cn.com.mvp.util

import android.app.Activity


/**
 */

class BaseAppManager private constructor() {

    private val sActivityList = ArrayList<Activity>()

    companion object {
        fun getInstance(): BaseAppManager {
            return BaseAppManagerHolder.instance
        }
    }

    private object BaseAppManagerHolder {
        val instance = BaseAppManager()
    }

    /**
     * 当前activity的个数
     */
    val mActivitySize: Int get() = sActivityList.size

    val mForwardActivity: Activity? @Synchronized get() = if (mActivitySize > 0) sActivityList[mActivitySize - 1] else null

    /**
     * 添加相应activity
     */
    @Synchronized fun removeActivity(activity: Activity) {
        if (activity in sActivityList) sActivityList.remove(activity)
    }

    /**
     * 添加相应的activity
     */
    @Synchronized fun addActivity(activity: Activity) {
        sActivityList.add(activity)
    }

    /**
     * 清除栈内activity
     */
    @Synchronized fun clear() {
        var i = sActivityList.size - 1
        while (i > -1) {
            val activity = sActivityList[i]
            removeActivity(activity)
            activity.finish()
            i = sActivityList.size
            i--
        }
    }

    /**
     * 清除栈顶activity
     */
    @Synchronized fun clearToTop() {
        var i = sActivityList.size - 2
        while (i > -1) {
            val activity = sActivityList[i]
            removeActivity(activity)
            activity.finish()
            i = sActivityList.size - 1
            i--
        }
    }
}