package com.zubisofts.kickcounterex.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zubisofts.kickcounterex.R;
import com.zubisofts.kickcounterex.viewmodel.KickStatisticsViewModel;

public class KickStatisticsFragment extends Fragment {

    private KickStatisticsViewModel mViewModel;

    public static KickStatisticsFragment newInstance() {
        return new KickStatisticsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.kick_statistics_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(KickStatisticsViewModel.class);
        // TODO: Use the ViewModel
    }

}