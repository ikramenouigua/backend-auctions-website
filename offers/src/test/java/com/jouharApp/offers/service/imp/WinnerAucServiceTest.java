package com.jouharApp.offers.service.imp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.enumeration.StatutAuction;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.model.WinnerAuction;
import com.jouharApp.offers.repository.AuctionRepo;
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

@ContextConfiguration(classes = {WinnerAucService.class})
@ExtendWith(SpringExtension.class)
class WinnerAucServiceTest {
    @MockBean
    private AuctionRepo auctionRepo;

    @Autowired
    private WinnerAucService winnerAucService;

    @MockBean
    private WinnerAuctionRepo winnerAuctionRepo;

    /**
     * Method under test: {@link WinnerAucService#saveWinAuction(WinnerAuction)}
     */
    @Test
    void testSaveWinAuction() throws UnsupportedEncodingException {
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

        WinnerAuction winnerAuction1 = new WinnerAuction();
        winnerAuction1.setId(123L);
        winnerAuction1.setIdseller(1L);
        winnerAuction1.setIduser(1L);
        winnerAuction1.setOffer(offer1);
        winnerAuction1.setPriceWin(10.0d);
        winnerAuction1.setStatus(StatutAuction.accepter);
        assertSame(winnerAuction, winnerAucService.saveWinAuction(winnerAuction1));
        verify(winnerAuctionRepo).save((WinnerAuction) any());
    }

    /**
     * Method under test: {@link WinnerAucService#getWinnerAuctionBuuser(Long)}
     */
    @Test
    void testGetWinnerAuctionBuuser() {
        ArrayList<WinnerAuction> winnerAuctionList = new ArrayList<>();
        when(winnerAuctionRepo.findWinnerAuctionByIduser((Long) any())).thenReturn(winnerAuctionList);
        List<WinnerAuction> actualWinnerAuctionBuuser = winnerAucService.getWinnerAuctionBuuser(123L);
        assertSame(winnerAuctionList, actualWinnerAuctionBuuser);
        assertTrue(actualWinnerAuctionBuuser.isEmpty());
        verify(winnerAuctionRepo).findWinnerAuctionByIduser((Long) any());
    }

    /**
     * Method under test: {@link WinnerAucService#getWinnerAuctiionbyseller(Long)}
     */
    @Test
    void testGetWinnerAuctiionbyseller() {
        ArrayList<WinnerAuction> winnerAuctionList = new ArrayList<>();
        when(winnerAuctionRepo.findWinnerAuctionByIdsellerAndStatus((Long) any(), (StatutAuction) any()))
                .thenReturn(winnerAuctionList);
        List<WinnerAuction> actualWinnerAuctiionbyseller = winnerAucService.getWinnerAuctiionbyseller(123L);
        assertSame(winnerAuctionList, actualWinnerAuctiionbyseller);
        assertTrue(actualWinnerAuctiionbyseller.isEmpty());
        verify(winnerAuctionRepo).findWinnerAuctionByIdsellerAndStatus((Long) any(), (StatutAuction) any());
    }

    /**
     * Method under test: {@link WinnerAucService#accepterOffre(Long)}
     */
    @Test
    void testAccepterOffre() throws UnsupportedEncodingException {
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

        WinnerAuction winnerAuction1 = new WinnerAuction();
        winnerAuction1.setId(123L);
        winnerAuction1.setIdseller(1L);
        winnerAuction1.setIduser(1L);
        winnerAuction1.setOffer(offer1);
        winnerAuction1.setPriceWin(10.0d);
        winnerAuction1.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.save((WinnerAuction) any())).thenReturn(winnerAuction1);
        when(winnerAuctionRepo.findWinnerAuctionById((Long) any())).thenReturn(winnerAuction);
        winnerAucService.accepterOffre(123L);
        verify(winnerAuctionRepo).findWinnerAuctionById((Long) any());
        verify(winnerAuctionRepo).save((WinnerAuction) any());
    }

    /**
     * Method under test: {@link WinnerAucService#accepterOffre(Long)}
     */
    @Test
    void testAccepterOffre2() throws UnsupportedEncodingException {
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
        WinnerAuction winnerAuction = mock(WinnerAuction.class);
        doNothing().when(winnerAuction).setId((Long) any());
        doNothing().when(winnerAuction).setIdseller((Long) any());
        doNothing().when(winnerAuction).setIduser((Long) any());
        doNothing().when(winnerAuction).setOffer((Offer) any());
        doNothing().when(winnerAuction).setPriceWin((Double) any());
        doNothing().when(winnerAuction).setStatus((StatutAuction) any());
        winnerAuction.setId(123L);
        winnerAuction.setIdseller(1L);
        winnerAuction.setIduser(1L);
        winnerAuction.setOffer(offer);
        winnerAuction.setPriceWin(10.0d);
        winnerAuction.setStatus(StatutAuction.accepter);

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

        WinnerAuction winnerAuction1 = new WinnerAuction();
        winnerAuction1.setId(123L);
        winnerAuction1.setIdseller(1L);
        winnerAuction1.setIduser(1L);
        winnerAuction1.setOffer(offer1);
        winnerAuction1.setPriceWin(10.0d);
        winnerAuction1.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.save((WinnerAuction) any())).thenReturn(winnerAuction1);
        when(winnerAuctionRepo.findWinnerAuctionById((Long) any())).thenReturn(winnerAuction);
        winnerAucService.accepterOffre(123L);
        verify(winnerAuctionRepo).findWinnerAuctionById((Long) any());
        verify(winnerAuctionRepo).save((WinnerAuction) any());
        verify(winnerAuction).setId((Long) any());
        verify(winnerAuction).setIdseller((Long) any());
        verify(winnerAuction).setIduser((Long) any());
        verify(winnerAuction).setOffer((Offer) any());
        verify(winnerAuction).setPriceWin((Double) any());
        verify(winnerAuction, atLeast(1)).setStatus((StatutAuction) any());
    }

    /**
     * Method under test: {@link WinnerAucService#refuserOffre(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefuserOffre() throws UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 0
        //       at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //       at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //       at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //       at java.util.Objects.checkIndex(Objects.java:359)
        //       at java.util.ArrayList.get(ArrayList.java:427)
        //       at com.jouharApp.offers.service.imp.WinnerAucService.refuserOffre(WinnerAucService.java:51)
        //   In order to prevent refuserOffre(Long)
        //   from throwing IndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   refuserOffre(Long).
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

        WinnerAuction winnerAuction = new WinnerAuction();
        winnerAuction.setId(123L);
        winnerAuction.setIdseller(1L);
        winnerAuction.setIduser(1L);
        winnerAuction.setOffer(offer);
        winnerAuction.setPriceWin(10.0d);
        winnerAuction.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.findWinnerAuctionById((Long) any())).thenReturn(winnerAuction);
        winnerAucService.refuserOffre(123L);
    }

    /**
     * Method under test: {@link WinnerAucService#refuserOffre(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRefuserOffre2() throws UnsupportedEncodingException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 0
        //       at jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        //       at jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        //       at jdk.internal.util.Preconditions.checkIndex(Preconditions.java:266)
        //       at java.util.Objects.checkIndex(Objects.java:359)
        //       at java.util.ArrayList.get(ArrayList.java:427)
        //       at com.jouharApp.offers.service.imp.WinnerAucService.refuserOffre(WinnerAucService.java:51)
        //   In order to prevent refuserOffre(Long)
        //   from throwing IndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   refuserOffre(Long).
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
        WinnerAuction winnerAuction = mock(WinnerAuction.class);
        when(winnerAuction.getOffer()).thenReturn(offer1);
        doNothing().when(winnerAuction).setId((Long) any());
        doNothing().when(winnerAuction).setIdseller((Long) any());
        doNothing().when(winnerAuction).setIduser((Long) any());
        doNothing().when(winnerAuction).setOffer((Offer) any());
        doNothing().when(winnerAuction).setPriceWin((Double) any());
        doNothing().when(winnerAuction).setStatus((StatutAuction) any());
        winnerAuction.setId(123L);
        winnerAuction.setIdseller(1L);
        winnerAuction.setIduser(1L);
        winnerAuction.setOffer(offer);
        winnerAuction.setPriceWin(10.0d);
        winnerAuction.setStatus(StatutAuction.accepter);
        when(winnerAuctionRepo.findWinnerAuctionById((Long) any())).thenReturn(winnerAuction);
        winnerAucService.refuserOffre(123L);
    }
}

