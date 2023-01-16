package com.jouharApp.offers.service.imp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.exception.BidNotFoundException;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.repository.AuctionRepo;
import com.jouharApp.offers.repository.OfferRepo;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuctionServiceImp.class})
@ExtendWith(SpringExtension.class)
class AuctionServiceImpTest {
    @MockBean
    private AuctionRepo auctionRepo;

    @Autowired
    private AuctionServiceImp auctionServiceImp;

    @MockBean
    private OfferRepo offerRepo;

    /**
     * Method under test: {@link AuctionServiceImp#getAuction(Long)}
     */
    @Test
    void testGetAuction() throws UnsupportedEncodingException {
        Image image = new Image();
        image.setId(123L);
        image.setImage("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setType("Type");

        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDuration(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setId(123L);
        offer.setIduser(1L);
        offer.setImage(image);
        offer.setImagename("Imagename");
        offer.setInitialprice(10.0d);
        offer.setLat(10.0d);
        offer.setLng(10.0d);
        offer.setNbChambre(1);
        offer.setStateoffer(StateOffer.notStarted);
        offer.setSurface(1L);
        offer.setTitle("Dr");

        Auction auction = new Auction();
        auction.setId(123L);
        auction.setIduser(1L);
        auction.setOffer(offer);
        auction.setPrice(10.0d);
        auction.setState(true);
        Optional<Auction> ofResult = Optional.of(auction);
        when(auctionRepo.findAuctionById((Long) any())).thenReturn(ofResult);
        assertSame(auction, auctionServiceImp.getAuction(123L));
        verify(auctionRepo).findAuctionById((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuction(Long)}
     */
    @Test
    void testGetAuction2() {
        when(auctionRepo.findAuctionById((Long) any())).thenReturn(Optional.empty());
        assertThrows(BidNotFoundException.class, () -> auctionServiceImp.getAuction(123L));
        verify(auctionRepo).findAuctionById((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuction(Long)}
     */
    @Test
    void testGetAuction3() {
        when(auctionRepo.findAuctionById((Long) any())).thenThrow(new BidNotFoundException("An error occurred"));
        assertThrows(BidNotFoundException.class, () -> auctionServiceImp.getAuction(123L));
        verify(auctionRepo).findAuctionById((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuctionbyofferid(Long)}
     */
    @Test
    void testGetAuctionbyofferid() {
        ArrayList<Auction> auctionList = new ArrayList<>();
        when(auctionRepo.findAuctionByOffer_Id((Long) any())).thenReturn(auctionList);
        List<Auction> actualAuctionbyofferid = auctionServiceImp.getAuctionbyofferid(123L);
        assertSame(auctionList, actualAuctionbyofferid);
        assertTrue(actualAuctionbyofferid.isEmpty());
        verify(auctionRepo).findAuctionByOffer_Id((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuctionbyofferid(Long)}
     */
    @Test
    void testGetAuctionbyofferid2() {
        when(auctionRepo.findAuctionByOffer_Id((Long) any())).thenThrow(new BidNotFoundException("An error occurred"));
        assertThrows(BidNotFoundException.class, () -> auctionServiceImp.getAuctionbyofferid(123L));
        verify(auctionRepo).findAuctionByOffer_Id((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuctions()}
     */
    @Test
    void testGetAuctions() {
        ArrayList<Auction> auctionList = new ArrayList<>();
        when(auctionRepo.findAll()).thenReturn(auctionList);
        List<Auction> actualAuctions = auctionServiceImp.getAuctions();
        assertSame(auctionList, actualAuctions);
        assertTrue(actualAuctions.isEmpty());
        verify(auctionRepo).findAll();
    }

    /**
     * Method under test: {@link AuctionServiceImp#getAuctions()}
     */
    @Test
    void testGetAuctions2() {
        when(auctionRepo.findAll()).thenThrow(new BidNotFoundException("An error occurred"));
        assertThrows(BidNotFoundException.class, () -> auctionServiceImp.getAuctions());
        verify(auctionRepo).findAll();
    }

    /**
     * Method under test: {@link AuctionServiceImp#saveAuction(DtoAuction)}
     */
    @Test
    void testSaveAuction() throws UnsupportedEncodingException {
        Image image = new Image();
        image.setId(123L);
        image.setImage("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setType("Type");

        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDuration(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setId(123L);
        offer.setIduser(1L);
        offer.setImage(image);
        offer.setImagename("Imagename");
        offer.setInitialprice(10.0d);
        offer.setLat(10.0d);
        offer.setLng(10.0d);
        offer.setNbChambre(1);
        offer.setStateoffer(StateOffer.notStarted);
        offer.setSurface(1L);
        offer.setTitle("Dr");

        Auction auction = new Auction();
        auction.setId(123L);
        auction.setIduser(1L);
        auction.setOffer(offer);
        auction.setPrice(10.0d);
        auction.setState(true);
        when(auctionRepo.save((Auction) any())).thenReturn(auction);

        Image image1 = new Image();
        image1.setId(123L);
        image1.setImage("AAAAAAAA".getBytes("UTF-8"));
        image1.setName("Name");
        image1.setType("Type");

        Offer offer1 = new Offer();
        offer1.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer1.setDebutAuction(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        offer1.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer1.setDuration(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        offer1.setId(123L);
        offer1.setIduser(1L);
        offer1.setImage(image1);
        offer1.setImagename("Imagename");
        offer1.setInitialprice(10.0d);
        offer1.setLat(10.0d);
        offer1.setLng(10.0d);
        offer1.setNbChambre(1);
        offer1.setStateoffer(StateOffer.notStarted);
        offer1.setSurface(1L);
        offer1.setTitle("Dr");
        when(offerRepo.findOfferById((Long) any())).thenReturn(offer1);

        DtoAuction dtoAuction = new DtoAuction();
        dtoAuction.setIdOffer(1L);
        dtoAuction.setIduser(1L);
        dtoAuction.setPrice(10.0d);
        assertSame(auction, auctionServiceImp.saveAuction(dtoAuction));
        verify(auctionRepo).save((Auction) any());
        verify(offerRepo).findOfferById((Long) any());
    }

    /**
     * Method under test: {@link AuctionServiceImp#saveAuction(DtoAuction)}
     */
    @Test
    void testSaveAuction2() throws UnsupportedEncodingException {
        Image image = new Image();
        image.setId(123L);
        image.setImage("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setType("Type");

        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDuration(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setId(123L);
        offer.setIduser(1L);
        offer.setImage(image);
        offer.setImagename("Imagename");
        offer.setInitialprice(10.0d);
        offer.setLat(10.0d);
        offer.setLng(10.0d);
        offer.setNbChambre(1);
        offer.setStateoffer(StateOffer.notStarted);
        offer.setSurface(1L);
        offer.setTitle("Dr");

        Auction auction = new Auction();
        auction.setId(123L);
        auction.setIduser(1L);
        auction.setOffer(offer);
        auction.setPrice(10.0d);
        auction.setState(true);
        when(auctionRepo.save((Auction) any())).thenReturn(auction);
        when(offerRepo.findOfferById((Long) any())).thenThrow(new BidNotFoundException("An error occurred"));

        DtoAuction dtoAuction = new DtoAuction();
        dtoAuction.setIdOffer(1L);
        dtoAuction.setIduser(1L);
        dtoAuction.setPrice(10.0d);
        assertThrows(BidNotFoundException.class, () -> auctionServiceImp.saveAuction(dtoAuction));
        verify(offerRepo).findOfferById((Long) any());
    }
}

