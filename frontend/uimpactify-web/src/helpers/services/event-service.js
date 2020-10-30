import axios from "axios";

const apiUrl = "http://localhost:8080/uimpactify/users";
export const createEvent = (eventName, eventDesc) => {
  const dateUTC = new Date(new Date().getTime()).toISOString();
  const authedUserInfo = JSON.parse(localStorage.getItem("authenticatedUser"));
  const authedUserID = authedUserInfo.id;
  const authedUserSocialInit = authedUserInfo.socialInit.name;
  const payload = {
    eventName: eventName,
    eventDesc: eventDesc,
    eventCreator: { id: authedUserID },
    eventDate: dateUTC,
    socialInit: { name: authedUserSocialInit },
  };
  return axios.post(`${apiUrl}/createEvent`, payload);
};

export const deleteEvent = (userId) => {
  return axios.post(`${apiUrl}/deleteEvent/${userId}`, {});
};

export const getEvent = (userId) => {
  return axios.get(`${apiUrl}/getEvent/${userId}`);
};

export const getEvents = (userId) => {
  return axios.get(`${apiUrl}/getEvents/${userId}`);
};
export const getEventsByDate = (userId) => {
  return axios.get(`${apiUrl}/getEventsByDate/${userId}`);
};
