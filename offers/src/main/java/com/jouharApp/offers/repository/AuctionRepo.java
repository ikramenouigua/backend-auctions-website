package com.jouharApp.offers.repository;

import com.jouharApp.offers.model.Auction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepo extends JpaRepository<Auction, Long> {
    Optional<Auction> findAuctionById(Long id);
    List<Auction> findAuctionByOffer_Id(Long id);
}
