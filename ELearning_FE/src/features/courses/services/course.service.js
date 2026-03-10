import axiosClient from "@/shared/services/axiosClient";

const API_URL = "/api/v1/courses";

const courseService = {
  findAll: async ({ page = 1, size = 12 }) => {
    const response = await axiosClient.get(API_URL, {
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
    const { page, size, search, sort } = params;

    const response = await axiosClient.get("/instructor/courses", {
      params: {
        page,
        size,
        search,
        sort,
      },
    });

    return response.data;
  },
};

export default courseService;
