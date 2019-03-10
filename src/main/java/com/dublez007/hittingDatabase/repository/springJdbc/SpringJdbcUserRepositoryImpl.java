package com.dublez007.hittingDatabase.repository.springJdbc;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.repository.UserRepository;
import com.dublez007.hittingDatabase.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpringJdbcUserRepositoryImpl implements UserRepository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SimpleJdbcInsert insertUser;
    private static final BeanPropertyRowMapper ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);


    @Autowired
    public SpringJdbcUserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }



    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
        if(user.isNew()){
            Number key = insertUser.executeAndReturnKey(parameterSource);
            user.setId(key.intValue());

        } else{
            int result = namedParameterJdbcTemplate.update("" +
                    "UPDATE users " +
                    "SET " +
                    "name=:name, " +
                    "email=:email, " +
                    "password=:password, " +
                    "registered=:registered," +
                    "enabled=:enabled," +
                    "calories_per_day=:caloriesPerDay" +
                    "WHERE" +
                    "id=:id",
                    parameterSource);
            if(result == 0)
                return null;
        }

        return user;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM users WHERE id = ?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public User getByEmail(String email) {
        throw new NotFoundException("method SpringJdbcUserRepositoryImpl.getByEmail() is not implemented");
    }

    @Override
    public boolean remove(int id) {
        throw new NotFoundException("method SpringJdbcUserRepositoryImpl.remove() is not implemented");
    }

    @Override
    public List<User> getAll() {
        throw new NotFoundException("method SpringJdbcUserRepositoryImpl.getAll() is not implemented");
    }
}
