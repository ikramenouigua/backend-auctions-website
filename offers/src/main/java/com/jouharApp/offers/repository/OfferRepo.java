package com.jouharApp.offers.repository;


import com.jouharApp.offers.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
    Offer findOfferById(Long id);

    void deleteOfferById(Long id);

    List<Offer> findByIduser(Long id);
}
