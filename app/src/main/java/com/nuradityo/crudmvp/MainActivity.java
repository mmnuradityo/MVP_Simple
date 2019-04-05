package com.nuradityo.crudmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nuradityo.crudmvp.model.user;
import com.nuradityo.crudmvp.presenter.MainPrensenter;
import com.nuradityo.crudmvp.presenter.MainPresenterImpl;
import com.nuradityo.crudmvp.util.RV_Adapter;
import com.nuradityo.crudmvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    RecyclerView recyclerView;
    Button buttonAdd, buttonSave, buttonCancle;
    RV_Adapter rv_adapter;
    List<user> users = new ArrayList<>();
    AppCompatDialog dialog;
    MainPrensenter prensenter;
    EditText nama, noHp, pekerjaan, status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prensenter = new MainPresenterImpl(this);

        recyclerView = findViewById(R.id.RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rv_adapter = new RV_Adapter(users);
        rv_adapter.setOnCallbackListener(new RV_Adapter.OnCallbackListener() {
            @Override
            public void onClick(user user) {
                showDialogDetail(user);
            }
        });

        recyclerView.setAdapter(rv_adapter);

        buttonAdd = findViewById(R.id.BTN_Crud);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogInput();
            }
        });

        prensenter.load();

    }

    @Override
    public void onLoad(List<user> users_) {
        users.clear();
        users.addAll(users_);
        rv_adapter.notifyDataSetChanged();
    }

    @Override
    public void onSave() {
        Toast.makeText(this, "Berhasil disimpan!", Toast.LENGTH_SHORT).show();
        prensenter.load();
    }

    @Override
    public void onDelete() {
        Toast.makeText(this, "Berhasil dihapus!", Toast.LENGTH_SHORT).show();
        prensenter.load();
    }

    @Override
    public void onUpdate() {
        Toast.makeText(this, "Berhasil diperbarui!", Toast.LENGTH_SHORT).show();
        prensenter.load();
    }

    private void showDialogInput() {
        dialog = new AppCompatDialog(this);
        dialog.setContentView(R.layout.dialog_input);
        dialog.setTitle("");

        nama = dialog.findViewById(R.id.ET_nama);
        noHp = dialog.findViewById(R.id.ET_nohp);
        pekerjaan = dialog.findViewById(R.id.ET_pekerjaan);
        status = dialog.findViewById(R.id.ET_status);

        buttonSave = dialog.findViewById(R.id.BTN_save);
        buttonSave.setText("Update");
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user user = new user();
                user.setNama(nama.getText().toString());
                user.setNoHp(noHp.getText().toString());
                user.setPekerjaan(pekerjaan.getText().toString());
                user.setStatus(status.getText().toString());

                prensenter.save(user);
                dialog.dismiss();
            }
        });

        buttonCancle = dialog.findViewById(R.id.BTN_cancel);
        buttonCancle.setText("Delete");
        buttonCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public void showDialogDetail(final user user) {
        dialog = new AppCompatDialog(this);
        dialog.setContentView(R.layout.dialog_input);
        dialog.setTitle("");

        final EditText editTextNama = dialog.findViewById(R.id.ET_nama);
        final EditText editTextNoHp = dialog.findViewById(R.id.ET_nohp);
        final EditText editTextPekerjaan = dialog.findViewById(R.id.ET_pekerjaan);
        final EditText editTextStatus = dialog.findViewById(R.id.ET_status);

        editTextNama.setText(user.getNama());
        editTextNoHp.setText(user.getNoHp());
        editTextPekerjaan.setText(user.getPekerjaan());
        editTextStatus.setText(user.getStatus());

        Button buttonUpdate = dialog.findViewById(R.id.BTN_save);
        buttonUpdate.setText("Update");
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setNama(editTextNama.getText().toString());
                user.setNoHp(editTextNoHp.getText().toString());
                user.setPekerjaan(editTextPekerjaan.getText().toString());
                user.setStatus(editTextStatus.getText().toString());

                prensenter.update(user);
                dialog.dismiss();
            }
        });

        Button buttonDelete = dialog.findViewById(R.id.BTN_cancel);
        buttonDelete.setText("Delet");
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prensenter.delete(user);
                dialog.dismiss();
            }
        });

        if (!dialog.isShowing()) {
            dialog.show();
        }
    }
}
