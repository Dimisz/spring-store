import { Button, Container, Divider, Paper, Typography } from "@mui/material";
import { Link } from "react-router-dom";

const NotFound = () => {

  return(
    <Container component={Paper} sx={{ height: 400 }}>
          <Typography variant='h3' gutterBottom>
            Ooops.. We could not find what you were looking for.
          </Typography>
          <Divider />
          <Button fullWidth component={Link} to='/catalog'>Go back to shop</Button>
    </Container>
  );
}

export default NotFound;