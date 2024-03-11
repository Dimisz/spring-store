import { ClearOutlined, Search } from "@mui/icons-material";
import { FormControl, IconButton, InputAdornment, InputLabel, OutlinedInput, debounce } from "@mui/material";
import { useAppDispatch, useAppSelector } from "../../../app/store/configureStore";
import { setProductParams } from "../catalogSlice";
import { useEffect, useMemo, useState } from "react";

interface Props {
  resettable: boolean;
  setResettable: (value: boolean) => void;
}

const ProductSearch = ({resettable, setResettable}: Props) => {
  const { productParams } = useAppSelector((state) => state.catalog);
  const dispatch = useAppDispatch();

  const [searchTerm, setSerchTerm] = useState(productParams.searchTerm || '');

  useEffect(() => {
    if(resettable){
      setSerchTerm('');
      setResettable(false);
    }
  }, [resettable]);

  const debouncedSearch = useMemo(
    () =>
      debounce((searchTerm: string) => {
        dispatch(setProductParams({ searchTerm: searchTerm }));
      }, 1000),
    [dispatch]
  );

  return(
    <FormControl 
      sx={{ 
        mb: {xs: 0, md: 2}, 
        mt: {xs: 2, md: 5}, 
        width: '100%' 
      }} 
      variant="outlined">
      <InputLabel htmlFor="search-products-field">Search Products</InputLabel>
      <OutlinedInput
        id="search-products-field"
        type="text"
        value={searchTerm}

        onChange={(event: any) => {
          setSerchTerm(event.target.value);
          // debouncedSearch func will send requests to API 
          // 1 second after the user stops typing
          // here will also use the onClick event 
          debouncedSearch(event.target.value); 
        }}
        endAdornment={
          <InputAdornment position="end">
            {
              searchTerm && searchTerm !== ''
              ?
              <IconButton
                onClick={() => {
                    setSerchTerm('');
                    debouncedSearch(''); 
                  }
                }
                aria-label="clear search input"
                edge="end"
              >
                  <ClearOutlined/>
              </IconButton>
              :
              // <IconButton
              //   // onClick={() => dispatch(setProductParams({searchTerm: searchTerm}))}
              //   aria-label="search products"
              //   edge="end"
              // >
                <Search/>
              // </IconButton>
            }

          </InputAdornment>
        }
        label="Search Products"
      />
    </FormControl>
  );
}

export default ProductSearch;