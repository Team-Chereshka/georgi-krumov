package org.sanity.consoleForum.models;

import org.sanity.consoleForum.models.enums.UserRole;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseModel {
    private String username;

    private String password;

    private boolean isEnabled;

    private UserRole role;

    private List<Post> posts;

    private List<Comment> comments;

    private List<PostRating> ratings;

    public User() {
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.ratings = new ArrayList<>();
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

    public boolean getIsEnabled() {
        return isEnabled;
    }

    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<PostRating> getRatings() {
        return ratings;
    }

    public void setRatings(List<PostRating> ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString() {
        return this.getId() + "," +
                this.getUsername() + "," +
                this.getPassword() + "," +
                this.getIsEnabled() + "," +
                this.getRole().toString() + "," +
                this.getIsDeleted();
    }
}

