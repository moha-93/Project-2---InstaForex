package com.moha.instaforexapp.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moha.instaforexapp.Adapter.ItemAdapter;
import com.moha.instaforexapp.Modal.Item;
import com.moha.instaforexapp.R;
import com.moha.instaforexapp.Rest.ApiClient;
import com.moha.instaforexapp.Rest.ApiInterface;
import com.moha.instaforexapp.Utility.NetworkUtils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SignalFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    public static SignalFragment instance;
    protected FragmentActivity mActivity;
    private RecyclerView recyclerView;
    private List<Item> itemList;
    private ItemAdapter adapter;
    CompositeDisposable disposable = new CompositeDisposable();
    public static String PASS_KEY ="8677ace21c37e55ba1e817f03ca3d9f60c14af23d2451861f0d3b319aa48d8ba";

    public SignalFragment() {
        // Required empty public constructor
    }

    public static SignalFragment getInstance() {
        if (instance == null) {
            instance = new SignalFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signal, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        if (NetworkUtils.isNetworkAvailable(mActivity)){
            LoadJson();
        }else {
            Toast.makeText(mActivity, "No internet connection", Toast.LENGTH_LONG).show();
        }

        return view;
    }

    public void LoadJson() {
        ApiInterface api = ApiClient.getRetrofit().create(ApiInterface.class);
        disposable.add(api.getAnalyticSignal(PASS_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Item>>() {
                    @Override
                    public void accept(List<Item> items) throws Exception {
                        try {
                            if (items != null) {
                                itemList = items;
                                adapter = new ItemAdapter(mActivity, itemList);
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            }
                        }catch (NumberFormatException e){
                            e.printStackTrace();
                            throw new Exception();
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Toast.makeText(mActivity, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }));
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposable.clear();
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
        mActivity=null;
    }

}
