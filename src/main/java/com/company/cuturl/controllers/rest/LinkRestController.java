package com.company.cuturl.controllers.rest;

import com.company.cuturl.controllers.ServiceController;
import com.company.cuturl.entities.Link;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LinkRestController extends ServiceController {

    @PostMapping("/link/create")
    public void createLink(@AuthenticationPrincipal User user, @RequestBody Link link) {
        link.setCustomer(customerService.getCustomerByLogin(user.getUsername()));
        linkService.createLink(link);
    }

    @DeleteMapping("link/delete/{id}")
    public void deleteLink(@PathVariable int id) {
        linkService.deleteLink(id);
    }
}
