package com.jouharApp.offers.controller;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.service.AuctionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<Auction> > findOfferByOfferId (@PathVariable("id") Long id){
        List<Auction> auction = auctionService.getAuctionbyofferid(id);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }
    @GetMapping("/findWinner/{id}")
    public ResponseEntity<Auction> findWinnerOffer (@PathVariable("id") Long id){
       List<Auction> auctions = auctionService.getAuctionbyofferid(id);

        List<Auction> sortedUsers = auctions.stream()
                .sorted( Comparator.comparing( Auction::getPrice ).reversed() )
                .collect( Collectors.toList() );
        System.out.println(sortedUsers);
//        Collections.sort(auctions, new Comparator<Auction>() {
//            @Override
//            public int compare(Auction o1, Auction o2) {
//
//                int x= (int)Math.round (o1.getPrice()-o2.getPrice());
//                return x;
//            }
//
//
//        });

//        Collections.sort(auctions, new Comparator<Auction>() {
//
//            @Override
//            public int compare(Auction employee1, Auction employee2) {
//                return employee1.getPrice().compareTo(employee2.getPrice());
//            }
//
//        });
        Auction auction = sortedUsers.get(0);
        return new ResponseEntity<>(auction, HttpStatus.OK);
    }

}
