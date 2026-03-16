import axios from "axios";

const API_URL = "http://localhost:8080/api/photos";

export const getPhotos = () => {
  return axios.get(API_URL);
};