import { DeleteForeverOutlined, RemoveCircleOutline, AddCircleOutline } from "@mui/icons-material";
import { LoadingButton } from "@mui/lab";
import { TableContainer, Paper, Table, TableHead, TableRow, TableCell, Typography, TableBody, Card, CardMedia, Box, CardContent, Divider, TableFooter, Button } from "@mui/material";
import { Link } from "react-router-dom";
import { CartItem } from "../../../app/models/cart";
import { useAppDispatch } from "../../../app/store/configureStore";
import CartSummary from "../CartSummary";
import { removeCartItemAsync, addCartItemAsync } from "../cartSlice";
import { formatCurrency } from "../../../app/util/util";

interface Props {
  items: CartItem[],
  status?: string,
  subtotal: number;
  deliveryFee: number;
  isCart?: boolean;
  title?: string;
}

const CartTableMobile = ({ items, status, subtotal, deliveryFee, isCart = true, title='Order Details' }: Props) => {
  const dispatch = useAppDispatch();
  return(
    <TableContainer
          component={Paper}
          sx={{
            maxHeight: '90vh',
            width: '100%',
            display: 'flex',
            flexDirection: 'column'
            }}
        >
          <Table stickyHeader aria-label="products in the cart">
            <TableHead>
              <TableRow>
                <TableCell align='left'>
                  {
                    isCart 
                    ?
                    <Typography variant='h6'>{title}</Typography>
                    :
                    <Typography variant='h6' align='left'>{title}</Typography>
                  }
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {items.map((item) => (
                <TableRow
                  hover
                  key={item.productId}
                  // sx={{
                  //    '&:last-child td, &:last-child th': { border: 0 },
                  //    }}
                >
                  <TableCell align='left'>
                  <Card sx={{ display: 'flex' }}>
                    <CardMedia
                        component="img"
                        sx={{ width: {xs : 90, sm: 100} }}
                        image={item.pictureUrl}
                        alt={item.name}
                      />
                    <Box sx={{
                      padding: 1,
                      paddingTop: 0,
                      display: 'flex',
                      flexDirection: 'column',
                      flexGrow: 1,
                      alignItems: 'space-between',
                      alignContent: 'space-between'
                    }}>
                      <CardContent sx={{
                          p: 0,
                          // pl: 1,
                          display: 'flex',
                          flexDirection: 'column',
                          marginBottom: 3
                        }}
                      >
                        <Box
                          display='flex'
                          justifyContent='space-between'
                        >
                          <Box
                            display='flex'
                            flexDirection='column'
                            alignItems='start'
                            paddingTop={1}
                          >
                            <Typography
                              component="h2"
                              sx={{
                                fontSize: { xs: '1.2rem', sm: '2rem' },
                                fontWeight: 500,
                                lineHeight: 1.1,
                              }}>
                              {item.name}
                            </Typography>
                            <Typography
                              color="text.secondary"
                              component="h3"
                              sx={{ fontSize: { xs: '0.9rem', sm: '1.8rem', lineHeight: 1.1 }}}
                            >
                              ${formatCurrency(item.price).toFixed(2)}
                            </Typography>
                          </Box>
                          { isCart &&
                            <Box>
                              <LoadingButton
                                loading={status === `pendingRemoveItem${item.productId}del`} 
                                onClick={() => dispatch(removeCartItemAsync({
                                  productId: item.productId, 
                                  quantity: item.quantity,
                                  name: "del"
                                }))}
                              >
                                <DeleteForeverOutlined/>
                              </LoadingButton>
                            </Box>
                          }
                        </Box>
                      </CardContent>
                      <Divider />
                      <Box
                          display='flex'
                          justifyContent='space-between'
                          alignItems='center'
                          paddingTop={1}
                        >
                          <Box
                            display='flex'
                            flexDirection={{ xs: 'column', sm: 'row' }}
                            alignItems='center'
                          >
                              <Typography
                                variant='caption'
                                color='secondary'
                                sx={{ fontStyle: 'italic' }}
                              >Subtotal:</Typography>
                              <Typography variant='body1' ml={{ sm: 2 }}>
                                ${formatCurrency(item.price * item.quantity).toFixed(2)}
                              </Typography>
                          </Box>
                          
                          <Box sx={{ display: 'flex', alignItems: 'center'}}>
                          { isCart &&
                            <LoadingButton
                                loading={status === `pendingRemoveItem${item.productId}rem`}
                                onClick={() => dispatch(removeCartItemAsync({
                                  productId: item.productId,
                                  quantity: 1,
                                  name: 'rem'
                                }))}
                                color='primary'>
                                <RemoveCircleOutline />
                              </LoadingButton>
                            }
                              <Typography variant='h6'>{item.quantity}</Typography>
                            { isCart &&
                              <LoadingButton
                                loading={status === 'pendingAddItem' + item.productId}
                                onClick={() => dispatch(addCartItemAsync({productId: item.productId}))}
                                color='primary'
                              >
                                <AddCircleOutline/>
                              </LoadingButton>
                            }
                            </Box>  
                        </Box>
                    </Box>
                    </Card>
                  </TableCell>
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

export default CartTableMobile;