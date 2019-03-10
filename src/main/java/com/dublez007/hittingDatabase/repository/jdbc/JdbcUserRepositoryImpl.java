package com.dublez007.hittingDatabase.repository.jdbc;

import com.dublez007.hittingDatabase.model.User;
import com.dublez007.hittingDatabase.model.UserRole;
import com.dublez007.hittingDatabase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;


public class JdbcUserRepositoryImpl implements UserRepository {

    private static final String SQL_INSERT_USER =
        "insert into users (name, email, password, registered, enabled, calories_per_day) " +
                "values (?, ?, ?, ?, ?, ?)";
    private static final  String SQL_UPDATE_USER = "update users set" +
            "name = ?, email = ?, password = ?, registered = ?, enabled = ?, calories_per_day = ?";
    private static final String SQL_SELECT_USER = "select * from users where id = ?";
    private static final String SQL_SELECT_USER_ROLE = "select * from user_roles where user_id = ?";

    private DataSource dataSource;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        if(user.isNew()) {
            try{
                conn = dataSource.getConnection();
                stmt = conn.prepareStatement(SQL_INSERT_USER, stmt.RETURN_GENERATED_KEYS);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setTimestamp(4, Timestamp.valueOf(user.getRegistered()));
                stmt.setBoolean(5, true);
                stmt.setInt(6, user.getCaloriesPerDay());
                int affectedRows = stmt.executeUpdate();

                if(affectedRows == 0){
                    throw new SQLException("Creating user failed, no rows affected");
                }

                try (ResultSet generatedKeys = stmt.getGeneratedKeys()){
                    if(generatedKeys.next()){
                        user.setId(generatedKeys.getInt(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained");
                    }
                }
                return user;

            } catch (SQLException e){
                e.printStackTrace();
                return null;
            } finally {
                try{
                    if(stmt != null){
                        stmt.close();
                    }
                    if(conn != null){
                        conn.close();
                    }
                } catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }

        else{
            try{
                conn = dataSource.getConnection();
                stmt = conn.prepareStatement(SQL_UPDATE_USER);
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getEmail());
                stmt.setString(3, user.getPassword());
                stmt.setTimestamp(4, Timestamp.valueOf(user.getRegistered()));
                stmt.setBoolean(5, true);
                stmt.setInt(6, user.getCaloriesPerDay());
                stmt.execute();
                return user;

            } catch (SQLException e){
                e.printStackTrace();
                return null;
            } finally {
                try{
                    if(stmt != null)
                        stmt.close();
                    if(conn != null)
                        conn.close();
                } catch (SQLException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    @Override
    public User get(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        ResultSet rs = null;
        User user = null;

        if(id == 0)
            return null;
        else{
            try {
                conn = dataSource.getConnection();
                stmt = conn.prepareStatement(SQL_SELECT_USER);
                stmt.setInt(1, id);
                rs = stmt.executeQuery();

                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setCaloriesPerDay(rs.getInt("calories_per_day"));
                    user.setEnabled(rs.getBoolean("enabled"));
                }

                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }

                stmt2 = conn.prepareStatement(SQL_SELECT_USER);
                stmt2.setInt(1, user.getId());
                rs = stmt.executeQuery();
                if (rs.next()) {
                    UserRole role = UserRole.valueOf(rs.getString("role"));
                    user.setRole(role);
                }

                return user;

            } catch (SQLException e) {
                e.printStackTrace();
                return null;

            }finally{
                try{
                    if(stmt2 != null){
                        stmt2.close();
                    }
                    if(conn != null)
                        conn.close();
                } catch (SQLException e2){
                    e2.printStackTrace();
                    return null;
                }
            }
        }

    }

    //TODO
    @Override
    public User getByEmail(String email) {
        return null;
    }

    //TODO
    @Override
    public boolean remove(int id) {
        return false;
    }

    //TODO
    @Override
    public List<User> getAll() {
        return null;
    }
}
