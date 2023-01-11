package com.jouharApp.offers.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @Column(name = "iduser")
    private Long iduser;

    @Column(name = "state")
    private boolean state;

    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private Offer offer;



}