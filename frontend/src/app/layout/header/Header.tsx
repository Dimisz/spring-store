import { AppBar, ListItem, useMediaQuery, useTheme } from "@mui/material";
import { NavLink } from "react-router-dom";

import HeaderMobile from "./HeaderMobile";
import HeaderDesktop from "./HeaderDesktop";
import { useAppSelector } from "../../store/configureStore";

const midSectionLinks = [
  { title: 'catalog', path: '/catalog' },
  { title: 'about', path: '/about' },
  { title: 'contacts', path: '/contacts' }
];

const rightSectionLinks = [
  { title: 'login', path: '/login' },
  { title: 'register', path: '/register' },
];

interface Props {
  darkMode: boolean;
  toggleTheme: () => void;
}

const Header = ({ darkMode, toggleTheme }: Props) => {
  const { cart } = useAppSelector((state) => state.cart);

  const itemsCount = cart?.items.reduce((totalQty, item) => {
    return totalQty + item.quantity; 
  }, 0);

  const renderLinks = (links: { title: string; path: string; }[], onClick?: () => void ) => {
    return links.map(({ title, path }) => {
      return(
        <ListItem
          component={NavLink}
          to={path}
          key={path}
          sx={{ 
            color: 'inherit', 
            typography: 'h6', 
            display: 'block', 
            '&:hover': {
              color: 'grey.500'
            } ,
            '&.active': {
              color: darkMode ? 'secondary.main' : 'text.secondary'
            }
          }}  
          onClick={onClick}
        >
          {title.toUpperCase()}
        </ListItem>
      );
    });
  }

  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));

  return(
    <AppBar position='sticky' >
      {
        greaterThanMd
        ?
        <HeaderDesktop
          darkMode={darkMode} 
          toggleTheme={toggleTheme}
          renderLinks={renderLinks}
          midSectionLinks={midSectionLinks}
          rightSectionLinks={rightSectionLinks}
          itemsCount={itemsCount}
        />
        :
        <HeaderMobile
          darkMode= {darkMode}
          toggleTheme={toggleTheme}
          renderLinks={renderLinks} 
          midSectionLinks={midSectionLinks} 
          rightSectionLinks={rightSectionLinks}
          itemsCount={itemsCount}
        />
      }
    </AppBar>
  );
}

export default Header;