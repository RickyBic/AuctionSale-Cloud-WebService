/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model.repository;

import com.itu.auctionSale.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RickyBic
 */
@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
