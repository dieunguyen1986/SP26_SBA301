package edu.lms.entity;

import edu.lms.enums.CourseLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Integer courseId;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String title;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String subtitle;

    @Column(name = "short_description", columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "course_level")
    @Enumerated(EnumType.STRING)
    private CourseLevel courseLevel;

    @Column(columnDefinition = "NVARCHAR(255)")
    private String language;

    @Column(name = "thumbnail_url", columnDefinition = "VARCHAR(1024)")
    private String thumbnailUrl;

    private Double rating;

    @Column(name = "rating_count")
    private Integer ratingCount;
    private Integer students;

    @Column(name = "total_hours")
    private Double totalHours;

    private Double price;

    private Double discount;

    private String status;

    @Column(name = "publish_at")
    private LocalDateTime publishAt;

    @Column(name="create_at")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Module> modules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id", referencedColumnName = "id", nullable = false)
    private User creator;
}
