import ItemList from "./ItemList";
import "./ItemListContainer.css";
import {useParams} from 'react-router-dom';
import { getProduct, getProductCategory } from "../../services/firebase";
import { useEffect, useState } from "react";

function ItemListContainer (props){

    const [productList, setProductList] = useState([]);

    const {id} = useParams();

    useEffect(() => {
      console.log(id, "id");
        if (id === undefined) {
          console.log("cualquier cosa");
          getProduct().then((data) => {
            console.log(data, "data");
            setProductList(data);
          });
        } else {
          getProductCategory(id).then((data) => {
            setProductList(data);
          
          });
        }
      }, [id]);

    return(
        <>
        <h1 className="titleItemList">{props.greeting}</h1>
        <div className="itemListContainerStyle">
        <ItemList productList={productList}/>
        </div>
        </>
    );
}

export default ItemListContainer;
