package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by admarcu on 11/23/2016.
 */
@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cityId;

    @Column(name = "name")
    private String name;

    @Column(name = "json", columnDefinition = "TEXT")
    private String json;


    @Column(name = "updated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated; //java.util.Date

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = {@JoinColumn(name = "cityId")}, inverseJoinColumns = {@JoinColumn(name = "userId")})
    private List<User> users;

    public City(String name, List<User> users,Date lastUpdated) {
        this.name = name;
        this.users = users;
        this.lastUpdated = lastUpdated;
    }

    public City() {
    }


    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return name;
    }

    public void setCityName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @PreRemove
    public void preDelete() {
        Iterator<User> it = this.getUsers().iterator();
        System.out.println(this.getUsers());
        while(it.hasNext()) {
            User current = it.next();
            System.out.println(current.getCities().remove(this));
        }
    }


    @Override
    public String toString() {
        return "City{" +
                "cityId=" + cityId +
                ", name='" + name + '\'' +
                ", json='" + json + '\'' +
                ", users=" + users +
                '}';
    }
}