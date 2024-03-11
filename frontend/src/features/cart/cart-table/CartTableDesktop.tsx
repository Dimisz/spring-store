import { RemoveCircleOutline, AddCircleOutline, DeleteForeverOutlined } from "@mui/icons-material";
import { LoadingButton } from "@mui/lab";
import { TableContainer, Table, TableHead, TableRow, TableCell, TableBody, Box, Typography, TableFooter, Button } from "@mui/material";
import { Link } from "react-router-dom";
import CartSummary from "../CartSummary";
import { removeCartItemAsync, addCartItemAsync } from "../cartSlice";
import { useAppDispatch } from "../../../app/store/configureStore";
import { CartItem } from "../../../app/models/cart";
import { formatCurrency } from "../../../app/util/util";

interface Props {
  items: CartItem[],
  status?: string,
  subtotal: number;
  deliveryFee: number;
  isCart?: boolean;
  title?: string;
}
const CartTableDesktop = ({ items, status, subtotal, deliveryFee, isCart = true, title = 'Order Details' }: Props) => {
  const dispatch = useAppDispatch();
  
  return(
    <TableContainer sx={{ maxHeight: '90vh' }}>
          <Typography variant="h6" gutterBottom align='center'>
              {title}
          </Typography>
          <Table stickyHeader aria-label="products in the cart">
            <TableHead>
              <TableRow>
                <TableCell>Product</TableCell>
                <TableCell align="center">Price</TableCell>
                <TableCell align="center">Quantity</TableCell>
                <TableCell align="center">Subtotal</TableCell>
                { isCart && <TableCell align="center"></TableCell> }
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map((item) => (
                <TableRow key={item.productId} hover>
                  <TableCell scope="row">
                    <Box
                      display='flex'
                      alignItems='center'
                    >
                      <img
                        src={item.pictureUrl}
                        alt={item.name}
                        style={{
                          height: 50,
                          marginRight: 20
                        }}
                      />
                      {/* <span>{item.name}</span> */}
                      <Typography variant='h6'>{item.name}</Typography>
                    </Box>
                  </TableCell>
                  <TableCell align="center">${formatCurrency(item.price).toFixed(2)}</TableCell>
                  <TableCell align="center">
                    <Box display='flex' alignItems='center' justifyContent='center'>
                      { isCart &&
                        <LoadingButton 
                          color='primary' 
                          loading={status === `pendingRemoveItem${item.productId}rem`}
                          onClick={() => dispatch(removeCartItemAsync({
                            productId: item.productId,
                            quantity: 1,
                            name: 'rem'
                          }))}
                        >
                          <RemoveCircleOutline />
                        </LoadingButton>
                      }

                      <Typography variant='h6'>{item.quantity}</Typography>
                      
                      { isCart &&
                        <LoadingButton 
                          color='primary' 
                          loading={status === 'pendingAddItem' + item.productId}
                          onClick={() => dispatch(addCartItemAsync({productId: item.productId}))}
                        >
                          <AddCircleOutline/>
                        </LoadingButton>
                      }
                    </Box>
                  </TableCell>
                  <TableCell align="center">${formatCurrency(item.price * item.quantity).toFixed(2)}</TableCell>
                  { isCart &&
                    <TableCell align="center">
                      <LoadingButton 
                        color='error'
                        loading={status === `pendingRemoveItem${item.productId}del`} 
                        onClick={() => dispatch(removeCartItemAsync({
                          productId: item.productId, 
                          quantity: item.quantity,
                          name: "del"
                        }))}
                      >
                        <DeleteForeverOutlined />
                      </LoadingButton>
                    </TableCell>
                  }
                </TableRow>
              ))}
            </TableBody>
            <TableFooter>
              <CartSummary subtotal={subtotal} deliveryFee={deliveryFee}/>
            </TableFooter>
          </Table>
          { isCart &&
            <Button
              component={Link}
              to='/checkout'
              variant='contained'
              size='large'
              fullWidth
            >
              Checkout
            </Button>
          }
        </TableContainer>
  );
}

export default CartTableDesktop;