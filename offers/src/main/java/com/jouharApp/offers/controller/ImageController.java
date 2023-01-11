package com.jouharApp.offers.controller;


import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.service.imp.ImageService;
import com.jouharApp.offers.util.ImageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.Deflater;

import static com.jouharApp.offers.util.ImageUtil.compressImage;

@Slf4j
@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService productImageService;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    private OfferRepo offerRep;



    @ResponseStatus(value = HttpStatus.OK)
//    @PostMapping("/upload")
//    public void uploadImage(@RequestParam("productImage")MultipartFile file,@RequestParam("offer_id")String offer_id) throws IOException{
//        productImageService.uploadImage(file,Long.parseLong(offer_id));
//    }
    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file )

            throws IOException {
        Long x= Long.valueOf( 15 );
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())

                .type(file.getContentType())
                .image(compressImage(file.getBytes())).build()
        );
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public Image getImageDetails(@PathVariable("name") String name) throws IOException {



            final Image dbImage = imageRepository.findByName(name);



        return Image.builder()
                .name(dbImage.getName())
                .type(dbImage.getType())
                .image( ImageUtil.decompressImage(dbImage.getImage())).build();
    }
    //    @GetMapping("/download/{idOffre}")
//    public ResponseEntity<List<Image>> downloadImage(@PathVariable Long idOffre) {
//        System.out.println(idOffre);
//        List<Image> tab;
//        tab = productImageService.downloadImage(Long.parseLong( String.valueOf( idOffre ) ));
//       // return ResponseEntity.status(HttpStatus.OK).contentType( MediaType.valueOf("image/png")).body(image);
//        return new ResponseEntity<>(tab, HttpStatus.OK);
//    }
    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }



}