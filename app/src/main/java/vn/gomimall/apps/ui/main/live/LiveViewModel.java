package vn.gomimall.apps.ui.main.live;

import vn.gomimall.apps.BaseViewModel;

public class LiveViewModel extends BaseViewModel<LiveEvent> {

//    private RtmClient mRtmClient;
//
//    public LiveViewModel() {
//        mRtmClient = Application.getInstance().getChatManager().getRtmClient();
//    }
//
//    public void startBroadcast() {
//        mRtmClient.login(null, Application.getPreferences().getUserName(), new ResultCallback<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                EventBus.getDefault().post(new MainEvent<>(MainEvent.REQUEST_PERMISSION_LIVE));
//            }
//
//            @Override
//            public void onFailure(final ErrorInfo errorInfo) {
//                if (errorInfo.getErrorCode() == RtmStatusCode.LoginError.LOGIN_ERR_ALREADY_LOGIN) {
//                    EventBus.getDefault().post(new MainEvent<>(MainEvent.REQUEST_PERMISSION_LIVE));
//                } else {
//                    EventBus.getDefault().post(new LiveEvent(LiveEvent.LOGIN_FAILS, errorInfo.toString()));
//                }
//            }
//        });
//    }
}
