import { Launch } from "@mui/icons-material";
import { Box, Divider, Link, Paper, Typography } from "@mui/material";


const AboutPage = () => {
  
  return(
    <Paper sx={{p: 5, pb: 2}}>
      <Typography gutterBottom variant='h5' align="center">
        Tech Stack:
      </Typography>

      <Box 
        display='flex' 
        alignItems={{xs: 'flex-start', sm: 'center'}} 
        flexDirection={{xs: 'column', sm: 'row'}} 
        justifyContent='space-between'
      >
        <Box>
          <Typography variant='h6'>
            API:
          </Typography>
          <Typography variant='body1' fontStyle='italic'>
            C#, ASP.NET, PostgreSQL
          </Typography>
        </Box>
        <Box>
          <Typography variant='h6'>
            Client Side:
          </Typography>
          <Typography variant='body1' fontStyle='italic'>
            Typescript, React, Redux, Material UI
          </Typography>
        </Box>
      </Box>
      <Divider/>


      <Typography gutterBottom variant='h5' align="center" pt={3}>
        Testing:
      </Typography>
      <Typography variant='h6'>Signing In & Signing Up</Typography>
      <Typography variant='body1' align='justify' pb={1}>Only signed in users are able to test payment functionality. However in order to check search, filtering, sorting, and cart-management an account is not required. Anonymous cart is created which later is transfered to a user upon signing-in.</Typography>
      <Typography variant='body1' align='justify'>Even a non-existent email address (e.g. test@test.com) will suffice to create an account and log in.</Typography>
      <Divider/>
      <Typography variant='h6' pt={2}>Payments</Typography>
      <Link 
          pt={1}
          display='flex' alignContent='center'
          href='https://stripe.com/docs/testing#use-test-cards'
          target='_blank'
          rel='noopener'
          aria-label='View Stripe guidelines for testing payments'
          underline='none'
          color='inherit'
        >
        <Typography variant='body1' align='justify'>
          Follow Stripe guidelines to test payments integration.
        </Typography> 
        <Launch/>
      </Link> 
      <Divider/>
      <Link 
          pt={1}
          display='flex' alignContent='center'
          href='https://stripe.com/docs/testing#declined-payments'
          target='_blank'
          rel='noopener'
          aria-label='View Stripe guidelines for testing declined payments'
          underline='none'
          color='inherit'
        >
        <Typography variant='body1' align='justify'>
          Use the test banking cards listed here to test declined payments.
        </Typography> 
        <Launch/>
      </Link> 
      <Divider/>
      <Link 
          pt={3}
          display='flex' alignContent='center' alignItems='center' justifyContent='center'
          href='https://github.com/Dimisz/ecomm-store'
          target='_blank'
          rel='noopener'
          aria-label='Visit GitHub repo to see the source code of the project'
          underline='none'
          color='primary'
        >
          <Typography variant='h6' align='justify' >
            View Source Code
          </Typography>
          <Launch/>
      </Link> 
    </Paper>
  );
}

export default AboutPage;