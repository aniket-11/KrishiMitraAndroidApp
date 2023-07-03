package com.aniket.krishimitraapp.fragments;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.provider.Settings;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aniket.krishimitraapp.MainActivity;
import com.aniket.krishimitraapp.R;

import java.util.Date;


public class WeathersFragment extends Fragment {


    private static final int REQUEST_LOCATION =  1;
    LocationManager locationManager;
    String latitude, longitude;
    Double myLatitude;
    Double myLongitude;
    String city;

    public static String BaseUrl = "http://api.openweathermap.org/";
    public static String AppId = "1d7e2308fd54fb32e33bc5a05916bb48";
    public static String lat = "35";
    public static String lon = "139";


    TextView cityName;
    TextView countryName;
    TextView date;
    TextView temperature;
    TextView description;
    TextView wind;
    TextView humidity;
    TextView rain;

    String cityText;
    String countryText;
    String dateText;
    String temperatureText;
    String descriptionText;
    String windText;
    String humidityText;
    String rainText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weathers, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Weather");


        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            OnGPS();
        } else {
            getLocation();
            getCurrentData();

        }


        initComponents(view);

    }




        private  void  initComponents(View view){

            cityName = view.findViewById(R.id.cityName);
            countryName = view.findViewById(R.id.countryName);
            date = view.findViewById(R.id.date);
            temperature = view.findViewById(R.id.temp);
            description = view.findViewById(R.id.sky);
            wind = view.findViewById(R.id.windText);
            humidity = view.findViewById(R.id.humidityText);
            rain = view.findViewById(R.id.rainText);



        }

        void getCurrentData() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            WeatherService service = retrofit.create(WeatherService.class);
            Call<WeatherResponse> call = service.getCurrentWeatherData(String.valueOf(myLatitude), String.valueOf(myLongitude), AppId);
            call.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                    if (response.code() == 200) {
                        WeatherResponse weatherResponse = response.body();
                        assert weatherResponse != null;


                        cityText = weatherResponse.name;
                        countryText = weatherResponse.sys.country;
                        temperatureText = weatherResponse.main.temp + "\u2103";
                        descriptionText = weatherResponse.weather.get(0).description;
                        windText = weatherResponse.wind.speed + "Km/hr";
                        humidityText = weatherResponse.main.humidity+ "%";
//                    if (weatherResponse.rain.h3> 0)
//                    rainText = weatherResponse.rain.h3 + "%";

                        Date date11 = new Date();

                        String dayOfTheWeek = (String) DateFormat.format("EEEE", date11); // Thursday
                        String day          = (String) DateFormat.format("dd",   date11); // 20
                        String monthString  = (String) DateFormat.format("MMM",  date11); // Jun
                        String monthNumber  = (String) DateFormat.format("MM",   date11); // 06
                        String year         = (String) DateFormat.format("yyyy", date11); // 2013

                        String theDate = dayOfTheWeek.substring(0,2) + ","+ day + " " + monthString;

                        cityName.setText(cityText);
                        countryName.setText(countryText);
                        date.setText(theDate);
                        temperature.setText(temperatureText);
                        description.setText(descriptionText);
                        wind.setText("Wind\n"+windText);
                        humidity.setText("Humidity\n"+humidityText);
                        if (rainText!=null)
                            rain.setText("Rain\n" +rainText);
                        else
                            rain.setText("Rain\n" +"0%");


                    }
                }

                @Override
                public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
//                weatherData.setText(t.getMessage());
                }
            });
        }

//    public void getCityNames() {
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//        List<Address> addresses = null;
//        try {
//            addresses = geocoder.getFromLocation(myLatitude, myLongitude, 1);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String cityName = addresses.get(0).getAddressLine(0);
//        String stateName = addresses.get(0).getAddressLine(1);
//        String countryName = addresses.get(0).getAddressLine(2);
//
//        Toast.makeText(this, cityName, Toast.LENGTH_SHORT).show();
//    }


        private void OnGPS() {
            final AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            final AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        private void getLocation() {
            if (ActivityCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            } else {
                Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (locationGPS != null) {
                    double lat = locationGPS.getLatitude();
                    double longi = locationGPS.getLongitude();
                    myLatitude = locationGPS.getLatitude();
                    myLongitude = locationGPS.getLongitude();

                    latitude = String.valueOf(lat);
                    longitude = String.valueOf(longi);
//                showLocation.setText("Your Location: " + "\n" + "Latitude: " + latitude + "\n" + "Longitude: " + longitude);
                } else {
                    Toast.makeText(requireActivity(), "Unable to find location.", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
