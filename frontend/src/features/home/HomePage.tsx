import { Launch } from "@mui/icons-material";
import { Paper, Typography } from "@mui/material";
import { Link } from "react-router-dom";


// import Slider from "react-slick";

const HomePage = () => {
  // const settings = {
  //   dots: true,
  //   infinite: true,
  //   speed: 500,
  //   slidesToShow: 1,
  //   slidesToScroll: 1
  // }

  return(
    // <>
    //   <Slider {...settings}>
    //     <div>
    //       <img src='https://i.ibb.co/VpW4x5t/roll-up-jean-shirt.png' alt='hero1' style={{display: 'block', maxHeight: '50vh'}}/>
    //     </div>
    //     <div>
    //       <img src='https://i.ibb.co/N3BN1bh/red-polka-dot-dress.png' alt='hero2' style={{display: 'block', maxHeight: '50vh'}}/>
    //     </div>
    //     <div>
    //       <img src='https://i.ibb.co/fMTV342/nike-brown.png' alt='hero3' style={{display: 'block', maxHeight: '50vh'}}/>
    //     </div>
    //   </Slider>
    //   <Box display='flex'justifyContent='center' sx={{p:4}}>
    //     <Typography variant='h1'>
    //       Welcome to the shop!
    //     </Typography>
    //   </Box>
    // </>
    <Paper sx={{p: 2}}>
      <Typography variant='h5' align='center' pt={2} pb={2}>
        Hi there! My name is Vladimir and I'm a Fullstack Developer.
      </Typography>
      <Typography variant='body1' align='justify' pt={2} pb={2}>
      This is supposed to be a landing page of an eCommerce Store. But since it's my portfolio project, not a real store, I may as well provide some guidance on using and testing the web app instead of putting yet another fancy component here.
      </Typography>
      <Link 
        style={{display: 'flex', color: 'inherit', textDecoration: 'none', paddingTop: '1rem'}}
        to='/catalog'
        aria-label='View catalog of products'
      >
        <Typography variant='body1' align='justify'>Start with Catalog to explore the basic functionality of the store</Typography>
        <Launch />
      </Link>

      <Link 
        style={{display: 'flex', color: 'inherit', textDecoration: 'none', paddingTop: '1rem'}}
        to='/register'
        aria-label='Go to the registration form'
      >
        <Typography variant='body1' align='justify'>Create an account and login to test payments integration. Even a non-existent email address will suffice (e.g. test@test.com)</Typography>
        <Launch />
      </Link>

      <Link 
        style={{display: 'flex', color: 'inherit', textDecoration: 'none', paddingTop: '1rem'}}
        to='/about'
        aria-label='View the about page'
      >
        <Typography variant='body1' align='justify'>You may find more technical details about the app on the About page</Typography>
        <Launch />
      </Link>
    </Paper>
  );
}

export default HomePage;