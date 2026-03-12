import React from "react";
import { Table, Spinner } from "react-bootstrap";
import InstructorCourseRow from "./InstructorCourseRow";

const InstructorCourseTable = ({ courses, loading }) => {

  if (loading) {
    return <Spinner animation="border" />;
  }

  if (!courses || courses.length === 0) {
    return <p>No courses found.</p>;
  }

  return (
    <Table striped hover responsive>

      <thead>

        <tr>
          <th>Course</th>
          <th>Status</th>
          <th>Students</th>
          <th>Rating</th>
          <th>Price</th>
          <th>Publish Date</th>
          <th>Actions</th>
        </tr>

      </thead>

      <tbody>

        {courses.map((course) => (
          <InstructorCourseRow
            key={course.courseId}
            course={course}
          />
        ))}

      </tbody>

    </Table>
  );
};

export default InstructorCourseTable;