package org.sanity.consoleForum.models;

public class Comment extends BaseModel {
    private String content;

    private User user;

    private Post post;

    public Comment() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return this.getId() + "," + this.getContent() + "," + this.getUser().getId() + "," + this.getPost().getId() + "," + this.getIsDeleted();
    }
}
