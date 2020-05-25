package vn.gomimall.apps.data.model.pojo;

import java.util.List;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class AgoraChannels {
    /**
     * channels : []
     * total_size : 1
     */

    private int total_size;
    private List<Channel> channels;

    public int getTotal_size() {
        return total_size;
    }

    public void setTotal_size(int total_size) {
        this.total_size = total_size;
    }

    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
