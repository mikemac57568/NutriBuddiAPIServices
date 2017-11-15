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

    private String label;

    private String imageName;

    private Blob imageBlob;

    @Column(name = "transaction_date", columnDefinition="DATETIME", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    private String geoLocation; //Temporary, change to location in future reference

    @JsonBackReference
    @OneToOne(mappedBy = "image")
    private Eats eats;

    public Image(){}




}
