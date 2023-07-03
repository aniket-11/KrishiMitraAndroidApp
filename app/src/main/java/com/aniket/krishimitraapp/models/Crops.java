package com.aniket.krishimitraapp.models;

public class Crops {

    int cropId;
    String cropsName;
    String cropImage;

    public Crops( int cropId, String cropsName, String cropImage){
        this.cropId = cropId;
        this.cropsName = cropsName;
        this.cropImage = cropImage;
    }

    public int getCropId(){
        return cropId;
    }

    public String getCropsName(){
        return cropsName;
    }

    public String getCropImage(){
        return cropImage;
    }
}
