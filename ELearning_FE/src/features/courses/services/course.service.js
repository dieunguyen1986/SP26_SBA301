import axiosClient from "@/shared/services/axiosClient";

const API_URL = "/api/v1/courses";
const INSTRUCTOR_API_URL = "/api/v1/me/courses";

const courseService = {
  // Get all courses with pagination - public page
  findAll: async ({ page = 1, size = 12 }) => {
    const response = await axiosClient.get(API_URL, {
      // Nếu cần gửi token qua header - t.hợp dùng localstorage để lưu token sau khi đăng nhập
      // headers: {
      //   Authorization: `Bearer ${localStorage.getItem("token")}`,
      // },
      params: {
        page, 
        size,
      },
    });
    return response.data;
  },
  findById: async (courseId) => {
    const response = await axiosClient.get(`${API_URL}/${courseId}`);
    return response.data;
  },
  getInstructorCourses: async (params) => {
    const response = await axiosClient.get(INSTRUCTOR_API_URL, { params });

    return response.data;
  },
};

export default courseService;
