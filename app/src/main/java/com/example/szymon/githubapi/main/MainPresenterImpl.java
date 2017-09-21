package com.example.szymon.githubapi.main;

import com.example.szymon.githubapi.base.BasePresenter;
import com.example.szymon.githubapi.githubAPI.GitHubService;
import com.example.szymon.githubapi.githubAPI.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Szymon on 04.08.2017.
 */

public class MainPresenterImpl extends BasePresenter<MainView> implements MainPresenter {

    private static final String URL = "https://api.github.com/";
    private Retrofit retrofit;
    private GitHubService gitHubService;
    private List<Repo> repositories;

    @Override
    public void onStart(final MainView mainView) {
        attachView(mainView);
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    @Override
    public void onStop() {
        detachView(true);
    }

    @Override
    public void getReposOfUser(final String user) {
        Call<List<Repo>> repos = gitHubService.listRepos(user);
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    repositories = response.body();
                    getView().populateRecyclerView(repositories);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                getView().toast(t.toString());
            }
        });
    }
}
