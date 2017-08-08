package com.example.szymon.githubapi.main;

import com.example.szymon.githubapi.base.IBaseView;

/**
 * Created by Szymon on 04.08.2017.
 */

public interface MainView extends IBaseView {
    void toast(final String text);

    void setTextView(String text);
}
