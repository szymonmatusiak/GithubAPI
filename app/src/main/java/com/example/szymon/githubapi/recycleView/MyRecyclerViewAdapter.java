package com.example.szymon.githubapi.recycleView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.szymon.githubapi.R;
import com.example.szymon.githubapi.githubAPI.Repo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Szymon on 10.08.2017.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
    List<Repo> repositories;

    public MyRecyclerViewAdapter(List<Repo> repos) {
        repositories = repos;
    }

    public void populateRepositories(List<Repo> repos) {
        repositories.addAll(repos);
    }

    public void removeRepositories() {
        repositories.clear();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repo_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Repo repository = repositories.get(position);
        if (holder != null) {
            holder.name.setText(repository.getName());
            holder.fullName.setText(repository.getFullName());
            holder.defaultBranch.setText(repository.getDefaultBranch());
        }
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.full_name)
        TextView fullName;
        @BindView(R.id.default_branch)
        TextView defaultBranch;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
