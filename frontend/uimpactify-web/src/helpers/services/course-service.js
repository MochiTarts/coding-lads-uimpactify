import axios from 'axios';
const apiUrl = 'http://localhost:8080/uimpactify/users';

export const createCourse = (name, cost, description, instructor_id) => {
    return axios.post(`${apiUrl}/createCourse`, {
        courseName: name,
        cost: cost,
        courseDesc: description,
        instructor: {
            id: instructor_id
        }
    });
}