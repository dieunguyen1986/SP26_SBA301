import React, { useState } from "react";
import { useCourses } from "../hooks/useCourses";
import CourseTable from "../components/CourseTable";

const InstructorCourseList = () => {
  const [page, setPage] = useState(0);
  const [size] = useState(5);
  const [search, setSearch] = useState("");
  const [sort, setSort] = useState("createdAt");

  const { data, isLoading } = useCourses({
    page,
    size,
    search,
    sort,
  });

  const courses = data?.data || [];
  const totalPages = data?.meta?.totalPages || 0;

  const handleSearch = (e) => {
    setSearch(e.target.value);
    setPage(0);
  };

  return (
    <div>
      <h2>My Courses</h2>

      {/* Search */}
      <input
        type="text"
        placeholder="Search course..."
        value={search}
        onChange={handleSearch}
        style={{ marginBottom: "10px" }}
      />

      {/* Table */}
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <CourseTable data={courses} onSort={setSort} sort={sort} />
      )}

      {/* Pagination */}
      <div style={{ marginTop: "10px" }}>
        <button
          disabled={page === 0}
          onClick={() => setPage((prev) => prev - 1)}
        >
          Prev
        </button>

        <span style={{ margin: "0 10px" }}>
          Page {page + 1} / {totalPages}
        </span>

        <button
          disabled={page + 1 >= totalPages}
          onClick={() => setPage((prev) => prev + 1)}
        >
          Next
        </button>
      </div>
    </div>
  );
};

export default InstructorCourseList;
