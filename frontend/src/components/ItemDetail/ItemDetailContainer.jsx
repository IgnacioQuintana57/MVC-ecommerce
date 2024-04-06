import ItemDetail from './ItemDetail';
import { useState, useEffect } from 'react';
import {getDetailProduct} from '../../services/firebase';
import {useParams} from 'react-router-dom';
import './/ItemDetailContainer.css';

function ItemDetailContainer() {

    const [cafe, setCafe] = useState ([]);
    const {id} = useParams();
    const {img, title, detail, ml, price, stock} = cafe;

    useEffect(() => {
        getDetailProduct(id).then((data) => {
            setCafe(data);
        });
    }, [id]);

    return(
        <div className='container'>
            <ItemDetail
                id= {id}
                img= {img}
                title= {title}
                detail= {detail}
                ml= {ml}
                price= {price}
                stock= {stock}
            />
        </div>
    )
}


export default ItemDetailContainer;