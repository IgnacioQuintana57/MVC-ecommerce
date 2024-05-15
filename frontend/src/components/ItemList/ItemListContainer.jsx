// import ItemList from "./ItemList";
// import "./ItemListContainer.css";
// import { useParams } from "react-router-dom";
// //import { getProduct, getProductCategory } from "../../services/firebase";
// import { useEffect, useState } from "react";
// import getProducts from "../../mockAPI/mockAPI";
// import Card from "../Card/Card";

// function ItemListContainer(props) {
//   const [productList, setProductList] = useState([]);

//   useEffect(() => {
//     getProducts().then((productos) => {
//       setProductList(productos);
//     });
//   }, []);

//   const { id } = useParams();

//   useEffect(() => {
//     if (id === undefined) {
//       getProduct().then((data) => {
//         setProductList(data);
//       });
//     } else {
//       getProductCategory(id).then((data) => {
//         setProductList(data);
//       });
//     }
//   }, [id]);

//   // return (
//   //   <>
//   //     <h1 className="titleItemList">{props.greeting}</h1>
//   //     <div className="itemListContainerStyle">
//   //       <ItemList productList={productList} />
//   //     </div>
//   //   </>
//   // );

//   return (
//     <>
//       <h1 className="titleItemList">{props.greeting}</h1>
//       <div className="itemListContainerStyle">
//         {productList.map((producto) => {
//           return (
//             <Card
//               img={producto.img}
//               titulo={producto.titulo}
//               precio={producto.precio}
//             />
//           );
//         })}
//       </div>
//     </>
//   );
// }

// export default ItemListContainer;

import ItemList from "./ItemList";
import "./ItemListContainer.css";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import getProducts from "../../mockAPI/mockAPI";

function ItemListContainer(props) {
  const [productList, setProductList] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    // Simulación de carga de productos desde un mock API
    getProducts().then((productos) => {
      if (id) {
        // Si hay una categoría específica, filtra los productos por esa categoría
        const filteredProducts = productos.filter(
          (producto) => producto.categoria === id
        );
        setProductList(filteredProducts);
      } else {
        // Si no hay una categoría específica, muestra todos los productos
        setProductList(productos);
      }
    });
  }, [id]);

  return (
    <>
      <h1 className="titleItemList">{props.greeting}</h1>
      <div className="itemListContainerStyle">
        <ItemList productList={productList} />
      </div>
    </>
  );
}

export default ItemListContainer;
