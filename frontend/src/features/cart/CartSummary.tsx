import { Typography } from "@mui/material";
import TableFooterRow from "./TableFooterRow";
import { formatCurrency } from "../../app/util/util";

interface Props {
  subtotal: number;
  deliveryFee: number;
}

const CartSummary = ({subtotal, deliveryFee} : Props) => {
    return (
        <>
            <TableFooterRow>
              <>
                <Typography variant="h6">Subtotal</Typography>
                <Typography variant="h6">${formatCurrency(subtotal).toFixed(2)}</Typography>
              </>
            </TableFooterRow>
            <TableFooterRow>
              <>
                <Typography variant="h6">Delivery Fee*</Typography>
                <Typography variant="h6">${formatCurrency(deliveryFee).toFixed(2)}</Typography>
              </>
            </TableFooterRow>
            <TableFooterRow>
              <>
                <Typography variant="h6">Total</Typography>
                <Typography variant="h6">${formatCurrency(subtotal + deliveryFee).toFixed(2)}</Typography>
              </>
            </TableFooterRow>
            <TableFooterRow>
                <Typography style={{fontStyle: 'italic'}}>
                  *Orders over $100 qualify for free delivery
                </Typography>
            </TableFooterRow>
        </>
    )
}

export default CartSummary;