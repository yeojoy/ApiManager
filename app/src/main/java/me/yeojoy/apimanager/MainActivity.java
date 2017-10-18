package me.yeojoy.apimanager;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.ConnectException;

import io.reactivex.disposables.CompositeDisposable;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.LocalApi;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.ListsResponse;

public class MainActivity extends AppCompatActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCompositeDisposable = new CompositeDisposable();


        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(LocalApi.class).lists())
                .setOnResponse(this::onGetResponse)
                .setOnError(this::onError)
                .execute();
    }

    private void onError(Throwable throwable) {
        if (throwable != null) {
            TextView textView = (TextView) findViewById(R.id.text_view);
            if (throwable instanceof ConnectException) {
                textView.setText("서버에 연결할 수가 없습니다.\n\n");
                textView.append(throwable.getMessage());
            }
        }
        Log.e(TAG, "error message : " + throwable.getMessage());
    }

    private void onGetResponse(BaseResponse response) {
        Log.d(TAG, "response : " + response);
        TextView textView = (TextView) findViewById(R.id.text_view);
        if (response != null) {
            ListsResponse listsResponse = ListsResponse.class.cast(response);

            textView.setText("message : " + listsResponse.message);
            textView.append("\n-----------------------------------------------------------\n");

            for (String s : listsResponse.lists) {
                textView.append("\n");
                textView.append(s);
                textView.append("\n");
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    public CompositeDisposable getCompositeDisposible() {
        return mCompositeDisposable;
    }

    @Override
    public Context getContext() {
        return this;
    }
}
