package org.sanity.consoleForum.models;

import org.sanity.consoleForum.models.enums.PostRatingChoice;

public class PostRating extends BaseModel {
    private User user;

    private Post post;

    private boolean isPositive;

    private boolean isNegative;

    public PostRating(PostRatingChoice ratingChoice)
    {
        this.isPositive = ratingChoice == PostRatingChoice.POSITIVE;
        this.isNegative = ratingChoice == PostRatingChoice.NEGATIVE;
    }

    public void toggle()
    {
        this.isPositive = !this.isPositive;
        this.isNegative = !this.isNegative;
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

    public boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(boolean positive) {
        isPositive = positive;
    }

    public boolean getIsNegative() {
        return isNegative;
    }

    public void setIsNegative(boolean negative) {
        isNegative = negative;
    }

    @Override
    public String toString()
    {
        return this.getId() + "," + (this.getIsPositive() ? PostRatingChoice.POSITIVE.toString() : PostRatingChoice.NEGATIVE.toString()) + "," + this.getUser().getId() + "," + this.getPost().getId() + "," + this.getIsDeleted();
    }
}
