import axios from 'axios';
const apiUrl = 'http://localhost:8080/uimpactify/users';

export const getAllInvoices = (userId) => {
    return axios.get(`${apiUrl}/allInvoices?userId=${userId}`);
}

export const payInvoice = (invoiceId) => {
    return axios.get(`${apiUrl}/payInvoice?invoiceId=${invoiceId}`);
}