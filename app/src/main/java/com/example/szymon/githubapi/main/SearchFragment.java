package com.example.szymon.githubapi.main;


import android.os.Bundle;
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

    public SearchFragment() {
    }

    public static SearchFragment newInstantiate() {
        SearchFragment searchFragment = new SearchFragment();
        searchFragment.setRetainInstance(true);
        return searchFragment;
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
