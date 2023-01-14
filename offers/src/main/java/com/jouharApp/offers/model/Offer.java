package com.jouharApp.offers.model;


import com.jouharApp.offers.enumeration.StateOffer;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "iduser")
    private Long iduser;

    @Column(name = "surface")
    private Long surface;

    @Column(name = "nbchambre")
    private int nbChambre;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "address")
    private String address;

    @Column(name = "duration")
    private Date duration;

    @Column(name = "initialprice")
    private Double initialprice;

    @Column(name = "start_date")
    private Date debutAuction;

    @Column(name = "imagename")
    private String imagename;

    @Column(name = "stateoffer")
    @Enumerated(EnumType.STRING)
    private StateOffer stateoffer;

    @OneToOne
    @JoinColumn(name="image_id", nullable=false)
    private Image image;





}