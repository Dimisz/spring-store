import { useTheme, useMediaQuery } from "@mui/material";
import { useEffect, useState } from "react";
import agent from "../../app/api/agent";
import Loader from "../../app/layout/Loader";
import { Order } from "../../app/models/order";
import OrdersTableDesktop from "./OrdersTableDesktop";
import OrdersTableMobile from "./OrdersTableMobile";

const Orders = () => {
  const [orders, setOrders] = useState<Order[]|null>(null);
  const [loading, setLoading] = useState(true);

  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));

  useEffect(() => {
    agent.Orders.list()
      .then(orders => setOrders(orders))
      .catch(error => console.log(error))
      .finally(() => setLoading(false))
  }, []);

  if(loading) return <Loader message='Loading orders..' />
  return(
    <>
      { greaterThanMd
        ?
        <OrdersTableDesktop orders={orders}/>
        :
        <OrdersTableMobile orders={orders}/>
      }
    </>
  )
}

export default Orders;