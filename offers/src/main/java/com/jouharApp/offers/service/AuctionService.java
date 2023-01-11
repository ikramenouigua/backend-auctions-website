package com.jouharApp.offers.service;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.model.Auction;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    Auction getAuction(Long id);

    Optional<List<Auction>> getAuctionbyofferid(Long id);

    List<Auction> getAuctions();

    public Auction saveAuction(DtoAuction dtoAuction);
}
