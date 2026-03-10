import DashboardLayout from "../layouts/DashboardLayout";

export const instructorRoutes = [
    {path: "/instructor", element: <DashboardLayout />, 
        children: [
            {path: "courses", element: <>
                Course Instructor List
            </>},
        ]}


]