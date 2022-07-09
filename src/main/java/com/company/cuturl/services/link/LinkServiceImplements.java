package com.company.cuturl.services.link;

import com.company.cuturl.entities.Link;
import com.company.cuturl.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

@Service
public class LinkServiceImplements implements LinkService {

    @Autowired
    LinkRepository linkRepository;

    @Override
    public List<Link> getAllCustomerLinks(int customerId) {
        return linkRepository.getAllByCustomer_Id(customerId);
    }

    @Override
    public List<Link> getAllLinks() {
        return linkRepository.findAll();
    }

    @Override
    public void createLink(Link link) {
        Random random = new Random();
        String randValues = "";
        boolean next = false;
        List<Link> links = linkRepository.findAll();

        do {
            for (int i = 0; i < 5; i++) {
                char lower = (char) (random.nextInt(26) + 'a');
                char upper = (char) (random.nextInt(26) + 'A');
                int number = (int) (Math.random() * 9);

                switch (random.nextInt(3)) {
                    case 0: { randValues += lower; break; }
                    case 1: { randValues += upper; break; }
                    case 2: { randValues += number; break; }
                }
            }

            for (Link existingLink : links) {
                if (existingLink.getAfterLink().replace("http://localhost:8080/", "").equals(randValues)) {
                    next = true;
                    break;
                }
            }
        } while (next);

        String cutLink = "http://localhost:8080/" + randValues;

        link.setAfterLink(cutLink);
        linkRepository.save(link);
    }

    @Override
    public void deleteLink(int id) {
        linkRepository.deleteById(id);
    }
}