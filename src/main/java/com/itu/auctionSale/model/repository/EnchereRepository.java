/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model.repository;

import com.itu.auctionSale.model.Enchere;
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
public interface EnchereRepository extends JpaRepository<Enchere, Long> {

    @Query("FROM Enchere e WHERE lower(e.user.name) LIKE concat('%', lower(:searchText), '%') OR "
            + "lower(e.description) LIKE concat('%', lower(:searchText), '%') OR "
            + "lower(e.categorie.name) LIKE concat('%', lower(:searchText), '%') OR "
            + "lower(e.statut) LIKE concat('%', lower(:searchText), '%')")
    Page<Enchere> findAllEncheres(Pageable pageable, @Param("searchText") String searchText);

    @Query("FROM Enchere e WHERE e.user.email=:email")
    Page<Enchere> findAllEncheresByUser(Pageable pageable, @Param("email") String email);

    @Query("FROM Enchere e WHERE e.statut=:statut")
    List<Enchere> findAllEncheresByStatut(@Param("statut") String statut);

}
