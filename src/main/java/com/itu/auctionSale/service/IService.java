/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service;

import java.util.Collection;
import java.util.Optional;

/**
 *
 * @author RickyBic
 */
public interface IService<T> {

    Collection<T> findAll();

    Optional<T> findById(Long id);

    T saveOrUpdate(T t);

    String deleteById(Long id);

}
