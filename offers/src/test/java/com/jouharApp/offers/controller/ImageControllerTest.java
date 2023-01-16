package com.jouharApp.offers.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.service.imp.ImageService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ImageController.class})
@ExtendWith(SpringExtension.class)
class ImageControllerTest {
    @Autowired
    private ImageController imageController;

    @MockBean
    private ImageRepository imageRepository;

    @MockBean
    private ImageService imageService;

    @MockBean
    private OfferRepo offerRepo;

    /**
     * Method under test: {@link ImageController#getImageDetails(String)}
     */
    @Test
    void testGetImageDetails() throws Exception {
        Image image = new Image();
        image.setId(123L);
        image.setImage("AAAAAAAA".getBytes("UTF-8"));
        image.setName("Name");
        image.setType("Type");
        when(imageRepository.findByName((String) any())).thenReturn(image);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/images/get/image/info/{name}", "Name");
        MockMvcBuilders.standaloneSetup(imageController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":null,\"name\":\"Name\",\"type\":\"Type\",\"image\":\"\"}"));
    }

    /**
     * Method under test: {@link ImageController#uplaodImage(MultipartFile)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUplaodImage() throws IOException {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.jouharApp.offers.repository.ImageRepository.save(Object)" because "this.imageRepository" is null
        //       at com.jouharApp.offers.controller.ImageController.uplaodImage(ImageController.java:47)
        //   In order to prevent uplaodImage(MultipartFile)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   uplaodImage(MultipartFile).
        //   See https://diff.blue/R013 to resolve this issue.

        ImageController imageController = new ImageController();
        imageController
                .uplaodImage(new MockMultipartFile("Name", new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8"))));
    }

    /**
     * Method under test: {@link ImageController#compressBytes(byte[])}
     */
    @Test
    void testCompressBytes() throws UnsupportedEncodingException {
        assertEquals(11, ImageController.compressBytes("AAAAAAAA".getBytes("UTF-8")).length);
    }
}

