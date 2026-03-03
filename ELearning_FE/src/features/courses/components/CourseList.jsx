import CourseCard from "@/shared/components/CourseCard";
import CustomPagination from "@/shared/components/CustomPagination";
import React from "react";
import { Col, Pagination, Row } from "react-bootstrap";

const CourseList = (props) => {
  const courses = props.courses;
  const totalPages = props.totalPages;
  let currentPage = props.currentPage;

  console.log("Course List" + courses);

  return (
    <>
      <Row className="mt-3">
        {courses.map((course) => (
          <Col key={course.id} md={3}>
            <CourseCard course={course} />
          </Col>
        ))}
      </Row>

     <CustomPagination totalPages={totalPages} currentPage={currentPage} onPageChange={(pageNumber) => props.setCurrentPage(pageNumber)} />
    </>
  );
};

export default CourseList;
