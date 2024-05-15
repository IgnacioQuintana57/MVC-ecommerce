import NavBar from "./components/NavBar/NavBar";
import Cart from "./components/Cart/Cart";
import Form from "./components/Forms/Form";
import ItemListContainer from "./components/ItemList/ItemListContainer";
import ItemDetailContainer from "./components/ItemDetail/ItemDetailContainer";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import CartContextProvider from "./context/context";

function App() {
  return (
    <CartContextProvider>
      <BrowserRouter>
        <div>
          <NavBar />
          <Routes>
            <Route
              path="/"
              element={<ItemListContainer greeting="Nuestros Productos" />}
            />
            <Route
              path="/category/:id"
              element={<ItemListContainer greeting="Nuestros Productos" />}
            />
            <Route path="/item/:id" element={<ItemDetailContainer />} />
            <Route path="/cart" element={<Cart />} />
            <Route path="/checkout" element={<Form />} />
          </Routes>
        </div>
      </BrowserRouter>
    </CartContextProvider>
  );
}

export default App;
