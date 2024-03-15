import {
  Avatar,
  Button,
  Menu,
  MenuItem,
  // useMediaQuery, useTheme
} from '@mui/material';
import React from 'react';
import { useAppDispatch, useAppSelector } from '../../store/configureStore';
import { signOut } from '../../../features/account/accountSlice';
import { clearCart } from '../../../features/cart/cartSlice';
import { Link } from 'react-router-dom';

const SignedInMenu = () => {
  const dispatch = useAppDispatch();
  const { user } = useAppSelector((state) => state.account);
  // console.log('user in the menu');
  // console.log(user);

  // const theme = useTheme();
  // const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));

  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);
  const handleClick = (event: any) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <>
      <Button onClick={handleClick} color="inherit" sx={{ typography: 'h6' }}>
        {/* { 
          greaterThanMd
          ?
          user?.email
          : */}
        <Avatar sx={{ bgcolor: 'primary' }}>
          {user?.email[0] ? user?.email[0] : 'X'}
        </Avatar>
        {/* } */}
      </Button>
      <Menu anchorEl={anchorEl} open={open} onClose={handleClose}>
        <MenuItem onClick={handleClose}>Profile</MenuItem>
        <MenuItem component={Link} to="/orders">
          My orders
        </MenuItem>
        <MenuItem
          onClick={() => {
            dispatch(signOut());
            dispatch(clearCart());
          }}
        >
          Logout
        </MenuItem>
      </Menu>
    </>
  );
};

export default SignedInMenu;
