import ItemCount from "../ItemCount/ItemCount";
import "./ItemDetail.css";
import {cartContext} from '../../context/context';
import {useContext} from 'react';

function ItemDetail(props) {
  const {addItemCount} = useContext(cartContext);

  function onAdd (count){
    console.log("agregaste,", count )
    const item = {
      id: props.id,
      title: props.title,
      img: props.img,
      price: props.price,
      detail: props.detail,
      ml: props.ml,
      stock: props.stock,
      count: count,
    }
    addItemCount(item)
  }

  return (
    <div className="cardDetail">
      <div>
        <img className="cardImgDetail" src={props.img} alt="" />
      </div>
      <div className="cardDataDetail">
        <h2>{props.title}</h2>
        <p>{props.detail}</p>
        <p>{props.ml} ml</p>
        <h4>${props.price}</h4>
      </div>
      <ItemCount stock={props.stock} onAdd={onAdd} initial={1} />
    </div>
  );
}

export default ItemDetail;
