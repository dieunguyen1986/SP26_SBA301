import React, { useEffect, useState } from "react";
import { Alert, Button, Col, Form, Row, Table } from "react-bootstrap";
import { categoryService } from "../services/category.service";
import { CategoryDetailModal } from "../component/CategoryDetailModal";

const CategoryListPage = () => {
  const [categories, setCategories] = useState([]);
  const [keyword, setKeyword] = useState("");
  const [showDetailModal, setShowDetailModal] = useState(false);
  const [categoryEditable, setCategoryEditable] = useState({});

  // Call API
  useEffect(() => {
    const getAllCategories = async () => {
      try {
        const responseData = await categoryService.findAll();
        console.log("Categories: " + responseData);

        setCategories(responseData);
      } catch (error) {
        console.error(error?.message || "Has an error is occurred!");
      }
    };

    getAllCategories();
  }, []);

  const getAllCategories = async () => {
    try {
      const responseData = await categoryService.findAll();
      console.log("Categories: " + responseData);

      setCategories(responseData);
    } catch (error) {
      console.error(error?.message || "Has an error is occurred!");
    }
  };

  //  handle get detail category

  const getDetailCategory = async (id) => {
    try {
      const response = await categoryService.findById(id);
      setCategoryEditable(response.data);
    } catch (error) { }
  };

  const handleSubmit = async (formData) => {
    try {
      const responseData = await categoryService.updateCategory({
        ...formData,
        isActive: formData.active,
      });
      const message = responseData?.message;
      alert(message);
      getAllCategories();
      setShowDetailModal(false);
    } catch (error) {
      console.log(error);
    }
  };
  // UI
  return (
    <div className="mt-5 px-5">
      <div className="d-flex justify-content-between">
        <h3>Category List</h3>

        <Button
          variant="primary"
          className="rounded-2"
          style={{ width: "120px", color: "white" }}
          onClick={() => setShowDetailModal(true)}
        >
          Create New
        </Button>
      </div>

      <div className="pt-3">
        <Form>
          <Form.Group className="mb-3" controlId="keyword">
            <Form.Label className="fw-bold">Enter Search Data: </Form.Label>
            <Form.Control
              type="search"
              placeholder="Search (name, description)..."
              value={keyword}
              name="keyword"
              onChange={(e) => {
                setKeyword(e.target.value);
              }}
            />
          </Form.Group>
        </Form>
      </div>

      <Table striped bordered hover size="sm" className="mt-4">
        <thead>
          <tr>
            <th>#</th>
            <th>Category Name</th>
            <th>Description</th>
            <th>Parent</th>
            <th>Active?</th>
            <th>Update At</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {categories.length === 0 ? (
            <tr>
              <td colSpan={6}>
                <Alert variant="warning" className="mb-0">
                  No categories found.
                </Alert>
              </td>
            </tr>
          ) : (
            categories.map((c, index) => (
              <tr key={c.id}>
                <td>{index + 1}</td>
                <td>{c.categoryName}</td>
                <td>{c.description}</td>
                <td>{c.parentName}</td>
                <td>{c.active ? "Active" : "In-active"}</td>
                <td>{c.updateAt}</td>
                <td>
                  <Button
                    variant="warning"
                    className="rounded-3 mx-3"
                    style={{ width: "90px" }}
                    onClick={() => {
                      getDetailCategory(c.id);
                      setShowDetailModal(true)
                    }}
                  >
                    Edit
                  </Button>
                  <Button
                    variant="danger"
                    className="rounded-3"
                    style={{ width: "90px" }}
                  >
                    Delete
                  </Button>
                </td>
              </tr>
            ))
          )}
        </tbody>
      </Table>
      <CategoryDetailModal
        show={showDetailModal}
        handleClose={() => setShowDetailModal(false)}
        categories={categories}
        onSubmit={handleSubmit}
        categoryEditable={categoryEditable}
      />
    </div>
  );
};

export default CategoryListPage;
