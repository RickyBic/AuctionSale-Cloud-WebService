/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.resource.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.itu.auctionSale.model.Enchere;
import com.itu.auctionSale.resource.Resource;
import com.itu.auctionSale.service.IPageService;
import com.itu.auctionSale.service.IService;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author RickyBic
 */
@CrossOrigin("*") // to allow from all domains
@RestController
@RequestMapping("/encheres")
public class EnchereResourceImpl implements Resource<Enchere> {

    @Autowired
    private IService<Enchere> enchereService;

    @Autowired
    private IPageService<Enchere> encherePageService;

    @Override
    public ResponseEntity<Page<Enchere>> findAll(Pageable pageable, String searchText) {
        return new ResponseEntity<>(encherePageService.findAll(pageable, searchText), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Enchere>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        return new ResponseEntity<>(encherePageService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Enchere> findById(Long id) {
        return new ResponseEntity<>(enchereService.findById(id).get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Enchere> save(Enchere enchere) {
        return new ResponseEntity<>(enchereService.saveOrUpdate(enchere), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Enchere> update(Enchere enchere) {
        return new ResponseEntity<>(enchereService.saveOrUpdate(enchere), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(enchereService.deleteById(id), HttpStatus.OK);
    }

}
