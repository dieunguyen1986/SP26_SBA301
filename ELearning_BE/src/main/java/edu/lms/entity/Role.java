package edu.lms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true, nullable = false)
        private String name; // // ROLE_ADMIN, ROLE_INSTRUCTOR, ROLE_STUDENT

        private String description;

        @ManyToMany(mappedBy = "roles")
        private Set<User> users = new HashSet<>();
}
