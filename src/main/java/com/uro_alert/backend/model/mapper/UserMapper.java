package com.uro_alert.backend.model.mapper;

import com.uro_alert.backend.enumeration.Role;
import com.uro_alert.backend.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .firstname(rs.getString("first_name"))
                .lastname(rs.getString("last_name"))
                .email(rs.getString("email"))
                .role(Role.valueOf(rs.getString("role")))
                .build();
    }
}
