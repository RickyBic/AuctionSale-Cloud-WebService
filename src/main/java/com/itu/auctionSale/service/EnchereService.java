/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service;

import com.itu.auctionSale.model.Categorie;
import java.util.Collection;
import java.util.Optional;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.itu.auctionSale.model.Enchere;
import com.itu.auctionSale.model.Offre;
import com.itu.auctionSale.model.User;
import com.itu.auctionSale.model.repository.CategorieRepository;
import com.itu.auctionSale.model.repository.EnchereRepository;
import com.itu.auctionSale.model.repository.OffreRepository;
import com.itu.auctionSale.model.repository.UserRepository;
import com.itu.auctionSale.utils.Fonctions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author RickyBic
 */
@Service
public class EnchereService {

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategorieRepository categorieRepository;

    @Autowired
    private OffreRepository offreRepository;

    public Collection<Enchere> findAll() {
        return (Collection<Enchere>) enchereRepository.findAll();
    }

    public Page<Enchere> findAll(Pageable pageable, String searchText) {
        return enchereRepository.findAllEncheres(pageable, searchText);
    }

    public Page<Enchere> findAll(Pageable pageable) {
        return enchereRepository.findAll(pageable);
    }

    public Page<Enchere> findAllByUser(Pageable pageable, String email) {
        return enchereRepository.findAllEncheresByUser(pageable, email);
    }

    public Optional<Enchere> findById(Long id) {
        return enchereRepository.findById(id);
    }

    /**
     * Save enchere with user and categorie.
     *
     * @param enchere
     * @param user_id
     * @param categorie_id
     * @return
     */
    public Enchere save(Enchere enchere, Long user_id, Long categorie_id) {
        Optional<User> user = userRepository.findById(user_id);
        Optional<Categorie> categorie = categorieRepository.findById(categorie_id);
        enchere.setUser(user.get());
        Date datenow = new Date(new Date().getTime() + TimeUnit.HOURS.toMillis(3)); // UTC+3
        enchere.setDatedebut(new SimpleDateFormat("dd/MM/yyy HH:mm").format(datenow));
        enchere.setCategorie(categorie.get());
        enchere.setStatut("En cours");
        return enchereRepository.saveAndFlush(enchere);
    }

    public Enchere update(Enchere enchere) {
        return enchereRepository.saveAndFlush(enchere);
    }

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

    public void checkdatefinAll() {
        List<Enchere> le = enchereRepository.findAllEncheresByStatut("En cours");
        for (int i = 0; i < le.size(); i++) {
            if (Fonctions.isEnchereFin(le.get(i))) {
                le.get(i).setStatut("Terminée");
                enchereRepository.saveAndFlush(le.get(i));
                List<Offre> lo = offreRepository.findAllOffresByEnchere(le.get(i).getId());
                Offre offreMax = Fonctions.getOffreMax(lo);
                offreMax.setStatut("Adjugée");
                offreRepository.saveAndFlush(offreMax);
                User user = offreMax.getUser();
                user.setSoldecompte(user.getSoldecompte() - offreMax.getPrix());
                userRepository.saveAndFlush(user);
            }
        }
    }

}
