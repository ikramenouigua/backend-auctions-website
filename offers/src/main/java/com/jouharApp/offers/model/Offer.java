package com.jouharApp.offers.model;


import lombok.Data;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(name="image_id", nullable=false)
    private Image image;





}