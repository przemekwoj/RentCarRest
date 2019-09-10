package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.users.Administration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrationRepository extends JpaRepository<Administration,Long> {

    Optional<Administration> findByEmail(String email);
}
