import { Paper, Typography } from "@mui/material";

import { useAppSelector } from "../../app/store/configureStore";
import CartTableDesktop from "./cart-table/CartTableDesktop";

interface Props {
  isCart?: boolean;
}

const CartPageDesktop = ({isCart = true}: Props) => {
  const { cart, status } = useAppSelector((state) => state.cart);
  

  if(!cart || cart.items.length <= 0) return <Typography variant='h3'>Your cart is empty</Typography>;

  const subtotal = cart?.items.reduce((sum, item) => sum + item.price * item.quantity, 0) || 0;
  const deliveryFee = subtotal > 0 && subtotal <= 10000 ? 500 : 0;
  
  return(
    <Paper sx={{ width: '100%', overflow: 'hidden', display: 'flex' }}>
        <CartTableDesktop 
          title='Order Details'
          items={cart.items} 
          status={status} 
          subtotal={subtotal} 
          deliveryFee={deliveryFee} 
          isCart={isCart}       
        />
    </Paper>
  );
}

export default CartPageDesktop;