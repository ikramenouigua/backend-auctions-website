package com.jouharApp.offers.controller;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    @PostMapping("/add")
    public ResponseEntity<Auction> addBid( @RequestBody DtoAuction dtoAuction) {
        Auction newbid = auctionService.saveAuction( dtoAuction );
        return new ResponseEntity<>(newbid, HttpStatus.CREATED);

    }

    @GetMapping("/allAuctions")
    public ResponseEntity<List<Auction>> getAllAuctions (){
        List<Auction> auctions = auctionService.getAuctions();
        return new ResponseEntity<>(auctions, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Auction> findOffer (@PathVariable("id") Long id){
        Auction auction = auctionService.getAuction(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

    @GetMapping("/findbyoffer/{id}")
    public ResponseEntity<Optional<List<Auction>> > findOfferByOfferId (@PathVariable("id") Long id){
        Optional<List<Auction>> auction = auctionService.getAuctionbyofferid(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }
}
