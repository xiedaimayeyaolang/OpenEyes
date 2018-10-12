package blue.cn.com.mvp.util;

import android.support.annotation.Nullable;

/**
 * com.blueooo.miao.base.util in blueooo
 * Created by zhangdonghai on 2017/7/12
 */

public class Util {

    public static <T> void checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

}
