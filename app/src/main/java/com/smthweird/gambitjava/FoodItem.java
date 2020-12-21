package com.smthweird.gambitjava;

import com.google.gson.annotations.SerializedName;

public class FoodItem {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("image")
    private String image;
    @SerializedName("price")
    private Integer price;
    @SerializedName("oldPrice")
    private Integer oldPrice;
    @SerializedName("description")
    private String description;
    @SerializedName("isFavorite")
    private Object isFavorite;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Object isFavorite) {
        this.isFavorite = isFavorite;
    }
}





