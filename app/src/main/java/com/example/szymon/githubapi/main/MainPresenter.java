package com.example.szymon.githubapi.main;

/**
 * Created by Szymon on 04.08.2017.
 */

public interface MainPresenter {
    void onStart(final MyRecyclerView myRecyclerView);

    void onStop();

    void getReposOfUser(String user);
}
