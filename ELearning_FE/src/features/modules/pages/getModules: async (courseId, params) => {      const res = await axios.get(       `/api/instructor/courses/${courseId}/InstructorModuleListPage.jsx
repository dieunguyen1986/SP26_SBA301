import moduleService from "@/features/modules/services/module.service";
import React, { useEffect, useState } from "react";
import { Table, Button, Form, Row, Col, Pagination } from "react-bootstrap";
import { useParams } from "react-router-dom";

const InstructorModuleListPage = () => {
  const { courseId } = useParams();

  const [modules, setModules] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [page, setPage] = useState(1);
  const [totalPages, setTotalPages] = useState(1);

  const loadModules = async () => {
    console.log("Loading modules for courseId:", courseId, "with keyword:", keyword, "and page:", page);
    const res = await moduleService.getModules(courseId, {
      page,
      size: 10,
      keyword,
    });

    setModules(res.content);
    setTotalPages(res.totalPages);
  };

  useEffect(() => {
    const fetchModules = async () => {
      await loadModules();
    };
    fetchModules();
  }, [page]);

  const handleSearch = (e) => {
    e.preventDefault();
    setPage(1);
    loadModules();
  };

  return (
    <div className="mx-3">
      <h3 className="mb-4">Modules</h3>

      <Form onSubmit={handleSearch} className="mb-3">
        <Row>
          <Col md={4}>
            <Form.Control
              placeholder="Search module..."
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
            />
          </Col>

          <Col>
            <Button type="submit">Search</Button>
          </Col>
        </Row>
      </Form>

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>Title</th>
            <th>Lessons</th>
            <th>Status</th>
            <th>Sort</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>
          {modules.map((m, index) => (
            <tr key={m.moduleId}>
              <td>{index + 1}</td>

              <td>{m.title}</td>

              <td>{m.lessonCount}</td>

              <td>{m.status}</td>

              <td>{m.sortOrder}</td>

              <td>
                <Button size="sm" variant="primary">
                  Edit
                </Button>{" "}
                <Button size="sm" variant="secondary">
                  Lessons
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Pagination>
        {[...Array(totalPages)].map((_, i) => (
          <Pagination.Item
            key={i}
            active={page === i + 1}
            onClick={() => setPage(i + 1)}
          >
            {i + 1}
          </Pagination.Item>
        ))}
      </Pagination>
    </div>
  );
};

export default InstructorModuleListPage;
