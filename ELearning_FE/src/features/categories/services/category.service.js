import axiosClient from "@/shared/services/axiosClient";


export const categoryService = {
  findAll: async (keyword) => {
    const response = await axiosClient.get(
      `/api/v1/categories`,
    );
    return response.data;
  },
  updateCategory: async (payload) => {
    const response = await axiosClient.post(`/api/v1/categories`, payload);
    return response.data;
  },
  findById: async (id) => {
    return await axiosClient.get(`/api/v1/categories/${id}`);
  },

  delete: async (id) => {},
};
