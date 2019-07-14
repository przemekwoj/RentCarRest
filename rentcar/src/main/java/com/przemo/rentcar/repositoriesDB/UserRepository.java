package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
