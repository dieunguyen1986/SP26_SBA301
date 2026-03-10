import React from "react";
import { FiGrid, FiUsers, FiBookOpen } from "react-icons/fi";
import { FaUserCircle } from "react-icons/fa";

export const MenuItems = {

  ROLE_ADMIN: [
    { to: "/admin", label: "Dashboard", icon: <FiGrid /> },
    { to: "/admin/categories", label: "Category Management", icon: <FiUsers /> },
    { to: "/admin/courses", label: "Course Management", icon: <FiBookOpen /> },
    { to: "/admin/instructors", label: "Instructors", icon: <FaUserCircle /> },
    { to: "/admin/students", label: "Students", icon: <FiUsers /> }
  ],

  ROLE_INSTRUCTOR: [
    { to: "/instructor", label: "Dashboard", icon: <FiGrid /> },
    { to: "/instructor/courses", label: "My Courses", icon: <FiBookOpen /> },
    { to: "/instructor/students", label: "Students", icon: <FiUsers /> }
  ],

  ROLE_STUDENT: [
    { to: "/", label: "Dashboard", icon: <FiGrid /> },
    { to: "/courses", label: "Courses", icon: <FiBookOpen /> },
    { to: "/my-learning", label: "My Learning", icon: <FaUserCircle /> }
  ]

};


// Or menu items by permissions
export const MenuItems2 = [
  {
    to: "/admin/categories",
    label: "Category",
    permission: "CATEGORY_READ",
    icon: <FiUsers />,
  },
  {
    to: "/courses",
    label: "Courses",
    permission: "COURSE_READ",
    icon: <FiBookOpen />,
  },
];


// Filter menu theo user

// const { user } = useAuth();

// const filteredMenu = MenuItems.filter(item =>
//   user.permissions.includes(item.permission)
// );