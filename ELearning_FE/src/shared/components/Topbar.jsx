import React from "react";
import { Navbar, Container, Nav, Form, InputGroup, Badge, Button, NavDropdown } from "react-bootstrap";
import { FiBell, FiMail, FiSearch, FiMenu } from "react-icons/fi";
import useAuth from "../hooks/useAuth";
import { auth } from "@/features/auth/services/auth.service";

export default function Topbar({ onToggleSidebar }) {
  const { userContext } = useAuth(); //useContext(AuthStatesContext)?.user;
  const { logout } = useAuth(); //useContext(AuthActionsContext);

   const handleLogout = async () => {
      try {
        // Clear user context
        await auth.logout();
  
        // Update context and localStorage
        // localStorage.removeItem("user");
        logout();
  
      } catch (error) {
        console.error("Logout failed:", error);
      }
    };

  return (
    <Navbar bg="white" className="border-bottom py-3">
      <Container fluid="lg" className="gap-2">
        <Button
          variant="outline-secondary"
          className="d-lg-none"
          onClick={onToggleSidebar}
          aria-label="Toggle sidebar"
        >
          <FiMenu />
        </Button>

        <InputGroup className="bg-light rounded-pill px-2" style={{ maxWidth: 520 }}>
          <InputGroup.Text className="bg-transparent border-0">
            <FiSearch />
          </InputGroup.Text>
          <Form.Control placeholder="Search ..." className="bg-transparent border-0 shadow-none" />
        </InputGroup>

        <Nav className="ms-auto align-items-center gap-2">
          <Nav.Link className="text-secondary" href="#" aria-label="Mail">
            <FiMail />
          </Nav.Link>

          <Nav.Link className="text-secondary position-relative" href="#" aria-label="Notifications">
            <FiBell />
            <Badge bg="danger" pill className="position-absolute top-0 start-100 translate-middle">
              3
            </Badge>
          </Nav.Link>

          <div className="d-flex align-items-center bg-light rounded-pill px-2 py-1 border">
            <div className="rounded-circle bg-primary text-white d-flex align-items-center justify-content-center"
                 style={{ width: 34, height: 34, fontWeight: 800 }}>
              H
            </div>

            <Nav variant="pills" activeKey="1" onSelect="">
              <NavDropdown title={userContext?.fullName || userContext?.email} id="nav-dropdown">
                <NavDropdown.Item onClick={() => {handleLogout()}} eventKey="4.1">
                  Logout
                </NavDropdown.Item>
              </NavDropdown>
            </Nav>

            {/* <span className="ms-2 d-none d-md-inline fw-semibold">{userContext?.fullName || userContext?.email}</span> */}
          </div>
        </Nav>
      </Container>
    </Navbar>
  );
}