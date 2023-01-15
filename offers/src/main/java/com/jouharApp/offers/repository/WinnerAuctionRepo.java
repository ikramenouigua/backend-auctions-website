package com.jouharApp.offers.repository;

import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.model.WinnerAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WinnerAuctionRepo extends JpaRepository<WinnerAuction, Long> {

    List<WinnerAuction> findWinnerAuctionByIduser(Long id);

    List<WinnerAuction> findWinnerAuctionByOffer(Long id);

    WinnerAuction findWinnerAuctionById(Long id);
}
