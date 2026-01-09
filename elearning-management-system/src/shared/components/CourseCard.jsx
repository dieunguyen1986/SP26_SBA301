import React from "react";
import './CourseCard.css';

export const CourseCard = ({ course }) => {
    const renderStars = (rating) => {
        const fullStars = Math.floor(rating);
        const hasHalfStar = rating - fullStars >= 0.5;
        const emptyStars = 5 - fullStars - (hasHalfStar ? 1 : 0);

        return (
            <>
                {[...Array(fullStars)].map((_, i) => (
                    <i key={`full-${i}`} className="bi bi-star-fill text-warning"></i>
                ))}

                {hasHalfStar && <i className="bi bi-star-half text-warning"></i>}

                {[...Array(emptyStars)].map((_, i) => (
                    <i key={`empty-${i}`} className="bi bi-star text-warning"></i>
                ))}
            </>
        );
    };

    return (
        <div className="card h-100 border-0 shadow-sm course-card">
            <div className="position-relative">
                <img
                    src={course.image}
                    alt={course.title}
                    className="card-img-top"
                />

                <span className="badge rounded-circle bg-primary d-flex align-items-center justify-content-centertext-white course-badge">
                    {course.badge}
                </span>

            </div>

            <div className="card-body d-flex flex-column ">
                <h5 className="fw-bold course-title">{course.title}</h5>

                <p className="mb-2">{course.author}</p>

                <div className="d-flex justify-content-between align-items-center mb-3">
                    <div className="d-flex align-items-center gap-1">
                        <span className="fw-bold text-danger">
                            {course.rating}
                        </span>
                        {renderStars(course.rating)}
                    </div>
                    <span className="fw-bold fs-5">${course.price}</span>
                </div>

                <hr className="mt-auto" />

                <div className="d-flex justify-content-between text-muted small">
                    <div className="d-flex align-items-center gap-1">
                        📘 <span>{course.classes} classes</span>
                    </div>
                    <div className="d-flex align-items-center gap-1">
                        👥 <span>{course.students} students</span>
                    </div>
                </div>
            </div>
        </div>
    );
}