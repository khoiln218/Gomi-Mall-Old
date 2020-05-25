package vn.gomimall.apps.ui.main.live;

import androidx.lifecycle.MutableLiveData;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmClient;
import vn.gomimall.apps.Application;
import vn.gomimall.apps.BaseViewModel;
import vn.gomimall.apps.R;
import vn.gomimall.apps.data.AgoraRepository;
import vn.gomimall.apps.data.ResultListener;
import vn.gomimall.apps.data.model.api.AgoraResponseData;
import vn.gomimall.apps.data.model.pojo.AgoraChannels;
import vn.gomimall.apps.data.model.pojo.Channel;
import vn.gomimall.apps.event.LiveHandler;
import vn.gomimall.apps.ui.main.MainEvent;

public class LiveViewModel extends BaseViewModel<LiveEvent> implements LiveHandler, SwipeRefreshLayout.OnRefreshListener {

    private AgoraRepository mAgoraRepository;

    public MutableLiveData<ChannelAdapter> channelAdapterMutableLiveData;

    private List<Channel> channels;
    private ChannelAdapter adapter;

    private RtmClient mRtmClient;

    public LiveViewModel() {
        mRtmClient = Application.getInstance().getChatManager().getRtmClient();
        mAgoraRepository = AgoraRepository.getInstance();
        channelAdapterMutableLiveData = new MutableLiveData<>();

        channels = new ArrayList<>();
        adapter = new ChannelAdapter(channels, this);
        channelAdapterMutableLiveData.setValue(adapter);
    }

    public void viewLive() {
        mRtmClient.logout(null);
        mRtmClient.login(null, Application.getPreferences().getUserName(), new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                EventBus.getDefault().post(new MainEvent<>(MainEvent.REQUEST_PERMISSION_LIVE));
            }

            @Override
            public void onFailure(final ErrorInfo errorInfo) {
                EventBus.getDefault().post(new LiveEvent(LiveEvent.LOGIN_FAILS, errorInfo.toString()));
            }
        });
    }


    @Override
    public void onRefresh() {
        channels.clear();
        requestAgoraListChannel();
    }

    private void requestAgoraListChannel() {
        String appId = Application.getInstance().getString(R.string.private_app_id);
        mAgoraRepository.getListChannel(appId, new ResultListener<AgoraResponseData<AgoraChannels>>() {
            @Override
            public void onLoaded(AgoraResponseData<AgoraChannels> result) {
                loaded();
                channels.addAll(result.getData().getChannels());
                updateChannel();
                checkEmpty();
            }

            @Override
            public void onDataNotAvailable(String error) {
                loaded();
                checkConnection(error);
            }
        });
    }

    private void checkEmpty() {
        setErrorMessage(channels.size() > 0 ? null : "Chưa có kênh LIVE");
    }

    void showLoading() {
        showProgressing();
    }

    private void updateChannel() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onView(Channel channel) {
        Application.getInstance().engineConfig().setChannelName(channel.getChannel_name());
        viewLive();
    }
}
