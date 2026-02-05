import { LoginPage } from '@/features/auth/pages/LoginPage';
import PublicHomePage from '@/features/public-site/pages/PublicHomePage';
import React from 'react'
import PublicLayout from '../layouts/PublicLayout';
import PublicCoursePage from '@/features/courses/pages/PublicCoursePage';
import courseService from '@/features/courses/services/course.service';
import CourseDetail from '@/features/courses/pages/CourseDetail';
import Register from '@/features/auth/pages/Register';
import DashboardLayout from '../layouts/DashboardLayout';

const publicRoutes = [
    {path: "/login", element: <LoginPage />},
    {path: "/register", element: <Register />},
    {path: "/", element: <PublicLayout />,
        children: [
            {index: true, element: <PublicHomePage />},
            {path: "/courses", element: <PublicCoursePage />,
                loader: courseService.findAll,
                
            },
            {path: "/courses/:courseId", element: <CourseDetail />,}
            
        ]

    },
    {path: "*", element: <div>404 Not Found</div> },
];
export default publicRoutes;