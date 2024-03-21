package com.example.ManagerLib.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_book")
    @JsonIgnore
    private Book book;

//    @Column(name = "id_book", insertable = false, updatable = false) // Chỉ định không cần thực hiện insert hoặc update trường này
//    private Long idBook;

    @Column(name = "image_url")
    @JsonProperty("image_url")
    private String imageUrl;

}
