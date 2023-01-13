package com.jouharApp.offers.controller;
import com.jouharApp.offers.DTO.DTOOffer;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/offers")
public class OffersController {
    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/add")
    public ResponseEntity<Offer> addOffer(@RequestBody DTOOffer offer) {
        Offer newOffer = offerService.saveOffer( offer );
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);

    }

    @GetMapping("/allOffers")
    public ResponseEntity<List<Offer>> getAllOffers (){
        offerService.changeState();
        List<Offer> offers = offerService.getOffers();
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
    @GetMapping("/filterOffers/{surface}/{nbChambre}")
    public ResponseEntity<List<Offer>> FilterOffers (@PathVariable("surface") Long surface,@PathVariable("nbChambre") int nbChambre){
        List<Offer> offers = offerService.filterOffers(surface,nbChambre);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/getOffersByUser/{id}")
    public ResponseEntity<List<Offer>> getOffersByUser (@PathVariable("id") Long id){
        List<Offer> offers = offerService.getOffersByUser(id);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Offer> findOffer (@PathVariable("id") Long id){
        Offer offer = offerService.getOffer(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
}
