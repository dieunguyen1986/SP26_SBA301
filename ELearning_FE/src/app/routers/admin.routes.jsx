import CategoryListPage from "@/features/categories/page/CategoryListPage";
import DashboardLayout from "../layouts/DashboardLayout";

export const adminRoutes = [
    {path: "/admin", element: <DashboardLayout />, 
        children: [
            {path: "categories", element: <CategoryListPage />},
        ]}


]