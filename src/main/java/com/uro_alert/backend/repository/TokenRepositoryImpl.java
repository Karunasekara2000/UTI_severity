package com.uro_alert.backend.repository;

import com.uro_alert.backend.model.Token;
import com.uro_alert.backend.model.User;
import com.uro_alert.backend.model.mapper.TokenMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TokenRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Token> findAllValidTokenByUser(Integer id) {

        StringBuilder query = new StringBuilder("SELECT t FROM Token t inner join User u " +
                "     on t.user.id = u.id " +
                "     where u.id = :id and (t.expired = false or t.revoked = false");
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return namedParameterJdbcTemplate.query(query.toString(), params, new TokenMapper());
    }

    @Override
    public Token findByToken(String token) {
        StringBuilder query = new StringBuilder("SELECT * FROM token WHERE token=:token ");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("token", token);
        return namedParameterJdbcTemplate.queryForObject(query.toString(), param, Token.class);
    }

    @Override
    public Token save(Token token) {

        StringBuilder query = new StringBuilder("INSERT INTO token (user_id, token, token_type, expired, revoked) VALUES (?, ?, ?, ?, ? )");

        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, token.getUser().getId());
            ps.setString(2, token.getToken());
            ps.setString(3, String.valueOf(token.getTokenType()));
            ps.setBoolean(4, token.isExpired());
            ps.setBoolean(5, token.isRevoked());
            return ps;
        }, key);

        if (key.getKey() != null) {
            token.setId(key.getKey().intValue());
        }

        return token;
    }

    @Override
    public List<Token> saveAll(List<Token> tokenList) {


        StringBuilder query = new StringBuilder("INSERT INTO token (user_id, token, token_type, expired, revoked) VALUES (?, ?, ?, ?, ? )");

        KeyHolder key = new GeneratedKeyHolder();
        tokenList.forEach(token -> {
            jdbcTemplate.update(con -> {
                PreparedStatement ps = con.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, token.getUser().getId());
                ps.setString(2, token.getToken());
                ps.setString(3, String.valueOf(token.getTokenType()));
                ps.setBoolean(4, token.isExpired());
                ps.setBoolean(5, token.isRevoked());
                return ps;
            }, key);

            if (key.getKey() != null) {
                token.setId(key.getKey().intValue());
            }


        });

        return tokenList;
    }

}
