package edu.upc.dsa.dsa_error404_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;


import java.util.List;

public class TeamRankingAdapter extends RecyclerView.Adapter<TeamRankingAdapter.TeamViewHolder> {

    private Context context;
    private List<TeamRanking> teamList;
    private OnItemClickListener listener;

    // Interfaz para manejar clics en cada item
    public interface OnItemClickListener {
        void onItemClick(TeamRanking team);
    }

    public TeamRankingAdapter(Context context, List<TeamRanking> teamList, OnItemClickListener listener) {
        this.context = context;
        this.teamList = teamList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_team_ranking, parent, false);
        return new TeamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        TeamRanking team = teamList.get(position);
        holder.bind(team, listener);
    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    // ViewHolder sin ImageView
    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        TextView name, points;
        ImageView teamLogo;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teamName);
            points = itemView.findViewById(R.id.teamPoints);
            teamLogo = itemView.findViewById(R.id.teamLogoImageView);
        }

        public void bind(final TeamRanking team, final OnItemClickListener listener) {
            name.setText(team.getName());
            points.setText("Puntos: " + team.getPoints());

            itemView.setOnClickListener(v -> listener.onItemClick(team));

            itemView.setOnClickListener(v -> listener.onItemClick(team));
            String avatarUrl = team.getAvatar();

            if (avatarUrl != null && !avatarUrl.isEmpty()) {
                Glide.with(itemView.getContext())
                        .load(avatarUrl) // URL del Avatar
                        .placeholder(R.drawable.default_logo)
                        .into(teamLogo);
            } else {
                // Si la URL es nula o vacía, mostramos solo la imagen por defecto
                teamLogo.setImageResource(R.drawable.default_logo);
            }

            itemView.setOnClickListener(v -> listener.onItemClick(team));
        }
    }
}
