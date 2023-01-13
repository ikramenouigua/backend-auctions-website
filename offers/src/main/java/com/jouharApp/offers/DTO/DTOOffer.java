package com.jouharApp.offers.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class DTOOffer {
    private String title;


    private String description;


    private String address;


    private Date duration;
    private Long surface;
    private int nbChambre;

    private Long iduser;
    private Double initialprice;


    private Date debutAuction;



    private String imagename;
}
