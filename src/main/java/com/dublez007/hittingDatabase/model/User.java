package com.dublez007.hittingDatabase.model;

import java.time.LocalDateTime;

import static com.dublez007.hittingDatabase.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class User extends AbstractBaseEntity {
    private String name;
    private String email;
    private String password;
    private int caloriesPerDay;
    private boolean enabled;
    private LocalDateTime registered;
    private UserRole role;

    public User(){
    };

    public User(Integer id,
                String name,
                String email,
                String password,
                int caloriesPerDay,
                boolean enabled,
                LocalDateTime registered,
                UserRole role
                ) {
        super(id);
        this.name = name;
        this.email = email;
        this.password = password;
        this.caloriesPerDay = caloriesPerDay;
        this.enabled = enabled;
        this.registered = registered;
        this.role = role;
    }

    public User(Integer id, String name, String email, String password, UserRole role) {
        this(id, name, email, password, DEFAULT_CALORIES_PER_DAY, true, LocalDateTime.now(), role);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public void setCaloriesPerDay(int caloriesPerDay) {
        this.caloriesPerDay = caloriesPerDay;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", role=" + role +
                ", caloriesPerDay=" + caloriesPerDay +
                '}';
    }
}