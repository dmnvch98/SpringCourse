import React, {useState} from "react";
import {IProduct} from "../models";

interface ProductProps {
    product: IProduct;
}

export function Product({product}: ProductProps) {
    const [details, setDetails] = useState(false);

    return (
        <div>
            <img src={product.image} width={100} height={100}/>
            <p>{product.title}</p>
            <button onClick={() => setDetails(prevState => !prevState)}>
                {details ? 'Hide details' : 'Show details'}
            </button>
            {details && <div>
                <p>{product.description}</p>
            </div>}
        </div>
    )
}