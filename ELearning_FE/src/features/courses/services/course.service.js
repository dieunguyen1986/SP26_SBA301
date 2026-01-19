import axiosClient from "@/shared/services/axiosClient";


const courseService = {
    findAll: async () => {
        const response = await axiosClient.get("/courses");
        return response.data;
    },
};

export default courseService;