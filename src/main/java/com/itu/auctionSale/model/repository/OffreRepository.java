/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model.repository;

import com.itu.auctionSale.model.Offre;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RickyBic
 */
public interface OffreRepository extends JpaRepository<Offre, Long> {

    @Query("FROM Offre o WHERE o.enchere.id=:enchere_id")
    Page<Offre> findAllOffresByEnchere(Pageable pageable, @Param("enchere_id") Long enchere_id);

    @Query("FROM Offre o WHERE o.enchere.id=:enchere_id")
    List<Offre> findAllOffresByEnchere(@Param("enchere_id") Long enchere_id);

}
