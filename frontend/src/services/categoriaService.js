import axiosBackend from "../api/axiosConfig";



const urlMap = "/categoria/"


export const getCategorias = async () => {
    try {
      const response = await axiosBackend.get(urlMap + 'list');
      return response.data;
    } catch (error) {
      throw error;
    }

};