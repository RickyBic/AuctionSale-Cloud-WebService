/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model.repository;

import com.itu.auctionSale.model.Enchere;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author RickyBic
 */
public interface EnchereRepository extends PagingAndSortingRepository<Enchere, Long> {

    @Query("FROM Enchere e WHERE e.description LIKE %:searchText% OR e.statut LIKE %:searchText%")
    Page<Enchere> findAllEncheres(Pageable pageable, @Param("searchText") String searchText);

}
