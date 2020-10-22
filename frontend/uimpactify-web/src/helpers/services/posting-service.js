import axios from 'axios';
const apiUrl = 'http://localhost:8080/uimpactify/postings';

export const getAllPosting = () => {
    return axios.get(`${apiUrl}/getAllPostings`);
}