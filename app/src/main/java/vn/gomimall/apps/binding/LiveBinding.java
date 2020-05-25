package vn.gomimall.apps.binding;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import vn.gomimall.apps.R;
import vn.gomimall.apps.data.model.pojo.stats.StatsManager;
import vn.gomimall.apps.ui.main.live.ChannelAdapter;
import vn.gomimall.apps.ui.main.live.message.MessageAdapter;
import vn.gomimall.apps.ui.main.live.message.MessageBean;
import vn.gomimall.apps.ui.main.live.video.VideoGridContainer;
import vn.gomimall.apps.utils.Utils;

/**
 * Created by KHOI LE on 5/25/2020.
 */
public class LiveBinding {

    @BindingAdapter("scrollToPosition")
    public static void scrollToPosition(final RecyclerView recyclerView, final int msgCount) {
        ViewTreeObserver observer = recyclerView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                recyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams paramsMsg = recyclerView.getLayoutParams();
                if (paramsMsg.height == ViewGroup.LayoutParams.WRAP_CONTENT && recyclerView.getMeasuredHeight() > Utils.getScreenWidth() * 5 / 12) {
                    paramsMsg.height = Utils.getScreenWidth() * 5 / 12;
                    recyclerView.setLayoutParams(paramsMsg);
                }
                recyclerView.scrollToPosition(msgCount - 1);
            }
        });
    }


    @BindingAdapter("setMsgAdapter")
    public static void setMsgAdapter(RecyclerView recyclerView, MessageAdapter adapter) {
        if (recyclerView.getAdapter() == null && adapter != null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            layoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("setMsg")
    public static void setMsg(TextView textView, MessageBean msgBean) {
        SpannableString spannableString = new SpannableString(msgBean.getAccount() + ": " + msgBean.getMessage());
        spannableString.setSpan(new ForegroundColorSpan(msgBean.isBeSelf() ? Color.DKGRAY : Color.GRAY), 0, msgBean.getAccount().length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), 0, msgBean.getAccount().length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(.9f), 0, msgBean.getAccount().length() + 1, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new StyleSpan(Typeface.BOLD), msgBean.getAccount().length() + 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(.95f), msgBean.getAccount().length() + 2, spannableString.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(spannableString);
    }

    @BindingAdapter("initUserIcon")
    public static void initUserIcon(final ImageView imageView, String url) {
        Glide.with(imageView)
                .load(url)
                .placeholder(R.drawable.fake_user_icon)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(100)))
                .into(imageView);
    }

    @BindingAdapter("setStatsManager")
    public static void setStatsManager(VideoGridContainer videoGridContainer, StatsManager statsManager) {
        videoGridContainer.setStatsManager(statsManager);
    }

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
