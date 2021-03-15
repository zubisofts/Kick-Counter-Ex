package com.zubisofts.kickcounterex.ui.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.zubisofts.kickcounterex.R;
import com.zubisofts.kickcounterex.viewmodel.KickCounterViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class KickFragment extends Fragment {

    private KickCounterViewModel kickCounterViewModel;
    private static KickSessionListener kickSessionListener;

    public static KickFragment newInstance(KickSessionListener listener) {
        KickFragment fragment = new KickFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        kickSessionListener=listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kickCounterViewModel = new ViewModelProvider.NewInstanceFactory().create(KickCounterViewModel.class);

    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_kick, container, false);

        final CircularProgressBar circularProgressBar = root.findViewById(R.id.circularProgressBar);
        final TextView txtCountInfo = root.findViewById(R.id.kick_text_info);
        final TextView txtKickCount = root.findViewById(R.id.txtKicksCount);
        final TextView txtFirstKickDate = root.findViewById(R.id.txtFirstKickDate);
        final TextView txtLastKickDate = root.findViewById(R.id.txtLastKickDate);
        MaterialCardView btnStart = root.findViewById(R.id.btnStartKick);
        MaterialButton btnReset = root.findViewById(R.id.btnReset);
        final MaterialButton btnComplete = root.findViewById(R.id.btnComplete);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (kickCounterViewModel.getActiveSession().getValue()) {
                    kickCounterViewModel.increaseKickCount();
                } else {
                    new AlertDialog.Builder(getContext())
                            .setMessage("Start new session?")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            })
                            .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    kickCounterViewModel.resetKickCount();
                                    kickCounterViewModel.increaseKickCount();
                                    kickCounterViewModel.setActive(true);
                                }
                            }).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getContext())
                        .setMessage("Start new session?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                kickCounterViewModel.setActive(false);
                                kickCounterViewModel.resetKickCount();
                                txtFirstKickDate.setText("-");
                                txtLastKickDate.setText("-");
                                txtCountInfo.setText(getResources().getString(R.string.click_on_foot_icon_on_first_click));
                            }
                        }).show();

            }
        });

        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setMessage("Start new session?")
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("Complete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                kickCounterViewModel.setActive(false);
                                txtFirstKickDate.setText("-");
                                txtLastKickDate.setText("-");
                                int totalKicks = kickCounterViewModel.getKickCount().getValue();
                                txtCountInfo.setText("Good job, " + totalKicks + " kicks in 2 minutes for today");
                                kickCounterViewModel.addKick(getContext());
                                kickCounterViewModel.resetKickCount();
                            }
                        }).show();
            }
        });

        kickCounterViewModel.getKickCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer count) {
                circularProgressBar.setProgress((float) (count * 10));
                if (count > 0) {
                    txtKickCount.setText(String.valueOf(count));
                    kickCounterViewModel.setLastTime();
                } else {
                    txtKickCount.setText("-");
                }

            }
        });

        kickCounterViewModel.getActiveSession().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                kickSessionListener.onSessionChanged(aBoolean);
                if (aBoolean) {
                    txtCountInfo.setText(R.string.count_progress_label);
                    kickCounterViewModel.setFirstTime();
                    root.findViewById(R.id.actionLayout).setVisibility(View.VISIBLE);
                } else {
                    root.findViewById(R.id.actionLayout).setVisibility(View.GONE);
                }
            }
        });

        kickCounterViewModel.getFirstTime().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {
                txtFirstKickDate.setText(new SimpleDateFormat("h:mm a", Locale.getDefault()).format(date));

            }
        });

        kickCounterViewModel.getLastTime().observe(getViewLifecycleOwner(), new Observer<Date>() {
            @Override
            public void onChanged(Date date) {

                txtLastKickDate.setText(new SimpleDateFormat("h:mm a", Locale.getDefault()).format(date));
            }
        });

        return root;
    }

    public interface KickSessionListener{
        public void onSessionChanged(boolean isActive);
    }

}