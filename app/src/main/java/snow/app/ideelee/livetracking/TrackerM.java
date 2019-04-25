package snow.app.ideelee.livetracking;

public class TrackerM {
    String orderStatus;
    String time;
    String msg;

    public TrackerM(String orderStatus, String time, String msg) {
        this.orderStatus = orderStatus;
        this.time = time;
        this.msg = msg;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
