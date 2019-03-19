package com.smlyk.template.jdbc;

import java.sql.ResultSet;

/**
 * ORM映射定制化的接口
 * @author yekai
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs, int row) throws Exception;

}
