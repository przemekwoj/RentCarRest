package com.przemo.rentcar.users;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class User
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long user_id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public User()
    {
        
    }

    public User(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(user_id, user.user_id) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, firstName, lastName, email, phone);
    }
}
