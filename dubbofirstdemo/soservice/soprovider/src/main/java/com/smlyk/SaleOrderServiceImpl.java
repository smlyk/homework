package com.smlyk;

import java.util.Arrays;
import java.util.List;

/**
 * @author yekai
 */
public class SaleOrderServiceImpl implements ISaleOrderService{


    @Override
    public List getSaleOrderList() {
        return Arrays.asList("Tom", "Mic", "James");
    }
}
