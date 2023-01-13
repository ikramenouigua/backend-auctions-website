package com.jouharApp.offers.service.imp;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.exception.BidNotFoundException;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.repository.AuctionRepo;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.service.AuctionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImp implements AuctionService {

    final AuctionRepo auctionRep ;
    final OfferRepo offerRep;

    public AuctionServiceImp(AuctionRepo auctionRep, OfferRepo offerRep) {

        this.auctionRep = auctionRep;
        this.offerRep = offerRep;
    }

    @Override
    public Auction getAuction(Long id) {
        return  auctionRep.findAuctionById(id).orElseThrow(()->new BidNotFoundException("auction by id"+id +" was not found"));
    }

    @Override
    public List<Auction> getAuctionbyofferid(Long id) {
        return  auctionRep.findAuctionByOffer_Id(id);
    }

    @Override
    public List<Auction> getAuctions() {
        return auctionRep.findAll();
    }

    @Override
    public Auction saveAuction(DtoAuction dtoAuction) {
        Auction auction =new Auction();
        auction.setPrice( dtoAuction.getPrice() );
        auction.setOffer(offerRep.findOfferById( dtoAuction.getIdOffer() ));
        auction.setIduser(dtoAuction.getIduser() );
        return auctionRep.save(auction);
    }
}
