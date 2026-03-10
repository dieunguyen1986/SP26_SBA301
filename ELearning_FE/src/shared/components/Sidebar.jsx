import React from "react";
import { NavLink } from "react-router-dom";
import { Badge, Button, Image } from "react-bootstrap";
import logo from "@/assets/logo.svg";
import { MenuItems } from "./MenuItems";
import useAuth from "../hooks/useAuth";

export default function Sidebar({ open, onClose }) {
  const {userContext} = useAuth(); //useContext(AuthStatesContext);

  const roles = userContext?.roles || []; // ["ROLE_INSTRUCTOR", "ROLE_ADMIN"]

  console.log("User roles in sidebar:", roles);

  if (roles.length === 0) return null;

  let role = null;
  if (roles.includes("ROLE_ADMIN")) {
    role = "ROLE_ADMIN";
  } else if (roles.includes("ROLE_INSTRUCTOR")) {
    role = "ROLE_INSTRUCTOR";
  } else if (roles.includes("ROLE_SUPPORT")) {
    role = "ROLE_SUPPORT";
  }

  const menuItems = MenuItems[role] || [];

  return (
    <>
      {/* overlay for mobile */}
      <div
        className={`sidebar-overlay d-lg-none ${open ? "show" : ""}`}
        onClick={onClose}
      />

      <aside className={`sidebar bg-white border-end ${open ? "open" : ""}`}>
        <div className="p-3 border-bottom">
          <div className="d-flex justify-content-center">
            <Image src={logo} style={{ width: "70%" }} />
          </div>

          <div className="d-flex gap-2 align-items-center mt-3 p-2 rounded-3 bg-light border">
            <div
              className="rounded-circle bg-primary-subtle text-primary d-flex align-items-center justify-content-center"
              style={{ width: 44, height: 44, fontWeight: 800 }}
            >
              H
            </div>
            <div>
              <div className="fw-bold">{userContext?.fullName || userContext?.email}</div>
              <div className="text-muted small">{role=== "ROLE_ADMIN" ? "Administrator" : role=== "ROLE_INSTRUCTOR" ? "Instructor" : "Support"}  </div>
            </div>
          </div>
        </div>

        <nav className="p-2">
          {menuItems.map((it) => (
            <NavLink
              key={it.to}
              to={it.to}
              end={it.end}
              onClick={onClose}
              className={({ isActive }) =>
                [
                  "d-flex align-items-center justify-content-between",
                  "text-decoration-none",
                  "px-3 py-2 rounded-3",
                  "mb-1",
                  isActive
                    ? "bg-primary text-white"
                    : "text-secondary hover-bg",
                ].join(" ")
              }
            >
              <span className="d-flex align-items-center gap-2 fw-semibold">
                <span style={{ width: 22, display: "inline-flex" }}>
                  {it.icon}
                </span>
                {it.label}
              </span>

              {typeof it.badge !== "undefined" && (
                <Badge
                  bg={it.to === "/" ? "light" : "secondary"}
                  text={it.to === "/" ? "dark" : "light"}
                  pill
                >
                  {it.badge}
                </Badge>
              )}
            </NavLink>
          ))}
        </nav>

        <div className="mt-auto p-3 border-top">
          <Button className="w-100">Update To Pro</Button>
        </div>
      </aside>
    </>
  );
}
