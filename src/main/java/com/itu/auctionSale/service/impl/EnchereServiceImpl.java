/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service.impl;

import java.util.Collection;
import java.util.Optional;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.itu.auctionSale.model.Enchere;
import com.itu.auctionSale.model.repository.EnchereRepository;
import com.itu.auctionSale.service.IPageService;
import com.itu.auctionSale.service.IService;

/**
 *
 * @author RickyBic
 */
@Service
public class EnchereServiceImpl implements IService<Enchere>, IPageService<Enchere> {

    @Autowired
    private EnchereRepository enchereRepository;

    @Override
    public Collection<Enchere> findAll() {
        return (Collection<Enchere>) enchereRepository.findAll();
    }

    @Override
    public Page<Enchere> findAll(Pageable pageable, String searchText) {
        return enchereRepository.findAllEncheres(pageable, searchText);
    }

    @Override
    public Page<Enchere> findAll(Pageable pageable) {
        return enchereRepository.findAll(pageable);
    }

    @Override
    public Optional<Enchere> findById(Long id) {
        return enchereRepository.findById(id);
    }

    @Override
    public Enchere saveOrUpdate(Enchere enchere) {
        return enchereRepository.save(enchere);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            enchereRepository.deleteById(id);
            jsonObject.put("message", "Enchere deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
