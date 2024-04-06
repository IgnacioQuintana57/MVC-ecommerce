import React from 'react';
import './/Form.css';
import { cartContext } from '../../context/context';
import { useContext, useState, useEffect } from 'react';
import {createCompra} from '../../services/firebase';
import { Link } from 'react-router-dom';


const Form = () => {
  const [nombre, setNombre] = useState("");
  const [apellido, setApellido] = useState("");
  const [mail, setMail] = useState("");
  const [celular, setCelular] = useState("");
  const [direccion, setDireccion] = useState("");
  const {cart, deleteCart, precioTotal} = useContext(cartContext);
  const [mensajeError, setMensajeError] = useState(null);
  const [numOrder, setNumOrder] = useState("")
  const [createOrder, setCreateOrder] = useState(null);

  useEffect(() => {
    if(createOrder === true){
      
      let compras = [...cart]
      compras = {
      buyer: { nombre, apellido, mail, celular, direccion },
      items: compras,
      date: new Date(),
      total: precioTotal(),
    }
      
    createCompra(compras)
      .then((data) => {
        setNumOrder([data]);
        deleteCart();
      })
      .catch((error) => {
        setMensajeError(error.message);
      });
    }
  }, [createOrder]);
   

  function onNombreChange (event){
    setNombre(event.target.value);
  }

  function onApellidoChange (event){
    setApellido(event.target.value);
  }

  function onMailChange (event){
    setMail(event.target.value);
  }

  function onCelularChange (event){
    setCelular(event.target.value);
  }

  function onDireccionChange (event){
    setDireccion(event.target.value);
  }


  function onComprar(){
    setCreateOrder(true) 
  }


  
  if(cart.length !== 0 || numOrder !== ""){
    return (
      mensajeError ? (
        <p>{mensajeError}</p>
      ) :
      <div>
        
        {
          numOrder !== "" ? 
          <div className='container'>
            <h1>¡Gracias por tu compra!</h1>
            <br />
            <p>Tu número de compra es:</p>
            <h3>{numOrder}</h3>
          </div>:
          
          <div className='containerForm'>
          <h2>Tus datos:</h2>
  
          <div>
            <p className='dataType'>Nombre</p>
            <input className='dataForm' type="text" onChange={event => onNombreChange(event)} />
          </div>
  
          <div>
            <p className='dataType'>Apellido</p>
            <input className='dataForm' type="text" onChange={event => onApellidoChange(event)} />
          </div>
  
          <div>
            <p className='dataType'>E-mail</p>
            <input className='dataForm' type="mail" onChange={event => onMailChange(event)} />
          </div>
  
          <div>
            <p className='dataType'>Número de teléfono</p>
            <input className='dataForm' type="tel" onChange={event => onCelularChange(event)} />
          </div>
  
          <div>
            <p className='dataType'>Dirección</p>
            <input className='dataForm' type="text" onChange={event => onDireccionChange(event)} />
          </div>
  
  
            <button className='btnComprarForm'
            disabled={!(nombre !== "" && apellido !== "" && mail !== "" && celular !== "" && direccion !=="")}
            onClick={event => onComprar(event)}>Comprar</button>
          
        </div>
        }
      </div>
    )

  }else{
    return(
      <>
      <div className='container'>
        <h1>¡Ops! todavía no tenés nada en tu carrito</h1>
      <Link to ="/"><button>Empezá a comprar</button></Link>
      </div>
      
      </>
    )

  }
}

export default Form;