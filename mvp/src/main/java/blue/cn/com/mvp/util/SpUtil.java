package blue.cn.com.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * com.blueooo.miao.base.util in blueooo
 * Created by zhangdonghai on 2017/7/12
 */

public class SpUtil {

    private static SharedPreferences prefs;

    public static void init(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setString(String key, String value) {
        Util.checkNotNull(prefs, "prefs == null");
        prefs.edit().putString(HashUtil.crc(key), AesUtil.getInstance().enCrypt(value)).apply();
    }

    public static String getString(String key, String defValue) {
        Util.checkNotNull(prefs, "prefs == null");
        return AesUtil.getInstance().deCrypt(prefs.getString(HashUtil.crc(key), defValue));
    }

    public static String getString(String key) {
        Util.checkNotNull(prefs, "prefs == null");
        return AesUtil.getInstance().deCrypt(prefs.getString(HashUtil.crc(key), ""));
    }

    public static void setBoolean(String key, boolean value) {
        Util.checkNotNull(prefs, "prefs == null");
        prefs.edit().putBoolean(HashUtil.crc(key), value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        Util.checkNotNull(prefs, "prefs == null");
        return prefs.getBoolean(HashUtil.crc(key), defValue);
    }

    public static boolean getBoolean(String key) {
        Util.checkNotNull(prefs, "prefs == null");
        return prefs.getBoolean(HashUtil.crc(key), false);
    }

    public static void setInt(String key, int value) {
        Util.checkNotNull(prefs, "prefs == null");
        prefs.edit().putInt(HashUtil.crc(key), value).apply();
    }

    public static int getInt(String key, int defValue) {
        Util.checkNotNull(prefs, "prefs == null");
        return prefs.getInt(HashUtil.crc(key), defValue);
    }

    public static int getInt(String key) {
        Util.checkNotNull(prefs, "prefs == null");
        return prefs.getInt(HashUtil.crc(key), -1);
    }


}
