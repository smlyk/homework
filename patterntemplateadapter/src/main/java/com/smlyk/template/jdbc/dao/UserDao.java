package com.smlyk.template.jdbc.dao;

import com.smlyk.template.jdbc.JDBCTemplate;
import com.smlyk.template.jdbc.RowMapper;
import com.smlyk.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author yekai
 */
public class UserDao extends JDBCTemplate{

    public UserDao(DataSource dataSource) {
        super(dataSource);
    }

    public List<?> selectAll(){
        String sql = "select * from user";
        return super.excuteQuery(sql, (RowMapper<User>) (rs, row) -> buildUser(rs) ,null);
    }

    public List<?> selectById(int id){
        String sql = "select * from user where id = ?";
        return super.excuteQuery(sql,(RowMapper<User>) (rs, row) -> buildUser(rs) ,new Object[]{id});
    }

    private User buildUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setAge(rs.getInt("age"));
        user.setAddr(rs.getString("addr"));
        return user;
    }
}
