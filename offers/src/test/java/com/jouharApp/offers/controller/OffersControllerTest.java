package com.jouharApp.offers.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.service.OfferService;

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

@ContextConfiguration(classes = {OffersController.class})
@ExtendWith(SpringExtension.class)
class OffersControllerTest {
    @MockBean
    private OfferService offerService;

    @Autowired
    private OffersController offersController;

    /**
     * Method under test: {@link OffersController#addOffer(Offer)}
     */
    @Test
    void testAddOffer() throws Exception {
        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        offer.setDuration(1);
        offer.setId(123L);
        offer.setInitialprice(10.0d);
        offer.setTitle("Dr");
        when(offerService.saveOffer((Offer) any())).thenReturn(offer);

        Offer offer1 = new Offer();
        offer1.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer1.setDebutAuction(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        offer1.setDescription("The characteristics of someone or something");
        offer1.setDuration(1);
        offer1.setId(123L);
        offer1.setInitialprice(10.0d);
        offer1.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(offer1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/offers/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"address\":\"42 Main"
                                        + " St\",\"initialprice\":10.0,\"debutAuction\":0,\"duration\":1}"));
    }

    /**
     * Method under test: {@link OffersController#findOffer(Long)}
     */
    @Test
    void testFindOffer() throws Exception {
        Offer offer = new Offer();
        offer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        offer.setDebutAuction(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        offer.setDescription("The characteristics of someone or something");
        offer.setDuration(1);
        offer.setId(123L);
        offer.setInitialprice(10.0d);
        offer.setTitle("Dr");
        when(offerService.getOffer((Long) any())).thenReturn(offer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/find/{id}", 123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"address\":\"42 Main"
                                        + " St\",\"initialprice\":10.0,\"debutAuction\":0,\"duration\":1}"));
    }

    /**
     * Method under test: {@link OffersController#getAllOffers()}
     */
    @Test
    void testGetAllOffers() throws Exception {
        when(offerService.getOffers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/allOffers");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getAllOffers()}
     */
    @Test
    void testGetAllOffers2() throws Exception {
        when(offerService.getOffers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/allOffers");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

