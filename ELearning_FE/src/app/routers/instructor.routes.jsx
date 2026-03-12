import InstructorCourseListPage from "@/features/courses/pages/InstructorCourseListPage";
import DashboardLayout from "../layouts/DashboardLayout";
import InstructorModuleListPage from "@/features/modules/pages/getModules: async (courseId, params) => {      const res = await axios.get(       `/api/instructor/courses/${courseId}/InstructorModuleListPage";

export const instructorRoutes = [
  {
    path: "/instructor",
    element: <DashboardLayout />,
    children: [
      { index: true, element: <InstructorCourseListPage /> },
      { path: "courses", element: <InstructorCourseListPage /> },
      { path: "courses/:courseId/modules", element: <InstructorModuleListPage /> },
    ],
  },
];
