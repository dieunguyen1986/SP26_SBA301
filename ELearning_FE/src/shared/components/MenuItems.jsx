import React from "react";
import { FiBookOpen, FiGrid, FiUsers } from "react-icons/fi";

export const MenuItems = {
  Admin: [
    { to: "/", label: "Dashboard", icon: <FiGrid />, badge: 5, end: true },
    { to: "/admin/categories", label: "Category", icon: <FiUsers />, badge: 5 },
    { to: "/courses", label: "Courses", icon: <FiBookOpen />, badge: 14 },
    { to: "/students", label: "Students", icon: <FiUsers />, badge: 50 },
  ],
  Instructor: [
    { to: "/courses", label: "Courses", icon: <FiBookOpen />, badge: 14 },
    { to: "/students", label: "Students", icon: <FiUsers />, badge: 50 },
  ],
};
