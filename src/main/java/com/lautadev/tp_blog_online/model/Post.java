package com.lautadev.tp_blog_online.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postings")
public class Post {
    private Long id;
    private String content;
    private LocalDate writeDate;
    @ManyToOne
    @JoinColumn(name = "fk_idAuthor")
    private Long idAuthor;
}
