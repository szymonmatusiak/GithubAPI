package com.example.szymon.githubapi.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.szymon.githubapi.R;
import com.example.szymon.githubapi.githubAPI.Repo;
import com.example.szymon.githubapi.recycleView.MyRecyclerViewAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment implements MyRecyclerView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;
    private MainPresenter mainPresenter;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    public static RecyclerFragment newInstantiate() {
        RecyclerFragment recyclerFragment = new RecyclerFragment();
        recyclerFragment.setRetainInstance(true);
        return recyclerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);
        ButterKnife.bind(this, view);
        mainPresenter = new MainPresenterImpl();
        setRecycler();
        return view;
    }

    private void setRecycler() {
        recyclerViewAdapter = new MyRecyclerViewAdapter(new ArrayList<Repo>());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.onStart(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    @Override
    public void populateRecyclerView(MessageEvent messageEvent) {
        mainPresenter.getReposOfUser(messageEvent.message);
        mainPresenter.getAccessToken();
    }

    @Override
    public void populateRecyclerView(final List<Repo> repositories) {
        setDataAndNotify(repositories);
    }

    private void setDataAndNotify(List<Repo> repositories) {
        recyclerViewAdapter.removeRepositories();
        recyclerViewAdapter.populateRepositories(repositories);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
