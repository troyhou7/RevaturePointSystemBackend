package dev.group3.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
//@JsonIgnoreProperties(value = { "password" }) // making it so passwords will not be passed around in JSON
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "role")
    private String role;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "username")
    private String username;

    @Column(name = "emp_password")
    private String password;

    @Column(name = "current_rp")
    private int currentRevaPoints;

    @Column(name = "alltime_rp")
    private int allTimeRevaPoints;

    @Column(name = "batch_id")
    private int batchId;

    @Column(name = "img_URL")
    private String imgURL;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_prize",
            joinColumns = { @JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "prize_id")})
    private Set<Prize> prizes = new HashSet<>();


    public Employee(){}

    public Employee(int employeeId, String role, String fname, String lname, String username, String password, int currentRevaPoints, int allTimeRevaPoints, int batchId, String imgURL) {
        this.employeeId = employeeId;
        this.role = role;
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.currentRevaPoints = currentRevaPoints;
        this.allTimeRevaPoints = allTimeRevaPoints;
        this.batchId = batchId;
        this.imgURL = imgURL;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrentRevaPoints() {
        return currentRevaPoints;
    }

    public void setCurrentRevaPoints(int currentRevaPoints) {
        this.currentRevaPoints = currentRevaPoints;
    }

    public int getAllTimeRevaPoints() {
        return allTimeRevaPoints;
    }

    public void setAllTimeRevaPoints(int allTimeRevaPoints) {
        this.allTimeRevaPoints = allTimeRevaPoints;
    }

    public int getBatchId() {
        return batchId;
    }

    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    public Set<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(Set<Prize> prizes) {
        this.prizes = prizes;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", role='" + role + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", currentRevaPoints=" + currentRevaPoints +
                ", allTimeRevaPoints=" + allTimeRevaPoints +
                ", batchId=" + batchId +
                ", imgURL='" + imgURL + '\'' +
                ", prizes=" + prizes +
                '}';
    }
}
