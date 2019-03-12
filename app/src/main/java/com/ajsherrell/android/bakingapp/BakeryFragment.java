package com.ajsherrell.android.bakingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ajsherrell.android.bakingapp.Adapters.BakeryAdapter;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.Utils.NetworkApiCallback;
import com.ajsherrell.android.bakingapp.Utils.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BakeryFragment extends Fragment {

    private static final String TAG = BakeryFragment.class.getSimpleName();

    private static String BAKERY_KEY = "bakery_key";

    @BindView(R.id.bakery_recycler_view)
    RecyclerView bakeryRecyclerView;

    @BindView(R.id.no_content)
    TextView noContent;

    private List<Bakery> bakeryList;

    private Unbinder unbinder;

    private OnBakeryClickListener clickListener;

    public BakeryFragment() {}

    private final BroadcastReceiver networkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (bakeryList == null) {
                loadBakery();
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bakery, container, false);
        unbinder = ButterKnife.bind(this, view);

        noContent.setVisibility(View.VISIBLE);
        setRecyclerView();

        if (savedInstanceState != null && savedInstanceState.containsKey(BAKERY_KEY)) {
            bakeryList = savedInstanceState.getParcelableArrayList(BAKERY_KEY);

            bakeryRecyclerView.setAdapter(new BakeryAdapter(getActivity().getApplicationContext(), bakeryList, new Constants.ClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    clickListener.onBakerySelected(bakeryList.get(position));
                }
            }));
            contentLoaded();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBakeryClickListener) {
            clickListener = (OnBakeryClickListener) context;
        } else {
            throw new RuntimeException(context.toString() + " implement OnBakeryClickListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(networkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(networkReceiver);
    }

    public interface OnBakeryClickListener {
        void onBakerySelected(Bakery bakery);
    }

    private void contentLoaded() {
        boolean isLoaded = bakeryList != null && bakeryList.size() > 0;
        bakeryRecyclerView.setVisibility(isLoaded ? View.VISIBLE : View.GONE);
        noContent.setVisibility(isLoaded ? View.GONE : View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (bakeryList != null && !bakeryList.isEmpty()) {
            outState.putParcelableArrayList(BAKERY_KEY, (ArrayList<? extends Parcelable>) bakeryList);
        }
    }

    private void setRecyclerView() {
        bakeryRecyclerView.setVisibility(View.GONE);
        bakeryRecyclerView.setHasFixedSize(true);

        bakeryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        bakeryRecyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());
    }

    private void loadBakery() {
        if (Constants.isNetwork(getActivity().getApplicationContext())) {

            NetworkUtil.getInstance().getBakery(new NetworkApiCallback<List<Bakery>>() {
                @Override
                public void onResponse(List<Bakery> result) {
                    if (result != null) {
                        bakeryList = result;
                        bakeryRecyclerView.setAdapter(new BakeryAdapter(getActivity().getApplicationContext(), bakeryList, new Constants.ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                clickListener.onBakerySelected(bakeryList.get(position));
                            }
                        }));
                        // todo default bakery widget
                    } else {
                        noContent.setVisibility(View.VISIBLE);
                        noContent.setText(getString(R.string.no_bakery_content));
                        Toast.makeText(getActivity(), "No baking data", Toast.LENGTH_LONG).show();
                    }
                    contentLoaded();
                }

                @Override
                public void onCancel() {
                    contentLoaded();
                }
            });
        } else {
            noContent.setVisibility(View.VISIBLE);
            noContent.setText(getString(R.string.no_internet_connection));
            Toast.makeText(getActivity(), "No internet connection", Toast.LENGTH_LONG).show();
        }
    }
}
