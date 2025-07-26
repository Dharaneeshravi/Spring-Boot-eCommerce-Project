package com.dharaneesh.trade_nest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    String message;
    boolean status;
}
