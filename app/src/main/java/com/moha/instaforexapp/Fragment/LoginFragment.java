package com.moha.instaforexapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moha.instaforexapp.Modal.ApiAccount;
import com.moha.instaforexapp.R;
import com.moha.instaforexapp.Rest.ApiClient;
import com.moha.instaforexapp.Rest.ApiInterface;
import com.moha.instaforexapp.Utility.NetworkUtils;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginFragment extends Fragment {

    @SuppressLint("StaticFieldLeak")
    public static LoginFragment instance;
    protected FragmentActivity mActivity;
    private EditText txt_login, txt_password;
    CompositeDisposable disposable = new CompositeDisposable();

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment getInstance() {
        if (instance == null) {
            instance = new LoginFragment();
        }
        return instance;

    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        txt_login = view.findViewById(R.id.txt_login);
        txt_password = view.findViewById(R.id.txt_pass);
        Button btn_confirm = view.findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_login.getText().toString().trim().isEmpty()||
                        txt_password.getText().toString().trim().isEmpty()){
                    Toast.makeText(mActivity, "Please enter login and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (NetworkUtils.isNetworkAvailable(mActivity)){
                    getAccessToken();
                }else {
                    Toast.makeText(mActivity, "No internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void getAccessToken(){
        String login = txt_login.getText().toString();
        String password = txt_password.getText().toString();
        ApiAccount account = new ApiAccount(login, password);
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        disposable.add(api.getAccessToken(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) {
                        if (object!=null){
                            showAlert(object);

                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }));

    }

    private void showAlert(Object object){
        final AlertDialog.Builder builder = new AlertDialog.Builder(mActivity)
                .setTitle("Pass key info")
                .setMessage("Your pass key is: "+object)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentActivity) {
            mActivity = (FragmentActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
    }
}
