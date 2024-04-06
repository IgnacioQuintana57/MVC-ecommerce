import CartWidget from './CartWidget/CartWidget'
import { Link } from 'react-router-dom';
import "./NavBar.css"

function NavBar (){

    return(
        <>
        <nav className='navContainer'>
            <div>
                <Link to="/"><img className='navLogo' src="https://i.fbcd.co/products/resized/resized-750-500/66bf70495aaf1bd998f73ac92ca931bb4dbe132a6e926435a4bbcf7f72161689.webp" alt="logo del coffee shop" /></Link>
            </div>
            <h1 className='navTitle'>Coffee Shop</h1>
            <ul className='linksContainer'>
                <Link className= "navLinks" to="/">Inicio</Link>
                <Link className= "navLinks" to="/category/clasicos">Café clásico</Link>
                <Link className= "navLinks" to="/category/especialidad">Café de especialidad</Link>
                <Link className= "navLinks" to="/category/otras">Otras bebidas</Link>
            </ul>
            <div className='cartStyles'>
                <Link to="/cart">
                <CartWidget/>
                </Link>
            </div>
        </nav>
        </>
        
    );
}

export default NavBar;