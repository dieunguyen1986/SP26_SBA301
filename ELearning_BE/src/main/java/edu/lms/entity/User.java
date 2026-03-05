package edu.lms.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(name = "full_name", columnDefinition = "VARCHAR(200)")
    private String fullName;

    private String major;

    private String phone;

    @Column(unique = true, name = "avatar_url", columnDefinition = "VARCHAR(1024)")
    private String avatarUrl;

    private String status;

    @Builder.Default
    @Column(name = "is_enabled")
    private Boolean isEnabled = true;

    @Builder.Default
    @Column(name = "is_locked")
    private Boolean isLocked = false;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @Column(name = "google_id")
    private String googleId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "UserRoles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> taughtCourses = new ArrayList<>();

}
