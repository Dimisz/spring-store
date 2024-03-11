import { Box, Card, CardActions, CardContent, Grid, Skeleton } from "@mui/material";

const ProductPlaceholder = () => {
  return(
          <Grid 
            item xs component={Card} 
            height={500} 
            display='flex' flexDirection='column' m={0}>
              <Skeleton sx={{ height: '60%' }} animation="wave" variant="rectangular" />
              <Box 
                height='40%' 
                display='flex' 
                flexDirection='column' 
                justifyContent='space-between' 
                padding={1} paddingLeft={0}
                >
                  <CardContent sx={{ flexGrow: 1 }}>
                      <>
                          <Skeleton animation="wave" height={15} style={{ marginBottom: 6 }} />
                          <Skeleton animation="wave" height={15}  width='50%' style={{ marginBottom: 12 }}/>
                          <Skeleton animation="wave" height={10}  width='60%' />
                      </>
                  </CardContent>
                  <CardActions sx={{ flexGrow: 1 }}>
                          <>
                            <Skeleton animation="wave" height={10} width='20%' style={{ marginTop: 'auto', marginLeft: 10 }}/>
                            <Skeleton animation="wave" height={10} width='20%' style={{ marginTop: 'auto' }}/>
                          </>
                  </CardActions>
              </Box>
          </Grid>

  );
}

export default ProductPlaceholder;