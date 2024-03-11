import { LightMode, DarkMode, Login, ShoppingCart } from "@mui/icons-material";
import { Badge, Box, IconButton, Menu, Toolbar, Typography } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';

import { useState } from "react";
import { Link, NavLink } from "react-router-dom";
import { useAppSelector } from "../../store/configureStore";
import SignedInMenu from "../signed-in-menu/SignedInMenu";

interface Props {
  darkMode: boolean;
  toggleTheme: () => void;
  renderLinks: (links: { title: string; path: string; }[], handler: () => void ) => React.ReactNode;
  midSectionLinks: { title: string; path: string; }[];
  rightSectionLinks: { title: string; path: string; }[];
  itemsCount: number | undefined;
}

const HeaderMobile = ({ 
  darkMode, 
  toggleTheme, 
  renderLinks, 
  midSectionLinks, 
  rightSectionLinks,
  itemsCount
 }: Props) => {
  const { user } = useAppSelector((state) => state.account);

  const [anchorElNav, setAnchorElNav] = useState<null | HTMLElement>(null);
  const [anchorElUser, setAnchorElUser] = useState<null | HTMLElement>(null);

  const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return(
    <Toolbar sx={{ display: { xs: 'flex', md: 'none' }, justifyContent: 'space-between', alignItems: 'center' }}>
      <Box display='flex' alignItems='center'>
        <IconButton
          size="large"
          aria-label="site navigation menu"
          aria-controls="menu-appbar"
          aria-haspopup="true"
          onClick={handleOpenNavMenu}
          color="inherit"
        >
          <MenuIcon />
        </IconButton>
        <Menu
          id="menu-appbar"
          anchorEl={anchorElNav}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'left',
          }}
          keepMounted
          transformOrigin={{
            vertical: 'top',
            horizontal: 'left',
          }}
          open={Boolean(anchorElNav)}
          onClose={handleCloseNavMenu}
          sx={{
            display: { xs: 'block', md: 'none' },
          }}
        >
          {renderLinks(midSectionLinks, handleCloseNavMenu)}
        </Menu>
        
        <IconButton
          size='large'
          onClick={toggleTheme}
        >
          {darkMode
            ?
            <LightMode titleAccess="Switch to light mode" />
            :
            <DarkMode titleAccess="Switch to dark mode" />}
        </IconButton>
      </Box>
      
      <Typography
        variant='h6'
        component={NavLink}
        to='/'
        sx={{
          color: 'inherit',
          textDecoration: 'none'
        }}
      >
        E-KOMM
      </Typography>
      
      <Box display={{ xs: 'flex', md: 'none' }} alignItems='center'>
        <IconButton 
          // sx={{ marginLeft: 1, marginRight: 1 }}
          component={Link}
          to='/cart'
          size='large' 
          edge='start' 
          color='inherit'
        >
          <Badge badgeContent={itemsCount} color='secondary'>
            <ShoppingCart />
          </Badge>
        </IconButton>
        {
          user 
          ?
          <SignedInMenu/>
          :
          <>
            <IconButton
            size="large"
            aria-label="user account menu"
            aria-controls="user-account-menu"
            aria-haspopup="true"
            onClick={handleOpenUserMenu}
            color="inherit"
                    >
              <Login />
            </IconButton>
            <Menu
                id="user-account-menu"
                anchorEl={anchorElUser}
                anchorOrigin={{
                  vertical: 'bottom',
                  horizontal: 'left',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'left',
                }}
                open={Boolean(anchorElUser)}
                onClose={handleCloseUserMenu}
                sx={{
                  display: { xs: 'block', md: 'none' },
                }}
            >
              {renderLinks(rightSectionLinks, handleCloseUserMenu)}
            </Menu>
          </>
        }
        
      </Box>
    </Toolbar>
  );
}

export default HeaderMobile;