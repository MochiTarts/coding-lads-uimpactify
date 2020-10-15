import axios from 'axios';

const apiUrl = 'placeholder';

export const signIn = (username, password) => {
    let credentials = {username, password};
    return axios.post(`${apiUrl}/signin`, 
    credentials);
}

export const signUp = (firstname, lastname, username, password) => {
    // return axios.post(`${apiUrl}/signup`,
    // {firstname, lastname, username, password});
    // MOCKING API CALL FOR NOW
    return Promise.resolve({});
}