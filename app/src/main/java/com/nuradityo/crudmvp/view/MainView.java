package com.nuradityo.crudmvp.view;

import com.nuradityo.crudmvp.model.user;

import java.util.List;

public interface MainView {

    void onLoad(List<user> users);

    void onSave();

    void onDelete();

    void onUpdate();

}
