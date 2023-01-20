/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itu.auctionSale.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RickyBic
 */
@RestController
public class TestResource {

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

}
