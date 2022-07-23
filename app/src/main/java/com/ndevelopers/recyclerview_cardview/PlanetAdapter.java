package com.ndevelopers.recyclerview_cardview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class PlanetAdapter extends RecyclerView.Adapter<PlanetAdapter.PlanetHolder>{

    private Context context;
    private ArrayList<Planet> planets;
    private AdapterView.OnItemClickListener listener;

    public interface onItemClickListener {
        void onItemClick(Planet itemPlanet);
    }

    public PlanetAdapter(Context context, ArrayList<Planet> planets) {
        this.context = context;
        this.planets = planets;
        this.listener = listener;
    }

    public class PlanetHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtDistance, txtGravity, txtDiameter;

        public PlanetHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDistance = itemView.findViewById(R.id.txtDistance);
            txtGravity = itemView.findViewById(R.id.txtGravity);
            txtDiameter = itemView.findViewById(R.id.txtDiameter);
        }

        public void setDetails(final Planet planet) {
            txtName.setText(planet.getPlanetName());
            txtDistance.setText(String.format(Locale.US, "Distance from Sun : %d Million KM", planet.getDistanceFromSun()));
            txtGravity.setText(String.format(Locale.US, "Surface Gravity : %d N/kg", planet.getGravity()));
            txtDiameter.setText(String.format(Locale.US, "Diameter : %d KM", planet.getDiameter()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent intent = new Intent(itemView.getContext(), MainActivity.class);
                    //intent.putExtra("itemDetail", planet);
                    //itemView.getContext().startActivity(intent);
                    Toast.makeText(view.getContext(),planet.getPlanetName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    @NonNull
    @Override
    public PlanetHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);
        return new PlanetHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetHolder planetHolder, int i) {
        Planet planet = planets.get(i);
        planetHolder.setDetails(planet);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }
}
