package vn.gomimall.apps.ui.main.live;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.gomimall.apps.data.model.pojo.Channel;
import vn.gomimall.apps.databinding.LiveItemBinding;
import vn.gomimall.apps.event.LiveHandler;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class ChannelAdapter extends RecyclerView.Adapter {
    private List<Channel> channels;
    private LiveHandler liveHandler;

    public ChannelAdapter(List<Channel> channels, LiveHandler liveHandler) {
        this.channels = channels;
        this.liveHandler = liveHandler;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LiveHolder.getInstance(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((LiveHolder) holder).bind(channels.get(position), liveHandler);
    }

    @Override
    public int getItemCount() {
        return channels == null ? 0 : channels.size();
    }

    static class LiveHolder extends RecyclerView.ViewHolder {
        private LiveItemBinding binding;

        public static LiveHolder getInstance(ViewGroup viewGroup) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            LiveItemBinding binding = LiveItemBinding.inflate(inflater, viewGroup, false);
            return new LiveHolder(binding);
        }

        private LiveHolder(LiveItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Channel channel, LiveHandler liveHandler) {
            binding.setChannel(channel);
            binding.setLiveHandler(liveHandler);
            binding.executePendingBindings();
        }
    }
}
