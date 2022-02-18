package org.sanity.consoleForum.models;

import java.util.ArrayList;
import java.util.List;

public class Post extends BaseModel {
    private String title;

    private String content;

    private User user;

    private List<Comment> comments;

    private List<PostRating> ratings;

    public Post() {
        this.comments = new ArrayList<Comment>();
        this.ratings = new ArrayList<PostRating>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return this.getId() + ","  + this.getTitle() + "," + this.getContent() + "," + this.getUser().getId() + "," + this.getIsDeleted();
    }
}
