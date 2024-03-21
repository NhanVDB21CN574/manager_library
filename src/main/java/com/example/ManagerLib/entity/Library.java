package com.example.ManagerLib.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "libraries")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name_lib")
    private String name;

    @OneToMany(mappedBy = "library",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonProperty
    private List<Book> listBooks;

}
