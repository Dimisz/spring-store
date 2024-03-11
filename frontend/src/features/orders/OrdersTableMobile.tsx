import { TableContainer, Paper, Table, TableHead, TableRow, TableCell, TableBody, Button, Typography } from "@mui/material"
import { Order } from "../../app/models/order";
import React, { useState } from "react";
import OrderDetails from "./order-details/OrderDetails";
import { formatCurrency } from "../../app/util/util";

interface Props {
  orders: Order[] | null;
}

const OrdersTableMobile = ({orders}:Props) => {
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
    <TableContainer component={Paper} sx={{maxHeight: '90vh'}}>
      <Table stickyHeader aria-label="list of orders">
        <TableHead>
          <TableRow>
            <TableCell align='center' colSpan={4}>
              <Typography variant='h6'>Your Orders</Typography>
            </TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {orders?.map((order) => (
            <React.Fragment key={order.id}>
              <TableRow hover>
                <TableCell align='left'>
                  Order Number:
                </TableCell>
                <TableCell align='left'>
                  {order.id}
                </TableCell>
              </TableRow>
              <TableRow hover>
                <TableCell align="left">Total</TableCell>
                <TableCell align="left">${formatCurrency(order.total).toFixed(2)}</TableCell>
              </TableRow>
              <TableRow hover>
                <TableCell align="left">Date</TableCell>
                <TableCell align="left">{order.date.split('T')[0]}</TableCell>
              </TableRow>
              <TableRow hover>
                <TableCell align="left">Order Status</TableCell>
                <TableCell align="left">{order.orderStatus}</TableCell>
              </TableRow>
              <TableRow sx={{ mb: 5 }}> 
                  <TableCell colSpan={4}>
                    <Button 
                      variant='outlined' 
                      fullWidth 
                      onClick={() => setSelectedOrderId(order.id)}
                    >View</Button>
                  </TableCell>
              </TableRow>
            </React.Fragment>

          ))}
        </TableBody>
      </Table>
    </TableContainer>
  )
}

export default OrdersTableMobile;