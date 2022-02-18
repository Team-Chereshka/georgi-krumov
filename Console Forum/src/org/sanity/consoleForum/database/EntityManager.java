package org.sanity.consoleForum.database;

import org.sanity.consoleForum.io.FileReader;
import org.sanity.consoleForum.io.FileWriter;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.Comment;
import org.sanity.consoleForum.models.Post;
import org.sanity.consoleForum.models.PostRating;
import org.sanity.consoleForum.models.User;
import org.sanity.consoleForum.models.enums.PostRatingChoice;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Locale;

public class EntityManager {
    private List<User> users = new ArrayList<>();

    private List<Post> posts = new ArrayList<>();

    private List<Comment> comments = new ArrayList<>();

    private List<PostRating> ratings = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<PostRating> getRatings() {
        return ratings;
    }

    private void clearFiles() throws IOException {

        String dbDirectory = System.getProperty("user.dir") +
                "/org/sanity/consoleForum/db/";


        Files.delete(Path.of(dbDirectory + "users.db"));
        Files.delete(Path.of(dbDirectory + "posts.db"));
        Files.delete(Path.of(dbDirectory + "comments.db"));
        Files.delete(Path.of(dbDirectory + "ratings.db"));
    }

    private void initializeUsers(List<String> unparsedUsers) {

        for (String unparsedUser : unparsedUsers) {
            String[] tokens = unparsedUser.split(",");

            int id = Integer.parseInt(tokens[0]);
            String username = tokens[1];
            String pass = tokens[2];
            boolean isEnabled = Boolean.parseBoolean(tokens[3]);
            UserRole role = UserRole.valueOf(tokens[4].toUpperCase());
            boolean isDeleted = Boolean.parseBoolean(tokens[5]);

            if (isDeleted) {
                continue;
            }

            this.users.add(new User() {{
                setId(id);
                setUsername(username);
                setPassword(pass);
                setIsEnabled(isEnabled);
                setRole(role);
            }});
        }
    }

    private void initializePosts(List<String> unparsedPosts) {
        for (String unparsedPost : unparsedPosts) {
            String[] tokens = unparsedPost.split(",");

            int id = Integer.parseInt(tokens[0]);
            String title = tokens[1];
            String content = tokens[2];
            //absolutely no idea what I'm doing..might not work
            User postUser = users
                    .stream()
                    .filter(u -> u.getId() == Integer.parseInt(tokens[3]))
                    .findFirst().orElse(null);
            boolean isDeleted = Boolean.parseBoolean(tokens[4]);


            if (isDeleted) {
                continue;
            }

            Post postForDb = new Post() {{
                setId(id);
                setTitle(title);
                setContent(content);
                setUser(postUser);
            }};

            posts.add(postForDb);
            postUser.getPosts().add(postForDb);
        }
    }

    private void initializeComments(List<String> unparsedComments) {
        //Initialize Data from Unparsed Strings and add them to database lists
        for (String unparsedComment : unparsedComments) {
            String[] tokens = unparsedComment.split(",");

            //absolutely no idea what I'm doing..might not work...x2
            int id = Integer.parseInt(tokens[0]);
            String content = tokens[1];
            User commentUser = users
                    .stream()
                    .filter(u -> u.getId() == Integer.parseInt(tokens[2]))
                    .findFirst().orElse(null);

            Post commentPost = posts
                    .stream()
                    .filter(p -> p.getId() == Integer.parseInt(tokens[3]))
                    .findFirst().orElse(null);
            boolean isDeleted = Boolean.parseBoolean(tokens[4]);


            if (isDeleted) {
                continue;
            }


            Comment commentForDb = new Comment() {{
                setId(id);
                setContent(content);
                setUser(commentUser);
                setPost(commentPost);
            }};

            comments.add(commentForDb);

            commentUser.getComments().add(commentForDb);
            commentPost.getComments().add(commentForDb);
        }

    }

    private void initializeRatings(List<String> unparsedRatings) {
        // Initialize Data from Unparsed Strings and add them to database lists

        for (String unparsedRating : unparsedRatings) {

            String[] tokens = unparsedRating.split(",");

            int id = Integer.parseInt(tokens[0]);
            PostRatingChoice choice = PostRatingChoice.valueOf(tokens[1].toUpperCase());
            User ratingUser = users
                    .stream()
                    .filter(u -> u.getId() == Integer.parseInt(tokens[2]))
                    .findFirst().orElse(null);

            Post ratingPost = posts
                    .stream()
                    .filter(p -> p.getId() == Integer.parseInt(tokens[3]))
                    .findFirst().orElse(null);

            boolean isDeleted = Boolean.parseBoolean(tokens[4]);

            if (isDeleted) {
                continue;
            }

            PostRating postRatingForDb = new PostRating(choice) {{
                setId(id);
                setUser(ratingUser);
                setPost(ratingPost);
            }};

            ratings.add(postRatingForDb);
            ratingUser.getRatings().add(postRatingForDb);
            ratingPost.getRatings().add(postRatingForDb);

        }
    }


    public User add(User user) {
        // Initialize entity id and add to database
        user.setId(this.users.size());
        this.users.add(user);
        return user;
    }

    public  Post add(Post post) {
        // Initialize entity id and add to database
        post.setId(this.posts.size());
        this.posts.add(post);
        return post;
    }

    public Comment add(Comment comment) {
        // Initialize entity id and add to database
        comment.setId(this.comments.size());
        this.comments.add(comment);
        return comment;
    }

    public PostRating add(PostRating postRating) {
        // Initialize entity id and add to database
        postRating.setId(this.ratings.size());
        this.ratings.add(postRating);
        return postRating;
    }

    public void initialize() {
        try {
            // READ DATA FROM
            List<String> unparsedUsers = new ArrayList<>();
            List<String> unparsedPosts = new ArrayList<>();
            List<String> unparsedComments = new ArrayList<>();
            List<String> unparsedRatings = new ArrayList<>();

            String dbDirectory = System.getProperty("user.dir") + "/org/sanity/consoleForum/db/";

            InputReader usersReader = new FileReader(dbDirectory + "users.db");
            InputReader postsReader = new FileReader(dbDirectory + "posts.db");
            InputReader commentsReader = new FileReader(dbDirectory + "comments.db");
            InputReader ratingsReader = new FileReader(dbDirectory + "ratings.db");


            String line = null;

            // READ FROM FILES WITH INPUT READERS

            while ((line = usersReader.readLine()) != null) {
                unparsedUsers.add(line);
            }
            while ((line = postsReader.readLine()) != null) {
                unparsedPosts.add(line);
            }

            while ((line = commentsReader.readLine()) != null) {
                unparsedComments.add(line);
            }

            while ((line = ratingsReader.readLine()) != null) {
                unparsedRatings.add(line);
            }

            // ADD LINES TO LISTS OF STRINGS

            initializeUsers(unparsedUsers);
            initializePosts(unparsedPosts);
            initializeComments(unparsedComments);
            initializeRatings(unparsedRatings);
        } catch (Exception ignored) {
        }
    }

    public void store() throws IOException {
        // STORE DATA TO FILES. SAME FILES FROM WHICH YOU READ THE DATA
        String dbDirectory = System.getProperty("user.dir") + "/org/sanity/consoleForum/db/";


        this.clearFiles();


        if (!Files.exists(Path.of(dbDirectory))) {
            Files.createDirectory(
                    Path.of(dbDirectory));
        }
        if (!Files.exists(Path.of(dbDirectory + "users.db"))) {
            Files.createFile(
                    Path.of(dbDirectory + "users.db"));

        }
        if (!Files.exists(Path.of(dbDirectory + "posts.db"))) {
            Files.createFile(
                    Path.of(dbDirectory + "posts.db"));
        }
        if (!Files.exists(Path.of(dbDirectory + "comments.db"))) {
            Files.createFile(
                    Path.of(dbDirectory + "comments.db"));
        }
        if (!Files.exists(Path.of(dbDirectory + "ratings.db"))) {
            Files.createFile(
                    Path.of(dbDirectory + "ratings.db"));
        }


        OutputWriter usersWriter = new FileWriter(dbDirectory + "users.db",
                StandardOpenOption.APPEND);

        OutputWriter postsWriter = new FileWriter(dbDirectory + "posts.db",
                StandardOpenOption.APPEND);

        OutputWriter commentsWriter = new FileWriter(dbDirectory + "comments.db",
                StandardOpenOption.APPEND);

        OutputWriter ratingsWriter = new FileWriter(dbDirectory + "ratings.db",
                StandardOpenOption.APPEND);


        for (User u : users) {
            //put users in file
            usersWriter.write(u);
        }

        for (Post post : posts) {
            postsWriter.write(post);
        }

        for (Comment c : comments) {
            commentsWriter.write(c);
        }

        for (PostRating r : ratings) {
            ratingsWriter.write(r);
        }
    }
}
