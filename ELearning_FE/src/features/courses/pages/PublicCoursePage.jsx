import React, { useState } from "react";
import { Badge, Button, Col, Container, Form, Row } from "react-bootstrap";
import { StarFill } from "react-bootstrap-icons";
import CourseFilter from "../components/CourseFilter";
import CourseList from "../components/CourseList";
import { useLoaderData } from "react-router-dom";

const PublicCoursePage = () => {
  // const courses = useLoaderData();
  const [courses, setCourses] = useState(useLoaderData());
  // Call API

  console.log(courses);

  return (
    <section className="bg-light p-4 my-5">
      <Container>
        <Row className="gap-2">
          <Col md={3} xl={4} className="bg-white p-4 rounded-3">
            <CourseFilter />
          </Col>
          <Col md={9} xl={8} className="bg-white">
            <CourseList courses={courses} />
          </Col>
        </Row>
      </Container>
    </section>
  );
};

export default PublicCoursePage;
