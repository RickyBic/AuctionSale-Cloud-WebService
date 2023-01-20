/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service;

/**
 *
 * @author RickyBic
 */
public interface IRoleService<T> extends IService<T> {

    T findByName(String name);

}
