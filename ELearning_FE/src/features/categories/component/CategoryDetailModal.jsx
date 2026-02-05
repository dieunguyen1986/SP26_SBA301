import React, { useEffect, useState } from "react";
import { Button, Form, FormGroup, Modal } from "react-bootstrap";

export const CategoryDetailModal = ({
  show,
  handleClose,
  onSubmit,
  categories,
  categoryEditable
}) => {
  const [category, setCategory] = useState({
    id:"",
    categoryName:"",
    description:"",
    sortOrder:0,
    parentId: "",
    active:true
  });
  

  useEffect(()=>{
    console.log(categoryEditable)
    setCategory({
       ...categoryEditable,

    })
  },[categoryEditable])

  

  const handleChange = (e) => {
    const {name, value, type, checked} = e.target;
    setCategory({
        ...category,
        [name]: type === "checkbox" ? checked : value
    }
    )
  }

  return (
    <Modal size="lg" show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>Category Detail</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <Form>
          <Form.Group className="mb-3"  controlId="id">
            <Form.Control type="hidden" value={category.id} />
          </Form.Group>
          <Form.Group className="mb-3" controlId="categoryName">
            <Form.Label>Category Name</Form.Label>
            <Form.Control name="categoryName" type="text" value={category.categoryName} onChange={handleChange} />
          </Form.Group>
          <Form.Group className="mb-3" controlId="description">
            <Form.Label>Desciption</Form.Label>
            <Form.Control as="textarea" rows={3} name="description" value={category.description} onChange={handleChange} />
          </Form.Group>
          <Form.Group className="mb-3" controlId="sortOrder">
            <Form.Label>Sort Order</Form.Label>
            <Form.Control type="number" name="sortOrder" value={category.sortOrder} onChange={handleChange} />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>Parent Category</Form.Label>
            <Form.Select aria-label="Default select example" name="parentId" value={category.parentId} onChange={handleChange}>
              <option value="">Open this select menu</option>
              {categories.map((cat) => (
                <option key={cat?.id} value={cat?.id}>
                  {cat?.categoryName}
                </option>
              ))}
            </Form.Select>
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Check // prettier-ignore
              type="checkbox"
              checked = {category.active}
              label="Acitve"
              name="active"
              onChange={handleChange}
            />
          </Form.Group>
        </Form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        <Button variant="primary" onClick={() => onSubmit(category)}>
          Save Changes
        </Button>
      </Modal.Footer>
    </Modal>
  );
};
