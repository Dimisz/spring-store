import { Button, useMediaQuery, useTheme } from "@mui/material";
import { CartItem } from "../../../app/models/cart";
import { Order } from "../../../app/models/order";
import CartTableDesktop from "../../cart/cart-table/CartTableDesktop";
import CartTableMobile from "../../cart/cart-table/CartTableMobile";

interface Props {
  order: Order;
  setSelectedOrderId: (id: number) => void;
}

const OrderDetails = ({order, setSelectedOrderId}: Props) => {
  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));
  return(
    <>
    {
      greaterThanMd
      ?
      <CartTableDesktop 
        items={order.orderItems as CartItem[]} 
        subtotal={order.subtotal} 
        deliveryFee={order.deliveryFee}  
        isCart={false}
        title={`Order #${order.id} (${order.date.split('T')[0]}) - ${order.orderStatus}`}
      />
      :
      <CartTableMobile
        items={order.orderItems as CartItem[]} 
        subtotal={order.subtotal} 
        deliveryFee={order.deliveryFee}  
        isCart={false}
        title={`Order #${order.id} (${order.date.split('T')[0]}) - ${order.orderStatus}`}
      />
    }
    <Button 
      fullWidth
      variant='outlined'
      onClick={() => setSelectedOrderId(0)}
    >Back to orders</Button>
    </>
    );
}

export default OrderDetails;