package com.example.szymon.githubapi.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.szymon.githubapi.R;
import com.example.szymon.githubapi.githubAPI.Repo;
import com.example.szymon.githubapi.recycleView.MyRecyclerViewAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            getFragmentManager().beginTransaction().replace(R.id.container, SearchFragment.newInstantiate()).commit();
        }
        mainPresenter = new MainPresenterImpl();
        recyclerViewAdapter = new MyRecyclerViewAdapter(repos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart(this);
    }


    @Override
    public void toast(final String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateRecyclerView(final List<Repo> repos) {
        this.repos.addAll(repos);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
