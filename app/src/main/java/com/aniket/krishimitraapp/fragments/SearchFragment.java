package com.aniket.krishimitraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aniket.krishimitraapp.R;
import com.aniket.krishimitraapp.adapters.SearchAdapter;
import com.aniket.krishimitraapp.models.Crops;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class SearchFragment extends Fragment {

    ArrayList<Crops> list = new ArrayList<>();
    RecyclerView recyclerView;
    SearchAdapter searchAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Search");


        //initOnce();

        fillCropList();


        initRecyclerview(view);

    }


    private void fillCropList(){



        // Access a Cloud Firestore instance from your Activity
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);

        list = new ArrayList<>();

        db.collection("crops").document("crops_info").collection("crops").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    int k =1;
                    for (QueryDocumentSnapshot document : task.getResult()) {


//                        listCrops.add(document.getId());
                        list.add(new Crops(k, document.getId(), "Cotton_image"));
                        k++;

                    }

                    searchAdapter.setData(list);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });





    }


    private void initRecyclerview(View view){

        recyclerView = view.findViewById(R.id.recyclerview);
        searchAdapter = new SearchAdapter(list, requireContext(),getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        recyclerView.setAdapter(searchAdapter);
    }


    private void  initOnce(){

        list = new ArrayList<>();

        list.add(new Crops(1, "Bajra", "Cotton_image"));
        list.add(new Crops(2, "Bitter Gourd", "Cotton_image"));
        list.add(new Crops(3, "Black Gram", "Cotton_image"));
        list.add(new Crops(4, "Black Pepper", "Cotton_image"));
        list.add(new Crops(5, "Cardamon", "Cotton_image"));
        list.add(new Crops(6, "Carrot", "Cotton_image"));
        list.add(new Crops(7, "Cashew", "Cotton_image"));
        list.add(new Crops(8, "Chilli", "Cotton_image"));
        list.add(new Crops(9, "Clove", "Cotton_image"));
        list.add(new Crops(10, "Coriander", "Cotton_image"));
        list.add(new Crops(11, "Fenugreek", "Cotton_image"));
        list.add(new Crops(12, "Garlic", "Cotton_image"));
        list.add(new Crops(13, "Ginger", "Cotton_image"));
        list.add(new Crops(14, "Green Gram", "Cotton_image"));
        list.add(new Crops(15, "Jowar", "Cotton_image"));
        list.add(new Crops(16, "Maize", "Cotton_image"));
        list.add(new Crops(17, "Mustard", "Cotton_image"));
        list.add(new Crops(18, "Onion", "Cotton_image"));
        list.add(new Crops(19, "Potato", "Cotton_image"));
        list.add(new Crops(20, "Soya", "Cotton_image"));
        list.add(new Crops(21, "Soyabean", "Cotton_image"));
        list.add(new Crops(22, "Tea", "Cotton_image"));
        list.add(new Crops(23, "Tomato", "Cotton_image"));
        list.add(new Crops(24, "Toor", "Cotton_image"));
        list.add(new Crops(25, "Turmeric", "Cotton_image"));
        list.add(new Crops(26, "Wheat", "Cotton_image"));
        list.add(new Crops(27, "Cotton", "Cotton_image"));


        FirebaseFirestore db = FirebaseFirestore.getInstance();



        for (int i=0; i< list.size(); i++) {


            Map<String, Object> data1 = new HashMap<>();
            data1.put("crop_id", list.get(i).getCropId());
            data1.put("crop_name", list.get(i).getCropsName());
            data1.put("crop_image", list.get(i).getCropImage());
            data1.put("crop_introduction", list.get(i).getCropsName() + "Introduction");
            data1.put("crop_climate_soil", list.get(i).getCropsName() + "Climate and Soil");
            data1.put("crop_sowing", list.get(i).getCropsName() + "Crop sowing and planting");
            data1.put("crop_irrigation", list.get(i).getCropsName() + "irrigation");
            data1.put("crop_fertilizer", list.get(i).getCropsName() + "crop fertilizer management");
            data1.put("crop_diseases", list.get(i).getCropsName() + "diseases and prevention");
            data1.put("crop_harvesting", list.get(i).getCropsName() + "harvesting");


            db.collection("crops").document("crops_info").collection("crops").document(list.get(i).getCropsName())
                    .set(data1)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });


        }


    }
}