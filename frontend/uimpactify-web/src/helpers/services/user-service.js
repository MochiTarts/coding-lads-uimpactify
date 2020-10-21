import axios from 'axios';
const apiUrl = 'http://localhost:8080/uimpactify/users';

export const signUp = (firstName, lastName, username, password, age, role, socialInit) => {
    var roleObj;
    if(role === "impact_learner" || role === "impact_consultant") {
        roleObj = {name: role};
    }
    var socialInitObj;
    if(socialInit) {
        socialInitObj = {name: socialInit};
    }
    return axios.post(`${apiUrl}/signup`,
    {firstName, lastName, username, password, age, role: roleObj, socialInit: socialInitObj});
    // MOCKING API CALL FOR NOW
    return Promise.resolve({});
}

export const signIn = (username, password) => {
    return axios.post(`${apiUrl}/login`,
    {username, password});
}