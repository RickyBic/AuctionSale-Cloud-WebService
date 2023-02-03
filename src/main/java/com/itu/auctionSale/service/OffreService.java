/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.service;

import com.itu.auctionSale.model.Enchere;
import com.itu.auctionSale.model.Offre;
import com.itu.auctionSale.model.User;
import com.itu.auctionSale.model.repository.EnchereRepository;
import com.itu.auctionSale.model.repository.OffreRepository;
import com.itu.auctionSale.model.repository.UserRepository;
import com.itu.auctionSale.utils.Fonctions;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EnchereRepository enchereRepository;

    @Autowired
    private UserRepository userRepository;

    public Page<Offre> findAllByEnchere(Pageable pageable, Long enchere_id) {
        return offreRepository.findAllOffresByEnchere(pageable, enchere_id);
    }

    /**
     * Save offre with enchere and user.
     *
     * @param offre
     * @param enchere_id
     * @param email
     * @return
     */
    public Offre save(Offre offre, Long enchere_id, String email) {
        Optional<Enchere> enchere = enchereRepository.findById(enchere_id);
        if (offre.getPrix() < enchere.get().getPrixdepart() || !enchere.get().getStatut().equalsIgnoreCase("En cours")) {
            return null;
        }
        User user = userRepository.findByEmail(email);
        /*if (offre.getPrix() > user.getSoldecompte()) {
            return null;
        }*/
        List<Offre> lo = offreRepository.findAllOffresByEnchere(enchere_id);
        if (!lo.isEmpty()) {
            Offre offremax = Fonctions.getOffreMax(lo);
            if (offre.getPrix() <= offremax.getPrix()) {
                return null;
            }
        }
        offre.setEnchere(enchere.get());
        offre.setUser(user);
        offre.setDate(new SimpleDateFormat("dd/MM/yyy HH:mm").format(new Date()));
        offre.setStatut("Non adjugée");
        return offreRepository.saveAndFlush(offre);
    }

    public Offre update(Offre offre) {
        return offreRepository.saveAndFlush(offre);
    }

    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            offreRepository.deleteById(id);
            jsonObject.put("message", "Offre deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

}
