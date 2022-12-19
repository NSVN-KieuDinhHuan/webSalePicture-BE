package com.newhouse.repository;


import com.newhouse.model.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Repository
public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Query(value = "SELECT * FROM  carts_cart_detail_list p " +
            " join cart_detail m on p.cart_detail_list_id=m.id where p.cart_id= :id", nativeQuery = true)
    List<CartDetail> getAllByCartID (@Param(value = "id") Long id);

    @Query(value = "SELECT  * FROM cart_detail_product_options p " +
            "join product_option m on p.product_options_id=m.id where p.cart_detail_id= :id", nativeQuery = true)
    List<Object> getOptionbyCartDetail (@Param(value = "id") Long id);
}
