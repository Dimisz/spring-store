import { useCallback, useEffect, useState } from 'react';
// import my components
import Header from "./layout/header/Header";
// import ResponsiveAppBar from './layout/ResponsiveHeader';
// mui imports
import { Container, CssBaseline, ThemeProvider, createTheme } from "@mui/material";
import { Outlet } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
// import { useStoreContext } from './context/StoreContext';
import Loader from './layout/Loader';
import { useAppDispatch } from './store/configureStore';
import { fetchCartAsync } from '../features/cart/cartSlice';
import { fetchCurrentUser } from '../features/account/accountSlice';

const App = () => {
  // const { setCart } = useStoreContext();
  const dispatch = useAppDispatch();
  const [loading, setLoading] = useState(true);

  const initApp = useCallback(async () => {
    try {
      await dispatch(fetchCurrentUser());
      await dispatch(fetchCartAsync());
    }
    catch(error) {
      console.log(error);
    }
  }, [dispatch]);

  useEffect(() => {
    initApp().then(() => setLoading(false));
    // const buyerId = getCookie('buyerId');
    // if(localStorage.getItem('user')){
    //   dispatch(fetchCurrentUser());
    // }
    
    // if(buyerId){
    //   agent.Cart.get()
    //     .then((cart) => dispatch(setCart(cart)))
    //     .catch((error) => console.log(error))
    //     .finally(() => setLoading(false));
    // }
    // else {
    //   setLoading(false);
    // }
  }, [initApp]);

  const [darkMode, setDarkMode] = useState(true);
  const toggleTheme = () => {
    setDarkMode((m) => !m);
  }

  const theme = createTheme({
    palette: {
      mode: darkMode ? 'dark' : 'light',
      background: {
        default: darkMode ? '#121212' : '#eaeaea' 
      }
    }
  });

  if(loading) return <Loader message='Intializing app...'/>;

  return (
    <ThemeProvider theme={theme}>
      <ToastContainer 
        position='bottom-right'
        hideProgressBar
        theme='colored'
      />
      <CssBaseline />
      
      <Header darkMode={darkMode} toggleTheme={toggleTheme} />
      <Container>
        <Outlet />
      </Container>
    </ThemeProvider>
  )
}

export default App
