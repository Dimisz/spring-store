import { DarkMode, LightMode, ShoppingCart } from "@mui/icons-material";
import { Badge, Box, IconButton, List, Toolbar, Typography } from "@mui/material";
import { Link, NavLink } from "react-router-dom";
import { useAppSelector } from "../../store/configureStore";
import SignedInMenu from "../signed-in-menu/SignedInMenu";

interface Props {
  darkMode: boolean;
  toggleTheme: () => void;
  renderLinks: (links: { title: string; path: string; }[], handler?: () => void ) => React.ReactNode;
  midSectionLinks: { title: string; path: string; }[];
  rightSectionLinks: { title: string; path: string; }[];
  itemsCount: number | undefined;
}

const HeaderDesktop = ({
  darkMode, 
  toggleTheme,
  renderLinks,
  midSectionLinks,
  rightSectionLinks,
  itemsCount
 }: Props) => {
  const { user } = useAppSelector((state) => state.account);

  const renderedMidSectionLinks = renderLinks(midSectionLinks);
  const renderedRightSectionLinks = renderLinks(rightSectionLinks);
  return(
    <Toolbar sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          {/* Logo section */}
          <Box display='flex' alignItems='center'>
            <Typography
                variant='h6'
                noWrap
                component={NavLink}
                to='/'
                sx={{ color: 'inherit', textDecoration: 'none' }}
              >
                E-KOMM
              </Typography>
              <IconButton
                size='large'
                onClick={toggleTheme}
              >
                { darkMode
                  ?
                  <LightMode titleAccess="Switch to light mode"/>
                  :
                  <DarkMode titleAccess="Switch to dark mode"/>
                }
              </IconButton>
          </Box>
          {/* middle section */}
          <List sx={{ display: 'flex' }}>
           {renderedMidSectionLinks}
          </List>
          {/* right hand section */}

          <Box display='flex' alignItems='center'>
            <IconButton 
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
              <SignedInMenu />
              : 
              <List sx={{ display: 'flex' }}>
                {renderedRightSectionLinks}
              </List>
            }
          </Box>
        </Toolbar>
  );
}

export default HeaderDesktop;