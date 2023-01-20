/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author RickyBic
 */
public interface IPageService<T> {

    Page<T> findAll(Pageable pageable, String searchText);

    Page<T> findAll(Pageable pageable);

}
