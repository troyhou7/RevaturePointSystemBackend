package dev.group3.models;

import javax.persistence.Column;

public class UserAuthentication {
    private int employeeId;
    private String role;
    private String fname;
    private String lname;
    private String username;
    private String JwtToken;

    public UserAuthentication() {
    }

    public UserAuthentication(int employeeId, String role, String fname, String lname, String username, String jwtToken) {
        this.employeeId = employeeId;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        JwtToken = jwtToken;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setJwtToken(String jwtToken) {
        JwtToken = jwtToken;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getRole() {
        return role;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getUsername() {
        return username;
    }

    public String getJwtToken() {
        return JwtToken;
    }

    @Override
    public String toString() {
        return "UserAuthentication{" +
                "employeeId=" + employeeId +
                ", role='" + role + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", JwtToken='" + JwtToken + '\'' +
                '}';
    }
}
