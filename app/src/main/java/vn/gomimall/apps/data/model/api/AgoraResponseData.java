package vn.gomimall.apps.data.model.api;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class AgoraResponseData<T> {
    /**
     * success : true
     * data : {}
     */

    private boolean success;
    private T data;
    /**
     * message : HMAC signature cannot be verified, a valid date or x-date header is required for HMAC Authentication
     */

    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
