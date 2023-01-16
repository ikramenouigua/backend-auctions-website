package com.jouharApp.offers.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jouharApp.offers.DTO.DtoAuction;
import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.service.AuctionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuctionController.class})
@ExtendWith(SpringExtension.class)
class AuctionControllerTest {
    @Autowired
    private AuctionController auctionController;

    @MockBean
    private AuctionService auctionService;

    /**
     * Method under test: {@link AuctionController#addBid(DtoAuction)}
     */
    @Test
    void testAddBid() throws Exception {
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
        when(auctionService.saveAuction((DtoAuction) any())).thenReturn(auction);

        DtoAuction dtoAuction = new DtoAuction();
        dtoAuction.setIdOffer(1L);
        dtoAuction.setIduser(1L);
        dtoAuction.setPrice(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(dtoAuction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auctions/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(auctionController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"price\":10.0,\"iduser\":1,\"state\":true,\"offer\":{\"id\":123,\"title\":\"Dr\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"iduser\":1,\"surface\":1,\"nbChambre\":1,\"lat\":10.0,\"lng\":10.0"
                                        + ",\"address\":\"42 Main St\",\"duration\":0,\"initialprice\":10.0,\"debutAuction\":0,\"imagename\":\"Imagename\","
                                        + "\"stateoffer\":\"notStarted\",\"image\":{\"id\":123,\"name\":\"Name\",\"type\":\"Type\",\"image\":\"QUFBQUFBQUE=\"}}}"));
    }

    /**
     * Method under test: {@link AuctionController#findOffer(Long)}
     */
    @Test
    void testFindOffer() throws Exception {
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
        when(auctionService.getAuction((Long) any())).thenReturn(auction);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctions/find/{id}", 123L);
        MockMvcBuilders.standaloneSetup(auctionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"price\":10.0,\"iduser\":1,\"state\":true,\"offer\":{\"id\":123,\"title\":\"Dr\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"iduser\":1,\"surface\":1,\"nbChambre\":1,\"lat\":10.0,\"lng\":10.0"
                                        + ",\"address\":\"42 Main St\",\"duration\":0,\"initialprice\":10.0,\"debutAuction\":0,\"imagename\":\"Imagename\","
                                        + "\"stateoffer\":\"notStarted\",\"image\":{\"id\":123,\"name\":\"Name\",\"type\":\"Type\",\"image\":\"QUFBQUFBQUE=\"}}}"));
    }

    /**
     * Method under test: {@link AuctionController#findOfferByOfferId(Long)}
     */
    @Test
    void testFindOfferByOfferId() throws Exception {
        when(auctionService.getAuctionbyofferid((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctions/findbyoffer/{id}", 123L);
        MockMvcBuilders.standaloneSetup(auctionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AuctionController#findOfferByOfferId(Long)}
     */
    @Test
    void testFindOfferByOfferId2() throws Exception {
        when(auctionService.getAuctionbyofferid((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/auctions/findbyoffer/{id}", 123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(auctionController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AuctionController#findWinnerOffer(Long)}
     */
    @Test
    void testFindWinnerOffer() throws Exception {
        Image image = new Image();
        image.setId(123L);
        image.setImage("AAAAAAAA".getBytes("UTF-8"));
        image.setName("?");
        image.setType("?");

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
        offer.setImagename("?");
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

        ArrayList<Auction> auctionList = new ArrayList<>();
        auctionList.add(auction);
        when(auctionService.getAuctionbyofferid((Long) any())).thenReturn(auctionList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auctions/findWinner/{id}", 123L);
        MockMvcBuilders.standaloneSetup(auctionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"price\":10.0,\"iduser\":1,\"state\":true,\"offer\":{\"id\":123,\"title\":\"Dr\",\"description\":\"The"
                                        + " characteristics of someone or something\",\"iduser\":1,\"surface\":1,\"nbChambre\":1,\"lat\":10.0,\"lng\":10.0"
                                        + ",\"address\":\"42 Main St\",\"duration\":0,\"initialprice\":10.0,\"debutAuction\":0,\"imagename\":\"?\",\"stateoffer"
                                        + "\":\"notStarted\",\"image\":{\"id\":123,\"name\":\"?\",\"type\":\"?\",\"image\":\"QUFBQUFBQUE=\"}}}"));
    }
}

