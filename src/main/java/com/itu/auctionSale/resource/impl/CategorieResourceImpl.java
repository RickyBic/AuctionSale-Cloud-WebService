/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.resource.impl;

import com.itu.auctionSale.model.Categorie;
import com.itu.auctionSale.resource.Resource;
import com.itu.auctionSale.service.IPageService;
import com.itu.auctionSale.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RickyBic
 */
@CrossOrigin("*") // to allow from all domains
@RestController
@RequestMapping("/categories")
public class CategorieResourceImpl implements Resource<Categorie> {

    @Autowired
    private IService<Categorie> categorieService;

    @Autowired
    private IPageService<Categorie> categoriePageService;

    @Override
    public ResponseEntity<Page<Categorie>> findAll(Pageable pageable, String searchText) {
        return new ResponseEntity<>(categoriePageService.findAll(pageable, searchText), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Categorie>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(categoriePageService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Categorie> findById(Long id) {
        return new ResponseEntity<>(categorieService.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Categorie> save(Categorie categorie) {
        return new ResponseEntity<>(categorieService.saveOrUpdate(categorie), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Categorie> update(Categorie categorie) {
        return new ResponseEntity<>(categorieService.saveOrUpdate(categorie), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(categorieService.deleteById(id), HttpStatus.OK);
    }

}
