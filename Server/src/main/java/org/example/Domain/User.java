package org.example.Domain;

import java.util.Objects;

public class User {
    private String parola;
    private String username;
    private Integer tip;
    private Integer id;


    public User()
    {

    }

    public User( String username,String parola,Integer tip)
    {
        this.parola=parola;
        this.username=username;
        this.tip=tip;
    }

    public User( Integer id,String username,String parola,Integer tip)
    {   this.id=id;
        this.parola=parola;
        this.username=username;
        this.tip=tip;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTip() {
        return tip;
    }

    public void setTip(Integer tip) {
        this.tip = tip;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(parola, user.parola) &&
                Objects.equals(username, user.username) &&
                Objects.equals(tip, user.tip) &&
                Objects.equals(id, user.id);
    }
}
