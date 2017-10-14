package com.example.szymon.githubapi.main;

import com.example.szymon.githubapi.base.BasePresenter;
import com.example.szymon.githubapi.githubAPI.AccessToken;
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

public class MainPresenterImpl extends BasePresenter<MyRecyclerView> implements MainPresenter, MainPresenterForActivity {//MainView

    private static final String URL = "https://github.com/";
    private static final String clientID = "aae5a6cfcddfa592556b";
    private static final String clientSecret = "9dc99a3b71c42e607852f6557ec48f5c8820f910";
    private static final String redirectUri = "githubapi://callback";
    private static String code;

    private Retrofit retrofit;
    private GitHubService gitHubService;
    private List<Repo> repositories;

    @Override
    public void onStart(final MyRecyclerView MyRecyclerView) {
        attachView(MyRecyclerView);
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
            }
        });
    }

    @Override
    public void getAccessToken() {
        if (code != null) {
            code.toString();
        }
        Call<AccessToken> accessTokenCall = gitHubService.getAccessToken(
                clientID,
                clientSecret,
                code);
        accessTokenCall.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                int statusCode = response.code();
                if (statusCode == 200) {
                    if (code != null) {
                        code.toString();
                    }
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

            }
        });
    }

    @Override
    public void setCode(String code) {
        this.code = code;
        if (this.code != null) {
            code.toString();
        }
    }
}
