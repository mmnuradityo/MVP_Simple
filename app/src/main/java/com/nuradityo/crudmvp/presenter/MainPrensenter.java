package com.nuradityo.crudmvp.presenter;

import com.nuradityo.crudmvp.model.user;

import java.util.List;

public interface MainPrensenter {

    void load();

    void save(user user);

    void delete(user user);

    void update(user user);
}
