import { TableContainer, Paper, Table, TableHead, TableRow, TableCell, TableBody, Button } from "@mui/material";
import { Order } from "../../app/models/order";
import { useState } from "react";
import OrderDetails from "./order-details/OrderDetails";
import { formatCurrency } from "../../app/util/util";


interface Props {
  orders: Order[] | null;
}

const OrdersTableDesktop = ({orders}: Props) => {
  const [selectedOrderId, setSelectedOrderId] = useState(0);
  if(selectedOrderId > 0){
    return(
      <OrderDetails
        order={orders?.find(o => o.id === selectedOrderId)!}
        setSelectedOrderId={setSelectedOrderId}
      />
    )
  }
  return(
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="list of orders" stickyHeader>
        <TableHead>
          <TableRow>
            <TableCell>Order No.</TableCell>
            <TableCell align="right">Total</TableCell>
            <TableCell align="right">Order Date</TableCell>
            <TableCell align="right">Order Status</TableCell>
            <TableCell align="right"></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {orders?.map((order) => (
            <TableRow
              key={order.id}
              sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
              hover
            >
              <TableCell component="th" scope="row">
                {order.id}
              </TableCell>
              <TableCell align="right">${formatCurrency(order.total).toFixed(2)}</TableCell>
              <TableCell align="right">{order.date.split('T')[0]}</TableCell>
              <TableCell align="right">{order.orderStatus}</TableCell>
              <TableCell align="right">
                <Button onClick={() => setSelectedOrderId(order.id)}>View</Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}

export default OrdersTableDesktop;