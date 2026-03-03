import React, {useEffect } from "react";
import { Card, Badge, Nav } from "react-bootstrap";
import { formatVND } from "../utils/formatters";
import { Star, StarFill, StarHalf } from "react-bootstrap-icons";
import { Link, NavLink } from "react-router-dom";

const CourseCard = ({ course }) => {
  const [image, setImage] = React.useState(null);
  const [error, setError] = React.useState(null);

  useEffect(() => {
    const loadImage = async () => {
      const renderImage = new Promise((resolve, reject) => {
        const img = new Image();
        img.src = course.thumbnailUrl;
        img.onload = () => resolve(img.src);
        img.onerror = (err) => reject(err.message);
      });

      renderImage
        .then((img) => {
          setImage(img);
          console.log("Image loaded:", img);
        })
        .catch((err) => {
          setError(err || "Failed to load image");

          console.error("Error loading image: " + err);
        });
    };

    loadImage();
  }, []);

  return (
    <Card
      className="shadow-sm border-1 rounded-4 overflow-hidden my-3"
      style={{ width: "100%", height: 430 }}
    >
      {/* Image + badge overlay */}
      <div className="position-relative">
        <Card.Img
          src={image}
          alt={error}
          style={{ height: 170, objectFit: "cover" }}
        />

        <Badge
          bg="primary"
          className="position-absolute rounded-circle d-flex flex-column justify-content-center align-items-center text-center fw-bold"
          style={{
            width: 72,
            height: 72,
            right: 14,
            bottom: -18,
            lineHeight: 1.05,
          }}
        >
          BEST
          <br />
          SELLER
        </Badge>
      </div>

      <Card.Body className="p-3">
        <Card.Title className="fw-bold fs-6 mb-2">
          <Nav.Link
            as={Link}
            to={`/courses/${course.courseId}`}
            className="text-primary text-decoration-none p-0 m-0"
          >
            {course.title}
          </Nav.Link>
          {/* <NavLink
            to={`/courses/${course.id}`}
            className="text-decoration-none text-primary"
          >
            {course.title}
          </NavLink> */}
        </Card.Title>

        <div className="d-flex justify-content-between align-items-end">
          <div>
            <div className="text-secondary small mb-2">
              {course?.instructor?.name}
            </div>

            <div className="d-flex align-items-center gap-2">
              <span className="fw-bold text-danger">{course.rating}</span>
              {course.rating < 1 && <Star className="text-warning" />}
              {course.rating >= 1 && course.rating < 2 && (
                <>
                  <StarFill className="text-warning" />
                  <Star className="text-warning" />
                </>
              )}
              {course.rating >= 1.5 && course.rating < 2 && (
                <>
                  <StarFill className="text-warning" />
                  <StarHalf className="text-warning" />
                  <Star className="text-warning" />
                </>
              )}
              {course.rating >= 2 && course.rating < 3 && (
                <>
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <Star className="text-warning" />
                </>
              )}
              {course.rating >= 2.5 && course.rating < 3 && (
                <>
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <StarHalf className="text-warning" />
                  <Star className="text-warning" />
                </>
              )}
              {course.rating >= 3 && course.rating < 4 && (
                <>
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <Star className="text-warning" />
                </>
              )}
              {course.rating >= 4 && course.rating < 5 && (
                <>
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <StarFill className="text-warning" />
                  <StarHalf className="text-warning" />
                </>
              )}
            </div>
          </div>

          <div className="fw-bold fs-6">{formatVND(course.price.current)}</div>
        </div>

        <hr className="my-3 opacity-25" />

        <div className="d-flex justify-content-between text-secondary fw-semibold small">
          <div className="d-flex align-items-center gap-2">
            <span style={{ color: "#5b5bff" }}>📘</span>
            <span>12 classes</span>
          </div>

          <div className="d-flex align-items-center gap-2">
            <span style={{ color: "#5b5bff" }}>👥</span>
            <span>{course.students}</span>
          </div>
        </div>
      </Card.Body>
    </Card>
  );
};

export default CourseCard;
