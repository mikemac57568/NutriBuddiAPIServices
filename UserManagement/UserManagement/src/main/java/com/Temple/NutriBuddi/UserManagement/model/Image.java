package com.Temple.NutriBuddi.UserManagement.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String foodName;    //input by user if all else fails

    private String fileName;

    @Lob
    private Blob imageBlob;

    @Column(name = "transaction_date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private String geoLocation; //Temporary, change to location in future reference

    @JsonBackReference
    @OneToOne(mappedBy = "image")
    private Eats eats;

    public Image(){}

    public Image(String foodName, String fileName, Blob imageBlob, Eats eats) {
        this.foodName = foodName;
        this.fileName = fileName;
        this.imageBlob = imageBlob;
        this.eats = eats;
    }

    public Image(String foodName, String fileName, Blob imageBlob, String geoLocation, Eats eats) {
        this.foodName = foodName;
        this.fileName = fileName;
        this.imageBlob = imageBlob;
        this.geoLocation = geoLocation;
        this.eats = eats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Blob getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(Blob imageBlob) {
        this.imageBlob = imageBlob;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public Eats getEats() {
        return eats;
    }

    public void setEats(Eats eats) {
        this.eats = eats;
    }
}
