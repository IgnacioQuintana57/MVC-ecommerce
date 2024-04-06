import { useState } from 'react';
import { Link } from 'react-router-dom';
import './/ItemCount.css';

export default function ItemCount({stock, onAdd}) {

    const [verCarrito, setVerCarrito] = useState(null)

    const [count, setCount] = useState (1);

    function quitar() {
     if(count > 1)   setCount(count - 1);
        
    }

    
    function agregar() {   
     if(count < stock)   setCount(count + 1);
        
    }

    const onAddCart = () => {
        onAdd(count);
        setVerCarrito(true);
    }


    return (
    !verCarrito ?
    <div className='count'>
        <div className='counter'>
            <h3>Elige la cantidad</h3>
            <br />
            <button onClick={()=>quitar()}>-</button>
            <div>{count}</div>
            <button onClick={()=>agregar()}>+</button>
        </div>
        <button className='btnCartCount' onClick={onAddCart}>Agregar al carrito</button>
    </div> :

    <div className='count'>
        <div className='counter'>Compralo ya</div>
        <Link to="/cart">
        <button className='btnCartCount'>Ver Carrito</button>
        </Link>
    </div>
      
    )
}