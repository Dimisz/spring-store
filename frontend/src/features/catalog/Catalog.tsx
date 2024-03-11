import { useEffect } from 'react';

import ProductList from './ProductList';
import { useAppDispatch, useAppSelector } from '../../app/store/configureStore';
import {
  fetchAllProductsAsync,
  fetchFiltersAsync,
  productSelectors,
  setPageNumber,
} from './catalogSlice';
import { Grid, Typography, useMediaQuery, useTheme } from '@mui/material';
import FiltersPanelDesktop from './filters/FiltersPanelDesktop';
import FiltersPanelMobile from './filters/FiltersPanelMobile';
import AppPagination from '../../app/layout/app-pagination/AppPagination';
import Loader from '../../app/layout/Loader';

const sortOptions = [
  { value: 'name', label: 'Alphabetical' },
  { value: 'priceDesc', label: 'Price - High to low' },
  { value: 'price', label: 'Price - Low to high' },
];

const Catalog = () => {
  const products = useAppSelector(productSelectors.selectAll);
  const {
    productsLoaded,
    filtersLoaded,
    brands,
    types,
    productParams,
    metaData,
  } = useAppSelector((state) => state.catalog);

  const dispatch = useAppDispatch();

  const theme = useTheme();
  const greaterThanMd = useMediaQuery(theme.breakpoints.up('md'));

  useEffect(() => {
    if (!productsLoaded) dispatch(fetchAllProductsAsync());
  }, [productsLoaded, dispatch]);

  useEffect(() => {
    if (!filtersLoaded) dispatch(fetchFiltersAsync());
  }, [filtersLoaded, dispatch]);

  if (!filtersLoaded) return <Loader message="Loading products..." />;
  return (
    <Grid container columnSpacing={3} spacing={1}>
      {greaterThanMd ? (
        <FiltersPanelDesktop
          sortOptions={sortOptions}
          brands={brands}
          types={types}
          orderBy={productParams.orderBy}
          checkedBrands={productParams.brands}
          checkedTypes={productParams.types}
        />
      ) : (
        <FiltersPanelMobile
          sortOptions={sortOptions}
          brands={brands}
          types={types}
          orderBy={productParams.orderBy}
          checkedBrands={productParams.brands}
          checkedTypes={productParams.types}
        />
      )}
      {metaData && metaData?.totalCount > 0 ? (
        <>
          <Grid item xs={12} md={9} mt={{ xs: -5, md: 0 }}>
            <ProductList products={products} />
          </Grid>
          <AppPagination
            greaterThanMd={greaterThanMd}
            metaData={metaData}
            onPageChange={
              (page: number) =>
                dispatch(setPageNumber({ pageNumber: page - 1 })) // API pagination starts from 0
            }
          />
        </>
      ) : (
        <Grid item xs={12} md={9} mt={{ xs: -5, md: 0 }}>
          <Typography sx={{ mt: { xs: 2, md: 5 } }} variant="h6">
            No products found...
          </Typography>
        </Grid>
      )}
      {/* <Grid item xs={12} md={9} mt={{xs: -5, md: 0}}>
          <ProductList products={products} />
        </Grid>
        <AppPagination greaterThanMd={greaterThanMd} metaData={metaData}/> */}
    </Grid>
  );
};

export default Catalog;
