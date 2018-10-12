package blue.cn.com.mvp.util;


import android.text.TextUtils;
import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * com.neusoft.si.lib.lvrip.base.util in mobile-android-module-base
 * Created by zhangdonghai on 2017/4/21
 */

public final class AesUtil {

    private static final String TAG = "AesUtil.class";

    private volatile static AesUtil aesUtil;
    private byte[] raw;
    private String ivStr;

    private AesUtil() {
        raw = getRawKey();
    }

    public static AesUtil getInstance() {
        if (aesUtil == null) {
            synchronized (AesUtil.class) {
                if (aesUtil == null) {
                    aesUtil = new AesUtil();
                }
            }
        }
        return aesUtil;
    }

    //get Key
    private byte[] getRawKey() {
//        File tempFile = null;
        try {
//            tempFile = File.createTempFile("aes-", null);
//            String md5 = HashUtil.md5(tempFile, false);
            String md5 = "d41d8cd98f00b204e9800998ecf8427e";
            ivStr = HashUtil.md5(Base64.encodeToString(md5.getBytes("UTF-8"), Base64.DEFAULT).substring(0, 16),
                    HashUtil.UTF_8, false).substring(0, 16);
            return md5.getBytes("ASCII");
        } catch (Exception e) {
            return null;
        } finally {
//            if (tempFile != null) {
//                tempFile.delete();
//            }
        }
    }

    // 加密
    public byte[] enCrypt(byte[] bytes) {
        try {

            if (bytes == null || bytes.length == 0) {
                return bytes;
            }

            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(bytes);
            return Base64.encode(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            return null;
        }
    }

    // 加密
    public String enCrypt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception e) {
            return str;
        }
    }

    // 解密
    public byte[] deCrypt(byte[] bytes) {
        try {

            if (bytes == null || bytes.length == 0) {
                return bytes;
            }

            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] decrypted = Base64.decode(bytes, Base64.DEFAULT);
            return cipher.doFinal(decrypted);
        } catch (Exception e) {
            return bytes;
        }
    }

    // 解密
    public String deCrypt(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            SecretKeySpec sKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivStr.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            byte[] decrypted = Base64.decode(str, Base64.DEFAULT);
            byte[] original = cipher.doFinal(decrypted);
            return new String(original, "UTF-8");
        } catch (Exception e) {
            return str;
        }
    }
}
