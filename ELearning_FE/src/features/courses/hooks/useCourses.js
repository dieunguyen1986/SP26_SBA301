import { useQuery } from "@tanstack/react-query";
import { getInstructorCourses } from "../api/courseApi";

export const useCourses = (params) => {
  return useQuery({
    queryKey: ["instructor-courses", params],
    queryFn: () => getInstructorCourses(params),
    keepPreviousData: true,
  });
};