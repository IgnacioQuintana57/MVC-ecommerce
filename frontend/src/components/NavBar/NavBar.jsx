import CartWidget from "./CartWidget/CartWidget";
import { Link } from "react-router-dom";
import "./NavBar.css";

function NavBar() {
  return (
    <>
      <nav className="navContainer">
        <div>
          <Link to="/">
            <img
              className="navLogo"
              src="https://st2.depositphotos.com/5829224/8642/v/450/depositphotos_86423788-stock-illustration-computer-logo.jpg"
              alt="logo de la tienda de informatica"
            />
          </Link>
        </div>
        <h1 className="navTitle">MVC INFORMÁTICA</h1>
        <ul className="linksContainer">
          <Link className="navLinks" to="/">
            PRODUCTOS
          </Link>
          <Link className="navLinks" to="/category/clasicos">
            COMPUTADORAS PREARMADAS
          </Link>
          <Link className="navLinks" to="/category/especialidad">
            NOTEBOOKS
          </Link>
          <Link className="navLinks" to="/category/otras">
            ACCESORIOS
          </Link>
        </ul>
        <div className="cartStyles">
          <Link to="/cart">
            <CartWidget />
          </Link>
        </div>
      </nav>
    </>
  );
}

export default NavBar;
