import axios from "axios";
const apiUrl = "http://localhost:8080/uimpactify";

export const createQuiz = (name, cost, description, instructor_id) => {
  return axios.post(`${apiUrl}/users/createCourse`, {
    courseName: name,
    cost: cost,
    courseDesc: description,
    instructor: {
      id: instructor_id,
    },
  });
};
