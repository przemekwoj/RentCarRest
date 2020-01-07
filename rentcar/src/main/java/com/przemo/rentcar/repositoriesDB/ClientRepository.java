package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.entities.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
