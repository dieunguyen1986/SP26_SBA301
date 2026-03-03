import React, { useEffect, useState } from "react";
import { Col, Container, Row } from "react-bootstrap";
import CourseFilter from "../components/CourseFilter";
import CourseList from "../components/CourseList";
import { useLoaderData } from "react-router-dom";
import HeroSection from "@/features/public-site/components/HeroSection";
import courseService from "../services/course.service";

const PublicCoursePage = () => {

  // Get data from loader
  const pageResponse = useLoaderData();

  // Declare state variables
  const [courses, setCourses] = useState(pageResponse.content);
  const [totalPages, setTotalPages] = useState(pageResponse.totalPages);
  const [currentPage, setCurrentPage] = useState(pageResponse.page);

  console.log(courses); 

  // Use useEffect to fetch data when currentPage changes
  useEffect(() => {
    const getAllCourseByPage = async () => {
      // Call API to get course by page
      const pageResponse = await courseService.findAll({page: currentPage});

      // Update state variables
      setCourses(pageResponse.content);
      setTotalPages(pageResponse.totalPages);
    };

    getAllCourseByPage();
  }, [currentPage]);


  // Render the component with HeroSection, CourseFilter, and CourseList
  return (
    <>
      <HeroSection />
      <section className="bg-light py-1">
        <Container fluid>
          <Row className="g-2 shadow-md">
            <Col md={2} xl={3}>
              <div className="bg-white p-4 rounded-3 h-100">
                <CourseFilter />
              </div>
            </Col>

            <Col md={11} xl={9}>
              <div className="bg-white p-4 rounded-3 shadow-md">
                <CourseList courses={courses} totalPages={totalPages} currentPage={currentPage} setCurrentPage={setCurrentPage} />
              </div>
            </Col>
          </Row>
        </Container>
      </section>
    </>
  );
};

export default PublicCoursePage;
