package com.jouharApp.offers.service.imp;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.enumeration.StatutAuction;
import com.jouharApp.offers.model.Auction;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.model.WinnerAuction;
import com.jouharApp.offers.repository.AuctionRepo;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.repository.WinnerAuctionRepo;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OfferServiceImp.class})
@ExtendWith(SpringExtension.class)
class OfferServiceImpTest {
    @MockBean
    private AuctionRepo auctionRepo;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private OfferRepo offerRepo;

    @Autowired
    private OfferServiceImp offerServiceImp;

    @MockBean
    private WinnerAuctionRepo winnerAuctionRepo;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OfferServiceImp#OfferServiceImp(OfferRepo)}
     *   <li>{@link OfferServiceImp#OfferServiceImp(OfferRepo)}
     * </ul>
     */
    @Test
    void testConstructor() {
        OfferServiceImp actualOfferServiceImp = new OfferServiceImp(mock(OfferRepo.class));
        actualOfferServiceImp.OfferServiceImp(mock(OfferRepo.class));
        assertTrue(actualOfferServiceImp.getOffers().isEmpty());
        assertNull(actualOfferServiceImp.imageRepository);
        assertNull(actualOfferServiceImp.auctionRep);
        assertNull(actualOfferServiceImp.WinAuctionRepository);
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffer(Long)}
     */
    @Test
    void testGetOffer() throws UnsupportedEncodingException {
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
        when(offerRepo.findOfferById((Long) any())).thenReturn(offer);
        assertSame(offer, offerServiceImp.getOffer(123L));
        verify(offerRepo).findOfferById((Long) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffers()}
     */
    @Test
    void testGetOffers() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findByStateoffer((StateOffer) any())).thenReturn(offerList);
        List<Offer> actualOffers = offerServiceImp.getOffers();
        assertSame(offerList, actualOffers);
        assertTrue(actualOffers.isEmpty());
        verify(offerRepo).findByStateoffer((StateOffer) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffersByUserAvailable(Long)}
     */
    @Test
    void testGetOffersByUserAvailable() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findByIduserAndStateoffer((Long) any(), (StateOffer) any())).thenReturn(offerList);
        List<Offer> actualOffersByUserAvailable = offerServiceImp.getOffersByUserAvailable(123L);
        assertSame(offerList, actualOffersByUserAvailable);
        assertTrue(actualOffersByUserAvailable.isEmpty());
        verify(offerRepo).findByIduserAndStateoffer((Long) any(), (StateOffer) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffersByUserFinished(Long)}
     */
    @Test
    void testGetOffersByUserFinished() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findByIduserAndStateoffer((Long) any(), (StateOffer) any())).thenReturn(offerList);
        List<Offer> actualOffersByUserFinished = offerServiceImp.getOffersByUserFinished(123L);
        assertSame(offerList, actualOffersByUserFinished);
        assertTrue(actualOffersByUserFinished.isEmpty());
        verify(offerRepo).findByIduserAndStateoffer((Long) any(), (StateOffer) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffersByUserNotStarted(Long)}
     */
    @Test
    void testGetOffersByUserNotStarted() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findByIduserAndStateoffer((Long) any(), (StateOffer) any())).thenReturn(offerList);
        List<Offer> actualOffersByUserNotStarted = offerServiceImp.getOffersByUserNotStarted(123L);
        assertSame(offerList, actualOffersByUserNotStarted);
        assertTrue(actualOffersByUserNotStarted.isEmpty());
        verify(offerRepo).findByIduserAndStateoffer((Long) any(), (StateOffer) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffersByUser(Long)}
     */
    @Test
    void testGetOffersByUser() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findByIduser((Long) any())).thenReturn(offerList);
        List<Offer> actualOffersByUser = offerServiceImp.getOffersByUser(123L);
        assertSame(offerList, actualOffersByUser);
        assertTrue(actualOffersByUser.isEmpty());
        verify(offerRepo).findByIduser((Long) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#filterOffers(Long, int)}
     */
    @Test
    void testFilterOffers() {
        ArrayList<Offer> offerList = new ArrayList<>();
        when(offerRepo.findBySurfaceAndNbChambreAndStateoffer((Long) any(), anyInt(), (StateOffer) any()))
                .thenReturn(offerList);
        List<Offer> actualFilterOffersResult = offerServiceImp.filterOffers(1L, 1);
        assertSame(offerList, actualFilterOffersResult);
        assertTrue(actualFilterOffersResult.isEmpty());
        verify(offerRepo).findBySurfaceAndNbChambreAndStateoffer((Long) any(), anyInt(), (StateOffer) any());
    }

    /**
     * Method under test: {@link OfferServiceImp#deleteCategory(Long)}
     */
    @Test
    void testDeleteCategory() {
        doNothing().when(offerRepo).deleteOfferById((Long) any());
        offerServiceImp.deleteCategory(123L);
        verify(offerRepo).deleteOfferById((Long) any());
        assertTrue(offerServiceImp.getOffers().isEmpty());
    }

    /**
     * Method under test: {@link OfferServiceImp#changeState()}
     */
    @Test
    void testChangeState() {
        when(offerRepo.findAll()).thenReturn(new ArrayList<>());
        offerServiceImp.changeState();
        verify(offerRepo).findAll();
        assertTrue(offerServiceImp.getOffers().isEmpty());
    }

    /**
     * Method under test: {@link OfferServiceImp#changeState()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testChangeState2() throws UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        //       at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //       at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //       at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //       at java.util.Objects.checkIndex(Objects.java:359)
        //       at java.util.ArrayList.get(ArrayList.java:427)
        //       at com.jouharApp.offers.service.imp.OfferServiceImp.changeState(OfferServiceImp.java:107)
        //   In order to prevent changeState()
        //   from throwing IndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   changeState().
        //   See https://diff.blue/R013 to resolve this issue.

        when(auctionRepo.findAuctionByOffer_Id((Long) any())).thenReturn(new ArrayList<>());

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

        ArrayList<Offer> offerList = new ArrayList<>();
        offerList.add(offer);
        when(offerRepo.findAll()).thenReturn(offerList);
        offerServiceImp.changeState();
    }

    /**
     * Method under test: {@link OfferServiceImp#changeState()}
     */
    @Test
    void testChangeState3() throws UnsupportedEncodingException {
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

        WinnerAuction winnerAuction = new WinnerAuction();
        winnerAuction.setId(123L);
        winnerAuction.setIdseller(1L);
        winnerAuction.setIduser(1L);
        winnerAuction.setOffer(offer);
        winnerAuction.setPriceWin(10.0d);
        winnerAuction.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.save((WinnerAuction) any())).thenReturn(winnerAuction);

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

        Auction auction = new Auction();
        auction.setId(123L);
        auction.setIduser(1L);
        auction.setOffer(offer1);
        auction.setPrice(10.0d);
        auction.setState(true);

        ArrayList<Auction> auctionList = new ArrayList<>();
        auctionList.add(auction);
        when(auctionRepo.findAuctionByOffer_Id((Long) any())).thenReturn(auctionList);

        Image image2 = new Image();
        image2.setId(123L);
        image2.setImage("AAAAAAAA".getBytes("UTF-8"));
        image2.setName("Name");
        image2.setType("Type");

        Offer offer2 = new Offer();
        offer2.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer2.setDebutAuction(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        offer2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer2.setDuration(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        offer2.setId(123L);
        offer2.setIduser(1L);
        offer2.setImage(image2);
        offer2.setImagename("Imagename");
        offer2.setInitialprice(10.0d);
        offer2.setLat(10.0d);
        offer2.setLng(10.0d);
        offer2.setNbChambre(1);
        offer2.setStateoffer(StateOffer.notStarted);
        offer2.setSurface(1L);
        offer2.setTitle("Dr");

        ArrayList<Offer> offerList = new ArrayList<>();
        offerList.add(offer2);

        Image image3 = new Image();
        image3.setId(123L);
        image3.setImage("AAAAAAAA".getBytes("UTF-8"));
        image3.setName("Name");
        image3.setType("Type");

        Offer offer3 = new Offer();
        offer3.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer3.setDebutAuction(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        offer3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer3.setDuration(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        offer3.setId(123L);
        offer3.setIduser(1L);
        offer3.setImage(image3);
        offer3.setImagename("Imagename");
        offer3.setInitialprice(10.0d);
        offer3.setLat(10.0d);
        offer3.setLng(10.0d);
        offer3.setNbChambre(1);
        offer3.setStateoffer(StateOffer.notStarted);
        offer3.setSurface(1L);
        offer3.setTitle("Dr");
        when(offerRepo.save((Offer) any())).thenReturn(offer3);
        when(offerRepo.findAll()).thenReturn(offerList);
        offerServiceImp.changeState();
        verify(offerRepo).save((Offer) any());
        verify(offerRepo).findAll();
    }

    /**
     * Method under test: {@link OfferServiceImp#changeState()}
     */
    @Test
    void testChangeState4() throws UnsupportedEncodingException {
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

        WinnerAuction winnerAuction = new WinnerAuction();
        winnerAuction.setId(123L);
        winnerAuction.setIdseller(1L);
        winnerAuction.setIduser(1L);
        winnerAuction.setOffer(offer);
        winnerAuction.setPriceWin(10.0d);
        winnerAuction.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.save((WinnerAuction) any())).thenReturn(winnerAuction);

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

        Image image2 = new Image();
        image2.setId(123L);
        image2.setImage("AAAAAAAA".getBytes("UTF-8"));
        image2.setName("Name");
        image2.setType("Type");

        Offer offer2 = new Offer();
        offer2.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer2.setDebutAuction(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        offer2.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer2.setDuration(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        offer2.setId(123L);
        offer2.setIduser(1L);
        offer2.setImage(image2);
        offer2.setImagename("Imagename");
        offer2.setInitialprice(10.0d);
        offer2.setLat(10.0d);
        offer2.setLng(10.0d);
        offer2.setNbChambre(1);
        offer2.setStateoffer(StateOffer.notStarted);
        offer2.setSurface(1L);
        offer2.setTitle("Dr");
        Auction auction = mock(Auction.class);
        when(auction.getOffer()).thenReturn(offer2);
        when(auction.getPrice()).thenReturn(10.0d);
        when(auction.getIduser()).thenReturn(1L);
        doNothing().when(auction).setId((Long) any());
        doNothing().when(auction).setIduser((Long) any());
        doNothing().when(auction).setOffer((Offer) any());
        doNothing().when(auction).setPrice((Double) any());
        doNothing().when(auction).setState(anyBoolean());
        auction.setId(123L);
        auction.setIduser(1L);
        auction.setOffer(offer1);
        auction.setPrice(10.0d);
        auction.setState(true);

        ArrayList<Auction> auctionList = new ArrayList<>();
        auctionList.add(auction);
        when(auctionRepo.findAuctionByOffer_Id((Long) any())).thenReturn(auctionList);

        Image image3 = new Image();
        image3.setId(123L);
        image3.setImage("AAAAAAAA".getBytes("UTF-8"));
        image3.setName("Name");
        image3.setType("Type");

        Offer offer3 = new Offer();
        offer3.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer3.setDebutAuction(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        offer3.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer3.setDuration(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        offer3.setId(123L);
        offer3.setIduser(1L);
        offer3.setImage(image3);
        offer3.setImagename("Imagename");
        offer3.setInitialprice(10.0d);
        offer3.setLat(10.0d);
        offer3.setLng(10.0d);
        offer3.setNbChambre(1);
        offer3.setStateoffer(StateOffer.notStarted);
        offer3.setSurface(1L);
        offer3.setTitle("Dr");

        ArrayList<Offer> offerList = new ArrayList<>();
        offerList.add(offer3);

        Image image4 = new Image();
        image4.setId(123L);
        image4.setImage("AAAAAAAA".getBytes("UTF-8"));
        image4.setName("Name");
        image4.setType("Type");

        Offer offer4 = new Offer();
        offer4.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer4.setDebutAuction(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        offer4.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer4.setDuration(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        offer4.setId(123L);
        offer4.setIduser(1L);
        offer4.setImage(image4);
        offer4.setImagename("Imagename");
        offer4.setInitialprice(10.0d);
        offer4.setLat(10.0d);
        offer4.setLng(10.0d);
        offer4.setNbChambre(1);
        offer4.setStateoffer(StateOffer.notStarted);
        offer4.setSurface(1L);
        offer4.setTitle("Dr");
        when(offerRepo.save((Offer) any())).thenReturn(offer4);
        when(offerRepo.findAll()).thenReturn(offerList);
        offerServiceImp.changeState();
        verify(auction).setId((Long) any());
        verify(auction).setIduser((Long) any());
        verify(auction).setOffer((Offer) any());
        verify(auction).setPrice((Double) any());
        verify(auction).setState(anyBoolean());
        verify(offerRepo).save((Offer) any());
        verify(offerRepo).findAll();
    }
}

