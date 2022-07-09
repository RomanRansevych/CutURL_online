package com.company.cuturl.repositories;


import com.company.cuturl.entities.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Integer> {

    List<Link> getAllByCustomer_Id(int customerId);
}
