package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Fields
    private List<Animal> items;
    private LayoutInflater layoutInflater;

    // Constructor
    RecyclerViewAdapter(Context context, List<Animal> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = items.get(position);

        // Displays the different values except img and info
        holder.name.setText(animal.getName());
        holder.age.setText(String.valueOf(animal.getCompany()));
        holder.location.setText(animal.getLocation());
        holder.color.setText(animal.getCategory());
        holder.length.setText(String.valueOf(animal.getSize()));
        holder.weight.setText(String.valueOf(animal.getCost()));

        // Displays the img and info fields values
        if (animal.getAuxdata() != null) {
            Picasso.get().load(animal.getAuxdata().getImg()).into(holder.auxImage);
            Picasso.get().load(animal.getAuxdata().getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.auxImage);
            holder.auxInfo.setText(animal.getAuxdata().getInfo());
        }
    }

    // Gets the size of the items list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Adds the different Views
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        TextView location;
        TextView color;
        TextView length;
        TextView weight;
        ImageView auxImage;
        TextView auxInfo;

        // Sets the value of the data onto the View
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            location = itemView.findViewById(R.id.location);
            color = itemView.findViewById(R.id.color);
            length = itemView.findViewById(R.id.length);
            weight = itemView.findViewById(R.id.weight);
            auxImage = itemView.findViewById(R.id.auxImage);
            auxInfo = itemView.findViewById(R.id.auxInfo);
        }
    }

    // Updates the adapter
    public void updateAdapter(ArrayList<Animal> newItems){
        items.addAll(newItems);
    }
}