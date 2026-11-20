package com.ecommerce.repository;

import com.ecommerce.entity.AppUser;
import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByEmail(String email);

    AppUser findByEmail(String email);

}
