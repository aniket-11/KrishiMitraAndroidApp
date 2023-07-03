package com.aniket.krishimitraapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aniket.krishimitraapp.R;
import com.aniket.krishimitraapp.models.Crops;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

     ArrayList<Crops> cropsList = new ArrayList<Crops>();
     Context context;
     FragmentActivity fragmentActivity;

    NavController navController;

    public SearchAdapter(ArrayList<Crops> list, Context requireContext, FragmentActivity activity) {

        this.context = requireContext;
        this.cropsList = list;
        fragmentActivity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_crops, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Crops crops = cropsList.get(position);
        holder.textView.setText(crops.getCropsName());
//        holder.imageView.setImageResource(listdata[position].getImgId());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();

                bundle.putString("crop_name", String.valueOf(crops.getCropsName()));

                navController = Navigation.findNavController(fragmentActivity, R.id.nav_view);
                navController.navigate(R.id.action_searchFragment_to_crop_detailsFragment, bundle);

//                Toast.makeText(view.getContext(),"click on item: "+crops.getCropId(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cropsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public ConstraintLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }

    public void setData(ArrayList<Crops> cropsList){

        this.cropsList = cropsList;
        notifyDataSetChanged();

    }
}
