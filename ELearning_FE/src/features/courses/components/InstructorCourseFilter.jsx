import React from "react";
import { Row, Col, Form } from "react-bootstrap";

const InstructorCourseFilter = ({
  keyword,
  setKeyword,
  status,
  setStatus
}) => {

  return (
    <Row className="mb-3">

      <Col md={6}>
        <Form.Control
          type="text"
          placeholder="Search course..."
          value={keyword}
          onChange={(e) => setKeyword(e.target.value)}
        />
      </Col>

      <Col md={3}>
        <Form.Select
          value={status}
          onChange={(e) => setStatus(e.target.value)}
        >
          <option value="ALL">All Status</option>
          <option value="DRAFT">Draft</option>
          <option value="PUBLISHED">Published</option>
          <option value="ARCHIVED">Archived</option>
        </Form.Select>
      </Col>

    </Row>
  );
};

export default InstructorCourseFilter;