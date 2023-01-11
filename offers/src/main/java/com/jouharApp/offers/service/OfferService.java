package com.jouharApp.offers.service;

import com.jouharApp.offers.DTO.DTOOffer;
import com.jouharApp.offers.model.Offer;

import java.util.List;

public interface OfferService {

    Offer getOffer(Long id);

    List<Offer> getOffers();

    void deleteCategory(Long id);

    public Offer saveOffer(DTOOffer offer);
    List<Offer> getOffersByUser(Long id);

}
