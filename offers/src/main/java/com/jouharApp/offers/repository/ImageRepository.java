package com.jouharApp.offers.repository;

import com.jouharApp.offers.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{

//    Optional<Image> findByName(String fileName);
    Image findByName(String fileName);
    Optional<Image> findById(Long id);


//    @Query("select c from Image c where c.offer.id like %?1")
//    Optional<Image> findByOfferId(Long offer_id);

    //    @Query( "SELECT bk FROM Image bk join bk.offer pg WHERE bk.offer.id = :offer_id")
//    List<Image> findByOfferId(@Param("offer_id") Long offer_id);
//    List<Image> findByOfferId(Long offer_id);
}