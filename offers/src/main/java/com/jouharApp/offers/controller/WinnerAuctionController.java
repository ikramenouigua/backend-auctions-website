package com.jouharApp.offers.controller;



import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.model.WinnerAuction;
import com.jouharApp.offers.service.imp.WinnerAucService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/winnerAuction")

public class WinnerAuctionController {
    @Autowired
    private final WinnerAucService winnerAucService;

    public WinnerAuctionController(WinnerAucService winnerAucService) {
        this.winnerAucService = winnerAucService;
    }

    @GetMapping("/getByuser/{id}")
    public ResponseEntity<List<WinnerAuction>> findWinnerAuctionByuser (@PathVariable("id") Long id){
        List<WinnerAuction> WinnerAuctions = winnerAucService.getWinnerAuctionBuuser( id );
        return new ResponseEntity<>(WinnerAuctions, HttpStatus.OK);
    }
    @GetMapping("/sellerwinauction/{id}")
    public ResponseEntity<List<WinnerAuction>> WinnerAuctionByseller (@PathVariable("id") Long id){
        List<WinnerAuction> WinnerAuctions = winnerAucService.getWinnerAuctiionbyseller( id );
        return new ResponseEntity<>(WinnerAuctions, HttpStatus.OK);
    }
    @GetMapping("/getoffer/{id}")
    public ResponseEntity<Offer> getOfferByid (@PathVariable("id") Long id){
        Offer offer= winnerAucService.getOffer( id );
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }

    @GetMapping("/accepter/{id}")
    public ResponseEntity<String> accepterOffre (@PathVariable("id") Long id){
        winnerAucService.accepterOffre( id );
        return new ResponseEntity<>("good", HttpStatus.OK);
    }

    @GetMapping("/refuser/{id}")
    public ResponseEntity<String> refuserOffre (@PathVariable("id") Long id){
        winnerAucService.refuserOffre( id );
        return new ResponseEntity<>("good", HttpStatus.OK);
    }

}