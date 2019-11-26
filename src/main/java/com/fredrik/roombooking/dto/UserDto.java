package com.fredrik.roombooking.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

    @NotNull
    @Size(min=1, max=50)
    private String firstName;
    @NotNull
    @Size(min=1, max=50)
    private String lastName;
    @Email(message = "Provide a valid e-mail address.   ")
    @NotNull
    @Size(min=6, max=254)
    private String email;
    @NotNull
    @Size(min=8, max=71)
    private String password;
    @NotNull
    @Size(min=8, max=71)
    private String passwordConfirm;
    // TODO Add custom validation for password match to class

    public UserDto() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

}