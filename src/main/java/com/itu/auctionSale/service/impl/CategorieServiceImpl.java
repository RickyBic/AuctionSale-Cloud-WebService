/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service.impl;

import com.itu.auctionSale.model.Categorie;
import com.itu.auctionSale.model.repository.CategorieRepository;
import com.itu.auctionSale.service.IPageService;
import com.itu.auctionSale.service.IService;
import java.util.Collection;
import java.util.Optional;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author RickyBic
 */
@Service
public class CategorieServiceImpl implements IService<Categorie>, IPageService<Categorie> {

    @Autowired
    private CategorieRepository categorieRepository;

    @Override
    public Collection<Categorie> findAll() {
        return (Collection<Categorie>) categorieRepository.findAll();
    }

    @Override
    public Page<Categorie> findAll(Pageable pageable, String searchText) {
        return categorieRepository.findAllCategories(pageable, searchText);
    }

    @Override
    public Page<Categorie> findAll(Pageable pageable) {
        return categorieRepository.findAll(pageable);
    }

    @Override
    public Optional<Categorie> findById(Long id) {
        return categorieRepository.findById(id);
    }

    @Override
    public Categorie saveOrUpdate(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            categorieRepository.deleteById(id);
            jsonObject.put("message", "Categorie deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
