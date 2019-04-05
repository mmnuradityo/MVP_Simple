package com.nuradityo.crudmvp.presenter;

import com.nuradityo.crudmvp.model.user;
import com.nuradityo.crudmvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainPresenterImpl implements MainPrensenter {

    private MainView mainView;
    private List<user> users = new ArrayList<>();
    private int no = 4;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        init();
    }

    private void init() {
        user user = new user();
        user.setId(1);
        user.setNama("Kristiawa Adi");
        user.setNoHp("081234567");
        user.setPekerjaan("Java Programer");
        user.setStatus("Jomlo Ngenes");
        users.add(user);

        user user1 = new user();
        user1.setId(2);
        user1.setNama("Bimo Joko");
        user1.setNoHp("085474748888");
        user1.setPekerjaan("IT Scurity");
        user1.setStatus("Udah punya pacar tapi drama melulu");
        users.add(user1);

        user user2 = new user();
        user2.setId(3);
        user2.setNama("Dirga");
        user2.setNoHp("085332423043");
        user2.setPekerjaan("IT Security");
        user2.setStatus("Udah punya pacar dan bentar lagi nikah");
        users.add(user2);
    }

    @Override
    public void load() {
        mainView.onLoad(users);
    }

    @Override
    public void save(user user) {
        no++;
        user.setId(no);
        users.add(user);

        mainView.onSave();
    }

    @Override
    public void delete(user user) {
        users.remove(user);

        mainView.onDelete();
    }

    @Override
    public void update(user user) {

        for (user model : users) {
            if (model.getId() == user.getId()) {
                model.setNama(user.getNama());
                model.setStatus(user.getStatus());
                model.setPekerjaan(user.getPekerjaan());
                model.setNoHp(user.getNoHp());

                break;
            }
        }

        mainView.onUpdate();
    }

}
