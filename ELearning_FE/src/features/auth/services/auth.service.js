import axiosClient from "@/shared/services/axiosClient";

const LOGIN_URL = "/api/v1/auth/login";
export const auth = {
  login: async (payload) => {
    const response = await axiosClient.post(LOGIN_URL, payload);
    return response.data;
  },
  
  logout: async () => {
    await axiosClient.post("/api/v1/auth/logout");
  },
  findByEmail: async (email) => {
    
  },
  register: (payload) => {

    const promise = fetch("http://localhost:3001/users", {
      method: "POST",
      body: JSON.stringify(payload),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    });

    return promise;
  },
  refreshToke: async () => {},
};
