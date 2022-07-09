package com.company.cuturl.controllers;

import com.company.cuturl.services.customer.CustomerService;
import com.company.cuturl.services.link.LinkService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ServiceController {

    @Autowired
    protected CustomerService customerService;

    @Autowired
    protected LinkService linkService;
}
