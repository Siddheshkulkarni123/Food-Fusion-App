package com.example.foodrecipeproject;

public class FoodData {

    private String itemName;
    private String itemDiscription;
    private String time;
    private String itemImage;


    public FoodData() {
        // Default constructor required for Firebase
    }
    public FoodData(String itemName, String itemDiscription, String time, String itemImage) {
        this.itemName = itemName;
        this.itemDiscription = itemDiscription;
        this.time = time;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDiscription() {
        return itemDiscription;
    }

    public String getTime() {
        return time;
    }

    public String getItemImage() {
        return itemImage;
    }
}

//public class FoodData {
//
//    private String itemName;
//    private String itemDescription;
//    private String time;
//    private String itemImage;
//
//    // Required default constructor for Firebase
//    public FoodData() {
//        // Default constructor required for Firebase
//    }
//
//    public FoodData(String itemName, String itemDescription, String time, String itemImage) {
//        this.itemName = itemName;
//        this.itemDescription = itemDescription;
//        this.time = time;
//        this.itemImage = itemImage;
//    }
//
//    public String getItemName() {
//        return itemName;
//    }
//
//    public String getItemDescription() {
//        return itemDescription;
//    }
//
//    public String getTime() {
//        return time;
//    }
//
//    public String getItemImage() {
//        return itemImage;
//    }
//}

