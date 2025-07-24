package com.dharaneesh.trade_nest.repository;

import com.dharaneesh.trade_nest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
