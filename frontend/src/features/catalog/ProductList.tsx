import { Product } from "../../app/models/product";

import { Grid } from "@mui/material";
import ProductCard from "./ProductCard";
import { useAppSelector } from "../../app/store/configureStore";
import ProductPlaceholder from "./product-placeholder/ProductPlaceholder";

interface Props {
  products: Product[];
}

const ProductList = ({ products }: Props) => {
  const { productsLoaded } = useAppSelector((state) => state.catalog);

  const renderedProducts = products.map((product) => {
    return(
      <Grid item xs={12} sm={6} md={6} lg={4} key={product.id}>
        { !productsLoaded
          ?
          <ProductPlaceholder />
          :
          <ProductCard key={product.id} product={product} />
        }
        
      </Grid>
    );
  });

  return(
    <Grid container spacing={4} sx={{ mt: { xs: 0, md: 1} }}>
        {renderedProducts}
    </Grid>
  );
}

export default ProductList;