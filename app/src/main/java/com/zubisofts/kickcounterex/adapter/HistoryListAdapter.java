package com.zubisofts.kickcounterex.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zubisofts.kickcounterex.R;
import com.zubisofts.kickcounterex.model.Kick;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryListAdapter extends RecyclerView.Adapter<HistoryListAdapter.HistoryItemViewHolder> {

    ArrayList<Kick> kicks=new ArrayList<>();

    @NonNull
    @Override
    public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.kick_list_item, parent,false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
        Kick kick=kicks.get(position);
        if (kick!=null){
            long elapsedSeconds = kick.getDuration() / 1000;
            holder.txtDate.setText(new SimpleDateFormat("d MMM", Locale.getDefault()).format(kick.getStartTime()));
            holder.txtStart.setText(new SimpleDateFormat("h:m a", Locale.getDefault()).format(kick.getStartTime()));
            holder.txtDuration.setText(MessageFormat.format("{0}s", elapsedSeconds));
            holder.txtKicks.setText(String.valueOf(kick.getKicksCount()));
        }
    }

    @Override
    public int getItemCount() {
        return kicks.size();
    }

    public void setKicks(ArrayList<Kick> kicks) {
        this.kicks = kicks;
        notifyDataSetChanged();
    }

    class HistoryItemViewHolder extends RecyclerView.ViewHolder{

        private TextView txtDate, txtStart, txtDuration, txtKicks;

        public HistoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDate=itemView.findViewById(R.id.txtDate);
            txtStart=itemView.findViewById(R.id.txtStart);
            txtDuration=itemView.findViewById(R.id.txtDuration);
            txtKicks=itemView.findViewById(R.id.txtKicks);
        }
    }
}
