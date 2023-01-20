/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model.repository;

import com.itu.auctionSale.model.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RickyBic
 */
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    @Query("FROM Categorie c WHERE c.name LIKE %:searchText%")
    Page<Categorie> findAllCategories(Pageable pageable, @Param("searchText") String searchText);

}
