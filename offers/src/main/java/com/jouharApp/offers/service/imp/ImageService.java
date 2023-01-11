package com.jouharApp.offers.service.imp;

import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepo;
    @Autowired
    private  OfferRepo offerRep;

//    public Image uploadImage(MultipartFile file,Long offer_id) throws IOException {
//        System.out.println(offer_id);
//        Image pImage = new Image();
//        pImage.setName(file.getOriginalFilename());
//        pImage.setType(file.getContentType());
//        pImage.setImageData( ImageUtil.compressImage(file.getBytes()));
//        pImage.setOffer(offerRep.findOfferById( offer_id ));
//        return imageRepo.save(pImage);
//    }
//
//    public List<Image> downloadImage(Long offer_id){
//        List<Image> imageData = imageRepo.findByOfferId(  offer_id  );
//        System.out.println(imageData);
//
//        //return ImageUtil.decompressImage(imageData.get().getImageData());
//        return imageData;
//    }
}
