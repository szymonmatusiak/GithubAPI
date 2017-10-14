package com.example.szymon.githubapi.main;

import com.example.szymon.githubapi.base.IBaseView;
import com.example.szymon.githubapi.githubAPI.Repo;

import java.util.List;

/**
 * Created by szymon on 25.09.17.
 */

public interface MyRecyclerView extends IBaseView {
    void populateRecyclerView(MessageEvent messageEvent);

    void populateRecyclerView(List<Repo> repositories);
}
