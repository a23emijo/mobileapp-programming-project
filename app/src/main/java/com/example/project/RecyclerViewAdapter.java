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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    // Fields
    private List<Animal> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    // Constructor
    RecyclerViewAdapter(Context context, List<Animal> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false));
    }

    // Sets the different values
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Animal animal = items.get(position);

        // Sets the different values except img and info
        holder.name.setText(animal.getName());
        holder.age.setText(String.valueOf(animal.getCompany() + " år"));
        holder.location.setText(animal.getLocation());
        holder.color.setText(animal.getCategory());
        holder.length.setText(String.valueOf(animal.getSize() + " m"));
        holder.weight.setText(String.valueOf(animal.getCost() + " kg"));

        // Sets the img and info fields values
        if (animal.getAuxdata() != null) {
            // Tries to load the JSON image three times
            Picasso.get().load(animal.getAuxdata().getImg()).into(holder.auxImage);

            // Placeholder that shows before the image is loaded
            Picasso.get().load(animal.getAuxdata().getImg()).placeholder(R.drawable.ic_launcher_background).into(holder.auxImage);

            // Shows if the original image couldn't load after 3 tries
            Picasso.get().load(animal.getAuxdata().getImg()).error(R.drawable.error);
        }
    }

    // Gets the size of the items list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Finds the different Views and adds a onClick to them
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView age;
        TextView location;
        TextView color;
        TextView length;
        TextView weight;
        ImageView auxImage;

        // Displays the value of the data on the View
        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            location = itemView.findViewById(R.id.location);
            color = itemView.findViewById(R.id.color);
            length = itemView.findViewById(R.id.length);
            weight = itemView.findViewById(R.id.weight);
            auxImage = itemView.findViewById(R.id.auxImage);
        }

        // Gets the position of the click on the different elements in the RecyclerView
        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    // Create a interface for onClick on Animal
    public interface OnClickListener {
        void onClick(Animal animal);
    }

    // Sort by A to Z
    public void sortByAToZ(){
        Collections.sort(items, new Comparator<Animal>() {
            @Override
            public int compare(Animal animal1, Animal animal2) {
                return animal1.getName().compareTo(animal2.getName());
            }
        });
    }

    // Sorts by Z to A
    public void sortByZToA(){
        Collections.sort(items, new Comparator<Animal>() {
            @Override
            public int compare(Animal animal1, Animal animal2) {
                return animal2.getName().compareTo(animal1.getName());
            }
        });
    }
}