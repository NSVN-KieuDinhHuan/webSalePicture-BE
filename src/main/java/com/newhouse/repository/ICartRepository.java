package com.newhouse.repository;


import com.newhouse.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM  cart p " +
            " where p.cart_group_id= :id", nativeQuery = true)
    List<Cart> getAllByCartID (@Param(value = "id") Long id);

    @Query(value = "SELECT  * FROM cart_options p " +
            "join option m on p.options_id=m.id where p.cart_id= :id", nativeQuery = true)
    List<Object> getOptionbyCartDetail (@Param(value = "id") Long id);

    @Query(value = "DELETE cart.* FROM cart  WHERE  = :cartGroupId", nativeQuery = true)
    void deleteCartByCartGroupId(
            @Param(value = "cartGroupId") int cartGroupId);
}
