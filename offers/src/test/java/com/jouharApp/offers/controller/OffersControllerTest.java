package com.jouharApp.offers.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jouharApp.offers.DTO.DTOOffer;
import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.model.Image;
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
     * Method under test: {@link OffersController#getOffersByUserAvailable(Long)}
     */
    @Test
    void testGetOffersByUserAvailable() throws Exception {
        when(offerService.getOffersByUserAvailable((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/getOffersByUseravailable/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUserAvailable(Long)}
     */
    @Test
    void testGetOffersByUserAvailable2() throws Exception {
        when(offerService.getOffersByUserAvailable((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/getOffersByUseravailable/{id}",
                123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUserFinished(Long)}
     */
    @Test
    void testGetOffersByUserFinished() throws Exception {
        when(offerService.getOffersByUserFinished((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/getOffersByUserfinished/{id}",
                123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUserFinished(Long)}
     */
    @Test
    void testGetOffersByUserFinished2() throws Exception {
        when(offerService.getOffersByUserFinished((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/getOffersByUserfinished/{id}",
                123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUserNotStarted(Long)}
     */
    @Test
    void testGetOffersByUserNotStarted() throws Exception {
        when(offerService.getOffersByUserNotStarted((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/offers/getOffersByUsernotstarted/{id}", 123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUserNotStarted(Long)}
     */
    @Test
    void testGetOffersByUserNotStarted2() throws Exception {
        when(offerService.getOffersByUserNotStarted((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/getOffersByUsernotstarted/{id}",
                123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#addOffer(DTOOffer)}
     */
    @Test
    void testAddOffer() throws Exception {
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
        when(offerService.saveOffer((DTOOffer) any())).thenReturn(offer);

        DTOOffer dtoOffer = new DTOOffer();
        dtoOffer.setAddress("42 Main St");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dtoOffer.setDebutAuction(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        dtoOffer.setDescription("The characteristics of someone or something");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        dtoOffer.setDuration(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        dtoOffer.setIduser(1L);
        dtoOffer.setImagename("Imagename");
        dtoOffer.setInitialprice(10.0d);
        dtoOffer.setLat(10.0d);
        dtoOffer.setLng(10.0d);
        dtoOffer.setNbChambre(1);
        dtoOffer.setSurface(1L);
        dtoOffer.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(dtoOffer);
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
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"iduser\":1,\"surface"
                                        + "\":1,\"nbChambre\":1,\"lat\":10.0,\"lng\":10.0,\"address\":\"42 Main St\",\"duration\":0,\"initialprice\":10.0,"
                                        + "\"debutAuction\":0,\"imagename\":\"Imagename\",\"stateoffer\":\"notStarted\",\"image\":{\"id\":123,\"name\":\"Name\","
                                        + "\"type\":\"Type\",\"image\":\"QUFBQUFBQUE=\"}}"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUser(Long)}
     */
    @Test
    void testGetOffersByUser() throws Exception {
        when(offerService.getOffersByUser((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/getOffersByUser/{id}", 123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#getOffersByUser(Long)}
     */
    @Test
    void testGetOffersByUser2() throws Exception {
        when(offerService.getOffersByUser((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/getOffersByUser/{id}", 123L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#FilterOffers(Long, int)}
     */
    @Test
    void testFilterOffers() throws Exception {
        when(offerService.filterOffers((Long) any(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/offers/filterOffers/{surface}/{nbChambre}", 1L, 1);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#FilterOffers(Long, int)}
     */
    @Test
    void testFilterOffers2() throws Exception {
        when(offerService.filterOffers((Long) any(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/offers/filterOffers/{surface}/{nbChambre}",
                1L, 1);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OffersController#findOffer(Long)}
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
        when(offerService.getOffer((Long) any())).thenReturn(offer);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/offers/find/{id}", 123L);
        MockMvcBuilders.standaloneSetup(offersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"title\":\"Dr\",\"description\":\"The characteristics of someone or something\",\"iduser\":1,\"surface"
                                        + "\":1,\"nbChambre\":1,\"lat\":10.0,\"lng\":10.0,\"address\":\"42 Main St\",\"duration\":0,\"initialprice\":10.0,"
                                        + "\"debutAuction\":0,\"imagename\":\"Imagename\",\"stateoffer\":\"notStarted\",\"image\":{\"id\":123,\"name\":\"Name\","
                                        + "\"type\":\"Type\",\"image\":\"QUFBQUFBQUE=\"}}"));
    }

    /**
     * Method under test: {@link OffersController#getAllOffers()}
     */
    @Test
    void testGetAllOffers() throws Exception {
        when(offerService.getOffers()).thenReturn(new ArrayList<>());
        doNothing().when(offerService).changeState();
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
        doNothing().when(offerService).changeState();
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

