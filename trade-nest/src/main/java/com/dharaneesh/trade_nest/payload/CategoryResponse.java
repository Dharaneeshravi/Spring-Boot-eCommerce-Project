package com.dharaneesh.trade_nest.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private List<CategoryDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
}
