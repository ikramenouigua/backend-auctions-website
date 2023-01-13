package com.jouharApp.offers.service.imp;

import com.jouharApp.offers.DTO.DTOOffer;
import com.jouharApp.offers.enumeration.StateOffer;
import com.jouharApp.offers.model.Image;
import com.jouharApp.offers.model.Offer;
import com.jouharApp.offers.repository.ImageRepository;
import com.jouharApp.offers.repository.OfferRepo;
import com.jouharApp.offers.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

        return offerRep.findByStateoffer(StateOffer.available);
    }

    public List<Offer> getOffersByUser(Long id){
        return offerRep.findByIduser( id );
    }

    public List<Offer> filterOffers(Long surface,int nbChambre){
        return offerRep.findBySurfaceAndNbChambreAndStateoffer( surface,nbChambre,StateOffer.available);

    }

    @Override
    public void deleteCategory(Long id) {
        offerRep.deleteOfferById(id);
    }

    public void changeState(){
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        List<Offer> offres =offerRep.findAll();
        System.out.println(date);
        for (int i = 0; i<offres.size(); i++) {
            System.out.println(offres.get(i).getDebutAuction());
            if(offres.get(i).getDebutAuction().after(date)){

                offres.get(i).setStateoffer( StateOffer.not_started );
            }else if(offres.get(i).getDuration().before(date)){
                offres.get(i).setStateoffer( StateOffer.finished );

            }
            else{
                offres.get(i).setStateoffer( StateOffer.available );
                System.out.println("available");
            }
              offerRep.save( offres.get(i));
        }
    }
    @Override
    public Offer saveOffer(DTOOffer offer) {
        LocalDate localDate = LocalDate.now();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        System.out.println(offer);
        Offer offre1=new Offer();
        System.out.println(offer);
        offre1.setImage(  (Image)imageRepository.findByName( offer.getImagename() ));
        System.out.println(offer.getImagename());
        offre1.setAddress( offer.getAddress() );
        offre1.setImagename( offer.getImagename());
        offre1.setDuration( offer.getDuration() );
        offre1.setTitle( offer.getTitle() );
        offre1.setSurface( offer.getSurface() );
        offre1.setNbChambre( offer.getNbChambre() );
        offre1.setDescription( offer.getDescription() );
        offre1.setDebutAuction(  offer.getDebutAuction());
        offre1.setInitialprice( offer.getInitialprice() );
        offre1.setIduser( offer.getIduser() );
        System.out.println(offer.getDebutAuction());
        System.out.println(date);

        if(offer.getDebutAuction().after(date)){
            offre1.setStateoffer( StateOffer.not_started );
        }else{
            offre1.setStateoffer( StateOffer.available );
        }

        return  offerRep.save( offre1);
    }
}

