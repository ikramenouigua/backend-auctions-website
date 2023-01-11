package com.jouharApp.offers.service.Imp;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.repository.OfferRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jouharApp.offers.service.imp.OfferServiceImp;
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
    private OfferRepo offerRepo;

    @Autowired
    private OfferServiceImp offerServiceImp;

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
    }

    /**
     * Method under test: {@link OfferServiceImp#getOffer(Long)}
     */
    @Test
    void testGetOffer() {
        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        offer.setDuration(1);
        offer.setId(123L);
        offer.setInitialprice(10.0d);
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
        when(offerRepo.findAll()).thenReturn(offerList);
        List<Offer> actualOffers = offerServiceImp.getOffers();
        assertSame(offerList, actualOffers);
        assertTrue(actualOffers.isEmpty());
        verify(offerRepo).findAll();
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
     * Method under test: {@link OfferServiceImp#saveOffer(Offer)}
     */
    @Test
    void testSaveOffer() {
        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        offer.setDuration(1);
        offer.setId(123L);
        offer.setInitialprice(10.0d);
        offer.setTitle("Dr");
        when(offerRepo.save((Offer) any())).thenReturn(offer);

        Offer offer1 = new Offer();
        offer1.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer1.setDebutAuction(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        offer1.setDescription("The characteristics of someone or something");
        offer1.setDuration(1);
        offer1.setId(123L);
        offer1.setInitialprice(10.0d);
        offer1.setTitle("Dr");
        assertSame(offer, offerServiceImp.saveOffer(offer1));
        verify(offerRepo).save((Offer) any());
    }
}

