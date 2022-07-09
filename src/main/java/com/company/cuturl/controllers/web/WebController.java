package com.company.cuturl.controllers.web;

import com.company.cuturl.controllers.ServiceController;
import com.company.cuturl.entities.Customer;
import com.company.cuturl.entities.Link;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@EnableWebSecurity
public class WebController extends ServiceController {

    @GetMapping("/cuturl/login")
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error != null);

        return "login";
    }

    @GetMapping("/cuturl/registration")
    public String registration(Model model) {
        model.addAttribute("allCustomers", customerService.getAllLogin());

        return "registration";
    }

    @GetMapping("/cuturl/links")
    public String links(@AuthenticationPrincipal User userSecurity, Model model) {
        Customer customer = customerService.getCustomerByLogin(userSecurity.getUsername());
        List<Link> linkList = linkService.getAllCustomerLinks(customer.getId());

        model.addAttribute("links", linkList);

        return "links";
    }

    @GetMapping("/cuturl/profile")
    public String profile(@AuthenticationPrincipal User userSecurity, Model model) {
        Customer customer = customerService.getCustomerByLogin(userSecurity.getUsername());
        model.addAttribute("customer", customer);

        return "profile";
    }

    @GetMapping("/{relocate}")
    public String relocate(@PathVariable String relocate) {
        List<Link> links = linkService.getAllLinks();
        for (Link link : links) {
            if (link.getAfterLink().replace("https://cuturl2022.herokuapp.com/", "").equals(relocate)) {
                return "redirect:" + link.getBeforeLink();
            }
        }

        return "login";
    }
}
