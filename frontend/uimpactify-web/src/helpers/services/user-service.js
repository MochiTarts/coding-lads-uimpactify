import axios from 'axios';

const apiUrl = 'placeholder';

export const signUp = (firstname, lastname, username, password) => {
    // return axios.post(`${apiUrl}/signup`,
    // {firstname, lastname, username, password});
    // MOCKING API CALL FOR NOW
    return Promise.resolve({});
}

export const signIn = (username, password) => {
    // return axios.post(`${apiUrl}/signin`,
    // {username, password});
    // MOCKING API CALL FOR NOW
    return Promise.resolve({});
}