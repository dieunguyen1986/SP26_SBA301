import React from "react";
import { Badge, Button, Image } from "react-bootstrap";
import { Link } from "react-router-dom";

const InstructorCourseRow = ({ course }) => {

  const renderStatus = () => {
    switch (course.status) {
      case "PUBLISHED":
        return <Badge bg="success">Published</Badge>;
      case "DRAFT":
        return <Badge bg="secondary">Draft</Badge>;
      default:
        return <Badge bg="dark">{course.status}</Badge>;
    }
  };

  return (
    <tr>

      <td>

        <div className="d-flex align-items-center">

          <Image
            src={course.thumbnailUrl}
            width={80}
            rounded
            className="me-3"
          />

          <div>

            <strong>{course.title}</strong>

            <div className="text-muted small">
              {course.subtitle}
            </div>

          </div>

        </div>

      </td>

      <td>{renderStatus()}</td>

      <td>{course.students}</td>

      <td>
        ⭐ {course.rating} ({course.ratingCount})
      </td>

      <td>${course.price}</td>

      <td>
        {course.publishAt
          ? new Date(course.publishAt).toLocaleDateString()
          : "-"}
      </td>

      <td>

        <Button
          as={Link}
          to={`/instructor/courses/${course.courseId}/edit`}
          size="sm"
          variant="outline-primary"
          className="me-2"
        >
          Edit
        </Button>

        <Button
          as={Link}
          to={`/instructor/courses/${course.courseId}/modules`}
          size="sm"
          variant="outline-info"
        >
          Content
        </Button>

      </td>

    </tr>
  );
};

export default InstructorCourseRow;