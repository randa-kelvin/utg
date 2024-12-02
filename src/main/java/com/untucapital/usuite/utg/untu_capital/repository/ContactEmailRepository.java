package com.untucapital.usuite.utg.untu_capital.repository;

import com.untucapital.usuite.utg.untu_capital.model.ContactEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactEmailRepository extends JpaRepository<ContactEmail, String> {
}
