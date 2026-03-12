import React, { useEffect, useState } from "react";
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Form,
  Pagination,
  Spinner
} from "react-bootstrap";
import { Link } from "react-router-dom";
import InstructorCourseTable from "../components/InstructorCourseTable";
import courseService from "../services/course.service";

const InstructorCourseListPage = () => {

  const [courses, setCourses] = useState([]);

  const [keyword, setKeyword] = useState("");
  const [status, setStatus] = useState("");

  const [page, setPage] = useState(0);
  const [size] = useState(10);

  const [totalPages, setTotalPages] = useState(0);

  const [loading, setLoading] = useState(false);

  const [searchTimeout, setSearchTimeout] = useState(null);

  const loadCourses = async () => {
    try {
      setLoading(true);

      const data = await courseService.getInstructorCourses({
        page,
        size,
        keyword,
        status
      });

      setCourses(data.content);
      setTotalPages(data.totalPages);

    } catch (error) {
      console.error("Cannot load instructor courses", error);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadCourses();
  }, [page, status]);

  // debounce search
  useEffect(() => {

    if (searchTimeout) {
      clearTimeout(searchTimeout);
    }

    const timeout = setTimeout(() => {

      setPage(0);
      loadCourses();

    }, 500);

    setSearchTimeout(timeout);

    return () => clearTimeout(timeout);

  }, [keyword]);

  const renderPagination = () => {

    let items = [];

    for (let number = 0; number < totalPages; number++) {
      items.push(
        <Pagination.Item
          key={number}
          active={number === page}
          onClick={() => setPage(number)}
        >
          {number + 1}
        </Pagination.Item>
      );

    }

    return <Pagination>{items}</Pagination>;

  };

  return (
    <Container fluid>

      <Row className="mb-4">

        <Col>

          <h2>My Courses</h2>

          <p className="text-muted">
            Manage and organize the courses you teach.
          </p>

        </Col>

        <Col className="text-end">

          <Button
            as={Link}
            to="/instructor/courses/create"
            variant="primary"
          >
            Create New Course
          </Button>

        </Col>

      </Row>

      <Card>

        <Card.Body>

          {/* Search & Filter */}

          <Row className="mb-3">

            <Col md={6}>

              <Form.Control
                type="text"
                placeholder="Search courses..."
                value={keyword}
                onChange={(e) => setKeyword(e.target.value)}
              />

            </Col>

            <Col md={3}>

              <Form.Select
                value={status}
                onChange={(e) => {
                  setStatus(e.target.value);
                  setPage(0);
                }}
              >

                <option value="">All Status</option>
                <option value="DRAFT">Draft</option>
                <option value="PUBLISHED">Published</option>
                <option value="ARCHIVED">Archived</option>

              </Form.Select>

            </Col>

          </Row>

          {/* Course Table */}

          {loading ? (

            <div className="text-center p-4">
              <Spinner animation="border" />
            </div>

          ) : (

            <InstructorCourseTable courses={courses} />

          )}

          {/* Pagination */}

          <div className="d-flex justify-content-center mt-3">

            {totalPages > 1 && renderPagination()}

          </div>

        </Card.Body>

      </Card>

    </Container>
  );
};

export default InstructorCourseListPage;