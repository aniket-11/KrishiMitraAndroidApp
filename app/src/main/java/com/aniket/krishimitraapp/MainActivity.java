package com.aniket.krishimitraapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.aniket.krishimitraapp.fragments.NotificationFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {




    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initToolbar();
        initNavigation();

    }




    private void initToolbar(){
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
    }

    private void initNavigation(){

        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        NavController navController = Navigation.findNavController(this, R.id.nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

    }

//    private void initFirestore(){
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//
//
//        String[] cropNames = {"Wheat", "Bajra", "Maize", "Soybean", "Green Gram", "Black Gram", "Fenugreek", "Clove", "Black Pepper",
//        "Coriander", "Chilli", "Cashew", "Garlic", "Ginger", "Bitter Gourd", "Turmeric", "Cardamon", "Tea"};
//
//
//        for (int id = 0; id<cropNames.length; id++) {
//
//            Map<String, Object> user = new HashMap<>();
//            user.put("id", id+ 1);
//            user.put("name", "");
//            user.put("fertilizer", "");
//            user.put("introduction", "");
//            user.put("climate_soil", "");
//            user.put("sowing", "");
//            user.put("irrigation", "");
//            user.put("diseases", "");
//            user.put("harvesting", "");
//
//            db.collection("crops").document("crops_info").collection("crops")
//                    .document(cropNames[id]).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Log.d("fdfdfg", "DocumentSnapshot successfully written!");
//
//                }
//            });
//
//        }
//    }
}