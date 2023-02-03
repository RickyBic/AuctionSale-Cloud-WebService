/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.resource;

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
import com.itu.auctionSale.service.EnchereService;
import java.util.Collection;
import javax.ws.rs.QueryParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author RickyBic
 */
@CrossOrigin("*") // to allow from all domains
@RestController
@RequestMapping("/encheres")
public class EnchereResource {

    @Autowired
    private EnchereService enchereService;

    @GetMapping("collection")
    public ResponseEntity<Collection<Enchere>> findAll() {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(enchereService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search/{searchText}")
    public ResponseEntity<Page<Enchere>> findAll(Pageable pageable, @PathVariable String searchText) {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(enchereService.findAll(pageable, searchText), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Enchere>> findAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(enchereService.findAll(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                )
        ), HttpStatus.OK);
    }

    @GetMapping("historique")
    public ResponseEntity<Page<Enchere>> findAllByUser(int pageNumber, int pageSize, String sortBy, String sortDir, String email) {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(enchereService.findAllByUser(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                ),
                email), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Enchere> findById(@PathVariable Long id) {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(enchereService.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Enchere> save(@RequestBody Enchere enchere, @QueryParam("user_id") Long user_id, @QueryParam("categorie_id") Long categorie_id) {
        return new ResponseEntity<>(enchereService.save(enchere, user_id, categorie_id), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Enchere> update(@RequestBody Enchere enchere) {
        return new ResponseEntity<>(enchereService.update(enchere), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(enchereService.deleteById(id), HttpStatus.OK);
    }

}
