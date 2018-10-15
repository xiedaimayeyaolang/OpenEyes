package blue.cn.com.mvp.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.zip.CRC32;

/**
 * com.blueooo.miao.base.util in blueooo
 * Created by zhangdonghai on 2017/7/11
 */

public class HashUtil {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    /**
     * 16进制的字符数组
     */
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String md5(String source, Charset encoding, boolean uppercase) {
        String result = "";
        try {
            result = source;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes(encoding));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return uppercase ? result.toUpperCase() : result;
    }

    public static String md5(File file, boolean uppercase) {
        InputStream inputStream = null;
        byte[] buffer = new byte[4 * 1024];
        int len;
        String result = "";
        try {
            inputStream = new FileInputStream(file);
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            while ((len = inputStream.read(buffer)) != -1) {
                messageDigest.update(buffer, 0, len);
            }
            result = byteArrayToHexString(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uppercase ? result.toUpperCase() : result;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexString(byte b) {
        return hexDigits[b >>> 4 & 0xf] + hexDigits[b & 0xf];
    }

    public static String crc(String str) {
        CRC32 crc32 = new CRC32();
        crc32.update(str.getBytes(UTF_8));
        return Long.toHexString(crc32.getValue());
    }

    public static String crc(byte[] source) {
        CRC32 crc32 = new CRC32();
        crc32.update(source);
        return Long.toHexString(crc32.getValue());
    }


}
