package com.jouharApp.offers.service;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.model.Auction;

import java.util.List;

public interface AuctionService {
    Auction getAuction(Long id);

    List<Auction> getAuctionbyofferid(Long id);

    List<Auction> getAuctions();

    public Auction saveAuction(DtoAuction dtoAuction);
}
