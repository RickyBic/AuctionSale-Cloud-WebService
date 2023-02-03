package com.itu.auctionSale;

import com.itu.auctionSale.model.Role;
import com.itu.auctionSale.model.User;
import com.itu.auctionSale.service.RoleService;
import com.itu.auctionSale.service.UserService;
import com.itu.auctionSale.utils.ConstantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AuctionSaleApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(AuctionSaleApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleService.findAll().isEmpty()) {
            roleService.saveOrUpdate(new Role(ConstantUtils.ADMIN.toString()));
            roleService.saveOrUpdate(new Role(ConstantUtils.USER.toString()));
        }

        if (userService.findAll().isEmpty()) {
            User user1 = new User();
            user1.setName("User");
            user1.setEmail("user@gmail.com");
            user1.setMobile("0325844569");
            user1.setPassword(new BCryptPasswordEncoder().encode("testuser"));
            user1.setSoldecompte(20000000.0);
            user1.setRole(roleService.findByName(ConstantUtils.USER.toString()));
            userService.saveOrUpdate(user1);

            User user2 = new User();
            user2.setName("Admin");
            user2.setEmail("admin@gmail.com");
            user2.setMobile("0331558306");
            user2.setPassword(new BCryptPasswordEncoder().encode("testadmin"));
            user2.setSoldecompte(0.0);
            user2.setRole(roleService.findByName(ConstantUtils.ADMIN.toString()));
            userService.saveOrUpdate(user2);
        }
    }

}
