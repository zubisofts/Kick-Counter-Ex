package com.zubisofts.kickcounterex.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zubisofts.kickcounterex.R;
import com.zubisofts.kickcounterex.adapter.HistoryListAdapter;
import com.zubisofts.kickcounterex.model.Kick;
import com.zubisofts.kickcounterex.viewmodel.KickCounterViewModel;
import com.zubisofts.kickcounterex.viewmodel.KickHistoryViewModel;

import java.util.ArrayList;

public class KickHistoryFragment extends Fragment {

    private static KickHistoryViewModel mViewModel;

    public static KickHistoryFragment newInstance() {
        return new KickHistoryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kick_history_fragment, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider.NewInstanceFactory().create(KickHistoryViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView historyList=view.findViewById(R.id.historyList);
        final HistoryListAdapter adapter=new HistoryListAdapter();
        historyList.setAdapter(adapter);
        mViewModel.getHistory(getContext());

        mViewModel.history.observe(getViewLifecycleOwner(), new Observer<ArrayList<Kick>>() {
            @Override
            public void onChanged(ArrayList<Kick> kicks) {
                if (!kicks.isEmpty()){
                    adapter.setKicks(kicks);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getHistory(getContext());
    }
}