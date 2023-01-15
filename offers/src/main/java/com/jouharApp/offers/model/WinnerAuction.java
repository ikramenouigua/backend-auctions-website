package com.jouharApp.offers.model;

import com.jouharApp.offers.enumeration.StatutAuction;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "auctionwinner")
public class WinnerAuction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "priceWin")
    private Double priceWin;

    @Column(name = "iduser")
    private Long iduser;

    @Column(name = "idseller")
    private Long idseller;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatutAuction status;


    @ManyToOne
    @JoinColumn(name="offer_id", nullable=false)
    private Offer offer;
}
