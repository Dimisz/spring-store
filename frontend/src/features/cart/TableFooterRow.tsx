import { TableRow, TableCell } from "@mui/material"
import { ReactElement } from "react";

interface Props {
  children: ReactElement
}

const TableFooterRow = ({ children }: Props) => {
  return(
    <TableRow 
      hover 
      sx={{'&:last-child td, &:last-child th': { border: 0 }}}
    >
        <TableCell sx={{ display: 'flex', justifyContent: 'space-between'}}>
          {children}
        </TableCell>
    </TableRow>
  );
}

export default TableFooterRow;