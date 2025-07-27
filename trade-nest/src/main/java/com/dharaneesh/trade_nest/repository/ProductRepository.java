package com.dharaneesh.trade_nest.repository;

import com.dharaneesh.trade_nest.model.Category;
import com.dharaneesh.trade_nest.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageable);

    List<Product> findByProductNameLikeIgnoreCase(String s);
}
