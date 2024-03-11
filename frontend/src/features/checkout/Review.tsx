import { useMediaQuery, useTheme } from '@mui/material';
import CartPageDesktop from '../cart/CartPageDesktop';
import CartPageMobile from '../cart/CartPageMobile';





const Review = () => {
  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up("md"));

  return (
    <>
      { greaterThanMd 
        ? 
        <>
          <CartPageDesktop isCart={false} />
        </>
        : 
        <CartPageMobile isCart={false} />
      }
      
    </>
  );
}

export default Review;