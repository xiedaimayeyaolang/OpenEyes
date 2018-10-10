package blue.cn.com.mvp.mvp.model;

/**
 * com.blueooo.miao.network.data in blueooo
 * Created by zhangdonghai on 2017/7/14
 */

public class JsonResult<K> {


    /**
     * status : 40005
     * data : []
     * msg : token错误
     */

    private int status;
    private int count;
    private String msg;
    private K data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
