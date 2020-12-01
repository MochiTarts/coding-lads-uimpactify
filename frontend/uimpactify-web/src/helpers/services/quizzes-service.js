import axios from "axios";
const apiUrl = "http://localhost:8080/uimpactify";

export const createQuiz = (courseId, quizStartDate, quizEndDate, quizQuestions) => {
  return axios.post(`${apiUrl}/users/createQuiz`, {
    course: {id:courseId},
    quizStartDate,
    quizEndDate,
    quizQuestions,
  });
};
