package com.aniket.krishimitraapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aniket.krishimitraapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class crop_detailsFragment extends Fragment {


    TextView cropName;
    TextView cropIntroduction;
    TextView cropClimateAndSoil;
    TextView cropSowing;
    TextView cropIrrigation;
    TextView cropFertilizer;
    TextView cropDiseases;
    TextView cropHarvesting;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_crop_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cropName = view.findViewById(R.id.textView2);
        cropIntroduction = view.findViewById(R.id.textView4);
        cropClimateAndSoil = view.findViewById(R.id.textView6);
        cropSowing = view.findViewById(R.id.textView8);
        cropIrrigation = view.findViewById(R.id.textView10);
        cropFertilizer = view.findViewById(R.id.textView12);
        cropDiseases = view.findViewById(R.id.textView14);
        cropHarvesting = view.findViewById(R.id.textView16);



        initData();
    }


    private void initData(){


        Toast.makeText(requireContext(), getArguments().getString("crop_name"), Toast.LENGTH_SHORT).show();



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);


        DocumentReference docRef = db.collection("crops").
                document("crops_info").
                collection("crops").
                document(getArguments().getString("crop_name"));
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("sdgdf", "DocumentSnapshot data: " + document.getData());

                        cropName.setText(document.get("crop_name").toString());
                        cropIntroduction.setText(document.get("crop_introduction").toString());
                        cropClimateAndSoil.setText(document.get("crop_climate_soil").toString());
                        cropSowing.setText(document.get("crop_sowing").toString());
                        cropIrrigation.setText(document.get("crop_irrigation").toString());
                        cropFertilizer.setText(document.get("crop_fertilizer").toString());
                        cropDiseases.setText(document.get("crop_diseases").toString());
                        cropHarvesting.setText(document.get("crop_harvesting").toString());

                    } else {
                        Log.d("sdfg", "No such document");
                    }
                } else {
                    Log.d("dsfdf", "get failed with ", task.getException());
                }
            }
        });


    }
}