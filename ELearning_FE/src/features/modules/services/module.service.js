import axiosClient from "@/shared/services/axiosClient";

const INSTRUCTOR_API_URL = "/api/v1/me/courses";

const moduleService = {
  getModules: async (courseId, params) => {
    const res = await axiosClient.get(
      `${INSTRUCTOR_API_URL}/${courseId}/modules`,
      { params },
    );

    return res.data;
  },
};

export default moduleService;
