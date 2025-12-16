package edu.upc.dsa.dsa_error404_android;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamRankingActivity extends AppCompatActivity {

    private RecyclerView teamRankingRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_ranking);

        teamRankingRecycler = findViewById(R.id.teamRankingRecycler);
        teamRankingRecycler.setLayoutManager(new LinearLayoutManager(this));

        loadTeamRanking();
    }

    private void loadTeamRanking() {
        ApiService api = RetrofitClient.getInstance().getMyApi();
        Call<List<TeamRanking>> call = api.getTeamsRanking();

        call.enqueue(new Callback<List<TeamRanking>>() {
            @Override
            public void onResponse(Call<List<TeamRanking>> call, Response<List<TeamRanking>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TeamRankingAdapter adapter = new TeamRankingAdapter(
                            TeamRankingActivity.this,
                            response.body(),
                            team -> {
                                // Manejo de clic en item
                                Toast.makeText(TeamRankingActivity.this,
                                        "Has pulsado: " + team.getName(),
                                        Toast.LENGTH_SHORT).show();
                            }
                    );
                    teamRankingRecycler.setAdapter(adapter);
                } else {
                    Toast.makeText(TeamRankingActivity.this,
                            "Error al cargar el ranking",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<TeamRanking>> call, Throwable t) {
                Toast.makeText(TeamRankingActivity.this,
                        "Error de conexión",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
