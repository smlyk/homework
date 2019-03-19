package com.smlyk.template.jdbc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yekai
 */
public class JDBCTemplate {

    private DataSource dataSource;

    public JDBCTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<?> excuteQuery(String sql, RowMapper<?> rowMapper, Object[] values){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = this.getConnection();

            //2.创建语句集
            ps = this.getPrepareStatement(conn, sql);

            //3.执行语句集
            rs = this.excuteQuery(ps, values);

            //4.处理结果集
            List<?> result = this.parseResultSet(rs, rowMapper);
            return result;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //5.关闭资源
                this.closeResource(rs, ps, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void closeResource(ResultSet rs, PreparedStatement ps, Connection conn) throws SQLException {
        rs.close();
        ps.close();
        conn.close();
    }

    private List<?> parseResultSet(ResultSet rs, RowMapper<?> rowMapper) throws Exception {
        List<Object> result = new ArrayList<>();
        int rowNum = 1;
        while (rs.next()){
            result.add(rowMapper.mapRow(rs, rowNum++));
        }
        return result;
    }

    private ResultSet excuteQuery(PreparedStatement ps, Object[] values) throws SQLException {
        if (null != values && values.length>0){
            for (int i = 0; i<values.length; i++){
                ps.setObject(i+1, values[i]);
            }
        }
        return ps.executeQuery();
    }

    private PreparedStatement getPrepareStatement(Connection conn, String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }

    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }


}
