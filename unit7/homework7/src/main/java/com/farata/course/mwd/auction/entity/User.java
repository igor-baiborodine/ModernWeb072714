package com.farata.course.mwd.auction.entity;

import com.google.common.base.MoreObjects;

import javax.json.Json;
import javax.json.JsonObject;
import javax.xml.bind.annotation.XmlTransient;

public class User {

    private int id;
    private String name;
    private String email;
    private boolean hasOverbidNotifications;

    public User(int id, String name, String email, boolean hasOverbidNotifications) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.hasOverbidNotifications = hasOverbidNotifications;
    }

    @XmlTransient
    public JsonObject getJsonObject() {

        return Json.createObjectBuilder()
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("hasOverbidNotifications", hasOverbidNotifications)
                .build();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isHasOverbidNotifications() {
        return hasOverbidNotifications;
    }

    public void setHasOverbidNotifications(boolean hasOverbidNotifications) {
        this.hasOverbidNotifications = hasOverbidNotifications;
    }

    @Override
    public boolean equals(final Object o) {

        if (this == o) return true;
        if (!(o instanceof User)) return false;

        final User user = (User) o;

        return id == user.id;

    }

    @Override
    public int hashCode() { return id; }

    @Override
    public String toString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("email", email)
                .add("hasOverbidNotifications", hasOverbidNotifications)
                .toString();
    }

    public String toShortString() {

        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

}
