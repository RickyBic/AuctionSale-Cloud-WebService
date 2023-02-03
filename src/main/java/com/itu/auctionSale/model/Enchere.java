/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.model;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author RickyBic
 */
@Entity
@Table(name = "tbl_enchere")
public class Enchere {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String datedebut;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @Column(nullable = false)
    private Float prixdepart;

    @Column(nullable = false)
    private String datefin;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = true)
    private String urlphotos;

    @OneToMany(targetEntity = Offre.class, mappedBy = "enchere", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offre> offres;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Float getPrixdepart() {
        return prixdepart;
    }

    public void setPrixdepart(Float prixdepart) {
        this.prixdepart = prixdepart;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getUrlphotos() {
        return urlphotos;
    }

    public void setUrlphotos(String urlphotos) {
        this.urlphotos = urlphotos;
    }

}
