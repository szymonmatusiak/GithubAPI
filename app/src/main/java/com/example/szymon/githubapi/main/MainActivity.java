package com.example.szymon.githubapi.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.szymon.githubapi.R;
import com.example.szymon.githubapi.githubAPI.Repo;
import com.example.szymon.githubapi.recycleView.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.username_input)
    EditText username;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private MyRecyclerViewAdapter recyclerViewAdapter;

    private MainPresenter mainPresenter;
    private List<Repo> repos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

    @OnClick(R.id.button)
    void onButtonClicked() {
        // toast("button");
        //recyclerViewAdapter = new MyRecyclerViewAdapter();
        mainPresenter.getReposOfUser(String.valueOf(username.getText()));
    }

    @Override
    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void populateRecyclerView(List<Repo> repos) {

            this.repos.addAll(repos);
            recyclerViewAdapter.notifyDataSetChanged();
            toast(this.repos.get(0).getFullName());

    }


}
