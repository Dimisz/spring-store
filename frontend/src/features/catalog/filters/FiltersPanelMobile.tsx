import { FilterListRounded } from "@mui/icons-material";
import { Grid, Paper, SwipeableDrawer, Button, Box, IconButton, Typography } from "@mui/material";
import { useState } from "react";
import ProductSearch from "../search-field/ProductSearch";
import { useAppDispatch } from "../../../app/store/configureStore";
import RadioButtonGroup from "../../../app/layout/radio-button-group/RadioButtonGroup";
import { resetProductParams, setProductParams } from "../catalogSlice";
import CheckboxButtonsGroup from "../../../app/layout/checkbox-buttons-group/CheckboxButtonsGroup";

interface Props {
  sortOptions: { value: string; label: string; }[];
  brands: string[];
  types: string[];
  orderBy: string;
  checkedBrands: string[] | undefined;
  checkedTypes: string[] | undefined;
}


const FiltersPanelMobile = ({ sortOptions, brands, types, orderBy, checkedBrands, checkedTypes }: Props) => {
  const dispatch = useAppDispatch();

  const [isOpen, setIsOpen] = useState(false);

  const [resettable, setResettable] = useState(false);

  const handleReset = () => {
    dispatch(resetProductParams());
    setResettable(true);
  }



  const toggleDrawer =
    (open: boolean) =>
    (event: React.KeyboardEvent | React.MouseEvent) => {
      if (
        event &&
        event.type === 'keydown' &&
        ((event as React.KeyboardEvent).key === 'Tab' ||
          (event as React.KeyboardEvent).key === 'Shift')
      ) {
        return;
      }

      setIsOpen(open);
    };

  return(
    <Grid item xs={12}>
      <ProductSearch 
        resettable={resettable}
        setResettable={setResettable}
      />
      <Box display='flex' alignItems='center' justifyContent='right'>
        <Typography>
          Filter by: 
        </Typography>
        <IconButton 
          size="large"
          aria-label="filtering controls"
          aria-controls="filter-controls"
          aria-haspopup="true"
          color="inherit"
          onClick={toggleDrawer(true)}
        >
          <FilterListRounded/>
        </IconButton>
      </Box>
      <SwipeableDrawer
        anchor='top'
        open={isOpen}
        onClose={toggleDrawer(false)}
        onOpen={toggleDrawer(true)}
      >
      <Grid 
        item
        sx={{ width:  'auto' }}
        role="presentation"
        // onClick={toggleDrawer(false)}
        onKeyDown={toggleDrawer(false)}
      >
        <Paper sx={{mb: 2, p: 2}}>
          <RadioButtonGroup
            selectedValue={orderBy}
            options={sortOptions}
            onChange={(event) => dispatch(setProductParams({orderBy: event.target.value}))}
          />
        </Paper>
        <Paper sx={{mb: 2, p: 2}}>
          <CheckboxButtonsGroup
            resettable={resettable}
            setResettable={setResettable}
            items={brands}
            checked={checkedBrands}
            onChange={(items: string[]) => dispatch(setProductParams({brands: items}))}
          />
        </Paper>
        <Paper sx={{mb: 2, p: 2}}>
            <CheckboxButtonsGroup
              resettable={resettable}
              setResettable={setResettable}
              items={types}
              checked={checkedTypes}
              onChange={(items: string[]) => dispatch(setProductParams({types: items}))}
            />
            <Box display='flex' flexDirection='column' alignContent='space-between' justifyContent='space-between'>
              <Button
                sx={{m: 0, mb: 1, mt: 1}}
                variant='outlined'
                color='warning'
                onClick={handleReset}
              >Reset Filters</Button>
              <Button
                sx={{m: 0, mb: 1, mt: 1}}
                onClick={toggleDrawer(false)}
                color='primary'
                variant='outlined'
                // sx={{ m: 1, ml: 'auto', display: 'block' }}
              >Close</Button>
            </Box>

        </Paper>
        
      </Grid>
      </SwipeableDrawer>
    </Grid>
  );
}

export default FiltersPanelMobile;