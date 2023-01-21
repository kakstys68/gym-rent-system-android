package com.example.androidrentsystem.model;

import lombok.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private int id;
    //private User user;
    private String title;
    private String commentText;
    //@OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Comment> replies;
    //@ManyToOne
    //private Comment parentComment;

    public Comment(String title, String commentText) {
        this.title = title;
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return title + ": " + commentText;
    }


}
