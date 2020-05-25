package vn.gomimall.apps.binding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.gomimall.apps.ui.main.live.ChannelAdapter;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class LiveBinding {
    @BindingAdapter("setChannelAdapter")
    public static void setOrderAdapter(RecyclerView recyclerView, ChannelAdapter adapter) {
        if (recyclerView.getAdapter() == null && adapter != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(adapter);
        }
    }
}
