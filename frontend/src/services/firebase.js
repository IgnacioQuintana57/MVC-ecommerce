// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getFirestore, collection, getDocs, doc, getDoc, query, where, addDoc } from "firebase/firestore";

// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyC8qGY2c9WtRmwZTnN6wClY1-ZeNIN4r4g",
  authDomain: "reactcoder-38cd9.firebaseapp.com",
  projectId: "reactcoder-38cd9",
  storageBucket: "reactcoder-38cd9.appspot.com",
  messagingSenderId: "45989746744",
  appId: "1:45989746744:web:eb851b70ec09918047aecd"
};

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig);

const db = getFirestore(firebaseApp);

export async function createCompra(compras) {
    const collectionRef = collection(db, "compras")
    let result = await addDoc(collectionRef, compras)
    if(result.id.length !== 0){
        return result.id
    }else{
        throw new Error("No se creo la compra, intentalo de nuevo.")
    }
}

export async function getProduct() {
    const collectionRef = collection(db, "productosCoffeeShop");
    let resultado = await getDocs(collectionRef);
    let dataCoffeeShop = resultado.docs.map(document => {
        return {
            id: document.id,
            ...document.data()
        }
    })
    return dataCoffeeShop;
}


  export async function getDetailProduct(id) {
    const docRef = doc(db, "productosCoffeeShop", id);
    const docResultado = await getDoc(docRef);
    if(docResultado.exists()){
        return {
            id: docResultado.id,
            ...docResultado.data()
        }

    } else{
        throw new Error("¡¡¡Este producto no existe!!!")
    }
  }

export async function getProductCategory(id) {
    const collectionRef = collection(db, "productosCoffeeShop");
    const queryCategory = query(collectionRef, where("category", "==", id));
    let resultado = await getDocs(queryCategory);
    if(resultado.docs.length !== 0){
        let dataCoffeeShop = resultado.docs.map(document => {
            return {
                id: document.id,
                ...document.data()
            }
        });
        return dataCoffeeShop;

    } else{
        throw new Error("¡¡¡Esta categoría no existe!!!") 
    }
}



export default firebaseApp;