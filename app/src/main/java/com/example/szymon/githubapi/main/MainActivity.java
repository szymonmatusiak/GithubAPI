package com.example.szymon.githubapi.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.szymon.githubapi.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {
    @BindView(R.id.text)
    TextView textView;

    @BindView(R.id.button)
    Button button;

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainPresenter = new MainPresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainPresenter.onStart(this);
    }

    @OnClick(R.id.button)
    void onButtonClicked() {
        mainPresenter.getReposOfUser("szymonmatusiak");
    }

    @Override
    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTextView(String text) {
        textView.setText(text);
    }
}
