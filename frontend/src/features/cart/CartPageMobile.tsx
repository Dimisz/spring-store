import { Typography } from '@mui/material';

import { useAppSelector } from '../../app/store/configureStore';
import CartTableMobile from './cart-table/CartTableMobile';

interface Props {
  isCart?: boolean;
}

const CartPageMobile = ({ isCart = true }: Props) => {
  const { cart, status } = useAppSelector((state) => state.cart);
  if (!cart || cart.items.length <= 0)
    return <Typography variant="h3">Your cart is empty</Typography>;

  const subtotal =
    cart?.items.reduce((sum, item) => sum + item.price * item.quantity, 0) || 0;
  const deliveryFee = subtotal > 0 && subtotal <= 100 ? 5 : 0;

  return (
    <>
      <CartTableMobile
        items={cart.items}
        status={status}
        subtotal={subtotal}
        deliveryFee={deliveryFee}
        isCart={isCart}
      />
    </>
  );
};

export default CartPageMobile;
