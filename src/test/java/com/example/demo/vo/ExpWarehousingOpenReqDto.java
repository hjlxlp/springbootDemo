package com.example.demo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ExpWarehousingOpenReqDto {

    /**
     * 采购入库list
     */
    @NotNull
    private List<ExpWarehousingDetailsOpenReqDto> reqDtoList;

}
