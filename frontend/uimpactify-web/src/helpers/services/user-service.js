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

export const mountMyOpportunities = (uid) => {
    return axios.get(`${apiUrl}/getPostings/${uid}`);
}

 export const createPosting = (title, description, uid, type, socialInit) => {
    var date = new Date;
    var serializedDate = date;
    var creatorObj = {id: uid};
    var socialInitObj = {name: socialInit};
    
    return axios.post(`${apiUrl}/createPosting`, {
        name: title,
        postingDesc: description,
        postingCreator: creatorObj,
        postingType: type,
        postingDate: serializedDate,
        socialInit: socialInitObj
    });
}

export const updatePosting = (pid, title, description, uid, type, socialInit) => {
    var date = new Date;
    var serializedDate = date;
    var creatorObj = {id: uid};
    var socialInitObj = {name: socialInit};

    return axios.post(`${apiUrl}/updatePosting`, {
        id: pid,
        name: title,
        postingDesc: description,
        postingCreator: creatorObj,
        postingType: type,
        postingDate: serializedDate,
        socialInit: socialInitObj
    });
}

export const deletePosting = (pid) => {
    return axios.post(`${apiUrl}/deletePosting/${pid}`);
}

export const getPosting = (pid) => {
    return axios.post(`${apiUrl}/getPosting/${pid}`);
}

export const getUser = (uid) => {
    return axios.get(`${apiUrl}/getUser/${uid}`);
}