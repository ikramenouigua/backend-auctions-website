package com.jouharApp.offers.service.imp;

import com.jouharApp.offers.DTO.DTOOffer;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImp implements OfferService {
    OfferRepo offerRep ;

    @Autowired
    ImageRepository imageRepository;

    public OfferServiceImp(OfferRepo offerRep) {
        this.offerRep = offerRep;
    }

    @Autowired
    public void OfferServiceImp(OfferRepo offerRep){
        this.offerRep = offerRep ;
    }

    @Override
    public Offer getOffer(Long id) {
        return  offerRep.findOfferById(id);

    }

    @Override
    public List<Offer> getOffers() {
        return offerRep.findAll();
    }

    public List<Offer> getOffersByUser(Long id){
        return offerRep.findByIduser( id );
    }

    @Override
    public void deleteCategory(Long id) {
        offerRep.deleteOfferById(id);
    }
    @Override
    public Offer saveOffer(DTOOffer offer) {
        Offer offre1=new Offer();
        System.out.println(offer);
        offre1.setImage(  (Image)imageRepository.findByName( offer.getImagename() ));
        System.out.println(offer.getImagename());
        offre1.setAddress( offer.getAddress() );
        offre1.setImagename( offer.getImagename());
        offre1.setDuration( offer.getDuration() );
        offre1.setTitle( offer.getTitle() );
        offre1.setDescription( offer.getDescription() );
        offre1.setDebutAuction(  offer.getDebutAuction());
        offre1.setInitialprice( offer.getInitialprice() );
        offre1.setIduser( offer.getIduser() );

        return  offerRep.save( offre1);
    }
}

