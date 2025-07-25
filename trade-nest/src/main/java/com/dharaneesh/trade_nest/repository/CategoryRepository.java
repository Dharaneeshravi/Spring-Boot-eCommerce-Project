package com.dharaneesh.trade_nest.repository;

import com.dharaneesh.trade_nest.model.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findByCategoryName(@NotBlank @Size(min = 2,max = 15) String categoryName);
}
