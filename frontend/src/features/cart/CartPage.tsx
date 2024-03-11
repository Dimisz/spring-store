import { useMediaQuery, useTheme } from "@mui/material";
import CartPageDesktop from "./CartPageDesktop";
import CartPageMobile from "./CartPageMobile";


const CartPage = () => {
  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));
  return(
    <>
      { greaterThanMd ? <CartPageDesktop /> : <CartPageMobile/>}
    </>
  );
                      
}

export default CartPage;