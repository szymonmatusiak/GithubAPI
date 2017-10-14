package com.example.szymon.githubapi.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.szymon.githubapi.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (savedInstanceState != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, SearchFragment.newInstantiate()).commit();
            getSupportFragmentManager().beginTransaction().replace(R.id.recycler_fragment_container, RecyclerFragment.newInstantiate()).commit();
        }
        //Intent intent = new Intent(this, Main2Activity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void toast(final String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
