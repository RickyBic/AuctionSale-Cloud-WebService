/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.utils;

import com.itu.auctionSale.model.Enchere;
import com.itu.auctionSale.model.Offre;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author RickyBic
 */
public class Fonctions {

    public static Offre getOffreMax(List<Offre> lo) {
        int imax = 0;
        for (int i = 0; i < lo.size(); i++) {
            if (lo.get(i).getPrix() > lo.get(imax).getPrix()) {
                imax = i;
            }
        }
        return lo.get(imax);
    }

    public static boolean isEnchereFin(Enchere e) {
        boolean fin = false;
        try {
            Date datenow = new Date();
            Date datefin = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(e.getDatefin());
            if (datenow.compareTo(datefin) >= 0) {
                fin = true;
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return fin;
    }

}
