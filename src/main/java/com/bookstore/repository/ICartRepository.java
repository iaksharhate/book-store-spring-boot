package com.bookstore.repository;

import com.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Integer> {

    @Query(value = "select * from cart where user_user_id =:userId", nativeQuery = true)
    List<Cart> getCartByUserId(int userId);
}
