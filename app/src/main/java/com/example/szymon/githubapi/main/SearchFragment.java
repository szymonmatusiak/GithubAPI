package com.example.szymon.githubapi.main;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.szymon.githubapi.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {
    @BindView(R.id.username_input)
    EditText username;
    @BindView(R.id.button)
    Button button;
    String clientID = "aae5a6cfcddfa592556b";
    String clientSecret = "9dc99a3b71c42e607852f6557ec48f5c8820f910";
    String redirectUri = "githubapi://callback";
    MainPresenterForActivity mainPresenter = null;
    static int test = 0;

    public SearchFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (test == 0) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login/oauth/authorize" + "?client_id=" + clientID + "&redirect_uri=" + redirectUri));
            startActivity(intent);
            test++;
        }
    }

    public static SearchFragment newInstantiate() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setRetainInstance(true);
        return searchFragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        Uri uri = getActivity().getIntent().getData();
        if (uri != null && uri.toString().startsWith(redirectUri)) {
            String code = uri.getQueryParameter("code");
            Toast.makeText(getActivity(), uri.toString(), Toast.LENGTH_LONG).show();
            mainPresenter.setCode(code);
        } else {
            Toast.makeText(getActivity(), "onResumeFAIL", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter = new MainPresenterImpl();

    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @OnClick(R.id.button)
    void onButtonClicked() {
        EventBus.getDefault().post(new MessageEvent(username.getText().toString()));
    }
}
