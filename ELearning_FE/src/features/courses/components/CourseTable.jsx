import React from "react";

const CourseTable = ({ data, onSort, sort }) => {
  const handleSort = (field) => {
    if (sort === field) {
      onSort(`-${field}`); // desc
    } else {
      onSort(field); // asc
    }
  };

  return (
    <table className="table">
      <thead>
        <tr>
          <th onClick={() => handleSort("title")}>Title</th>
          <th>Subtitle</th>
          <th onClick={() => handleSort("price")}>Price</th>
          <th>Language</th>
          <th>Level</th>
          <th onClick={() => handleSort("totalHours")}>Duration (hrs)</th>
          <th>Status</th>
          <th>Description</th>
          <th onClick={() => handleSort("createdAt")}>Created Date</th>
          <th onClick={() => handleSort("publishAt")}>Published Date</th>
        </tr>
      </thead>

      <tbody>
        {data?.map((course) => (
          <tr key={course.courseId}>
            <td>{course.title}</td>
            <td>{course.subtitle}</td>
            <td>{course.price}</td>
            <td>{course.language}</td>
            <td>{course.courseLevel}</td>
            <td>{course.totalHours}</td>
            <td>{course.status}</td>
            <td>{course.description}</td>
            <td>{new Date(course.createdAt).toLocaleDateString()}</td>
            <td>
              {course.publishAt
                ? new Date(course.publishAt).toLocaleDateString()
                : "Not published"}
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default CourseTable;
