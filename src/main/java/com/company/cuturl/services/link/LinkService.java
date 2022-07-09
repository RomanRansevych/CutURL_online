package com.company.cuturl.services.link;

import com.company.cuturl.entities.Link;
import java.util.List;

public interface LinkService {

    List<Link> getAllCustomerLinks(int customerId);

    List<Link> getAllLinks();

    void createLink(Link link);

    void deleteLink(int id);
}
