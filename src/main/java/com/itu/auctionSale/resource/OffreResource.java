/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.resource;

import com.itu.auctionSale.model.Offre;
import com.itu.auctionSale.service.EnchereService;
import com.itu.auctionSale.service.OffreService;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RickyBic
 */
@CrossOrigin("*") // to allow from all domains
@RestController
@RequestMapping("/offres")
public class OffreResource {

    @Autowired
    private OffreService offreService;

    @Autowired
    private EnchereService enchereService;

    @GetMapping
    public ResponseEntity<Page<Offre>> findAllByEnchere(int pageNumber, int pageSize, String sortBy, String sortDir, Long enchere_id) {
        return new ResponseEntity<>(offreService.findAllByEnchere(
                PageRequest.of(
                        pageNumber, pageSize,
                        sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
                ),
                enchere_id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Offre> save(@RequestBody Offre offre, @QueryParam("enchere_id") Long enchere_id, @QueryParam("email") String email) {
        enchereService.checkdatefinAll();
        return new ResponseEntity<>(offreService.save(offre, enchere_id, email), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Offre> update(@RequestBody Offre offre) {
        return new ResponseEntity<>(offreService.update(offre), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(offreService.deleteById(id), HttpStatus.OK);
    }

}
