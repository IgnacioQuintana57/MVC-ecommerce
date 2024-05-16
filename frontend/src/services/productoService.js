import axiosBackend from "../api/axiosConfig";

export const getListProductos = async () => {
    try {
      const response = await axiosBackend.get('/producto/list');
      return response.data;
    } catch (error) {
      throw error;
    }

};

export const getProductosPorFiltro = async (idCategorias, idSubCategorias) => {
    try {
      const response = await axiosBackend.get('/producto/getProductosPorFiltro', {queryParam: {
            idCategorias,
            idSubCategorias
      }});
      return response.data;
    } catch (error) {
      throw error;
    }
  };

