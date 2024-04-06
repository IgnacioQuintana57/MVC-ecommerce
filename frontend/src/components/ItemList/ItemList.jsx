import { useState, useEffect } from "react";
import Card from "../Card/Card";
import './/ItemList.css';

function ItemList (info){
    
        const [drinkList, setDrinkList] = useState([]);
        
        useEffect(
            () => {

               if (info?.productList){
                setDrinkList(info?.productList);
               }
 
            },[info]
        )

        return(
            <>
            <div className="itemListStyle">
                {drinkList?.length && (drinkList?.map( (product, index)=>{
                    return (<Card key={index} id={product?.id} img={product?.img} title={product?.title} price={product?.price} />)
                    }))
                }
            </div> 
            </>
        );
}

export default ItemList;