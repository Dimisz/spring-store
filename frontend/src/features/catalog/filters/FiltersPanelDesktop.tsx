import { Button, Grid, Paper } from "@mui/material";
import ProductSearch from "../search-field/ProductSearch";
import RadioButtonGroup from "../../../app/layout/radio-button-group/RadioButtonGroup";
import { useAppDispatch } from "../../../app/store/configureStore";
import { resetProductParams, setProductParams } from "../catalogSlice";
import CheckboxButtonsGroup from "../../../app/layout/checkbox-buttons-group/CheckboxButtonsGroup";
import { useState } from "react";


interface Props {
  sortOptions: { value: string; label: string; }[];
  brands: string[];
  types: string[];
  orderBy: string;
  checkedBrands: string[];
  checkedTypes: string[];
}

const FiltersPanelDesktop = ({ sortOptions, brands, types, orderBy, checkedBrands, checkedTypes }: Props) => {
  const dispatch = useAppDispatch();

  const [resettable, setResettable] = useState(false);

  const handleReset = () => {
    dispatch(resetProductParams());
    setResettable(true);
  }

  return(
    <Grid item md={3}>
      <ProductSearch
        resettable={resettable}
        setResettable={setResettable}
      />
      <Paper sx={{mb: 2, p: 2}}>
        <RadioButtonGroup
          selectedValue={orderBy}
          options={sortOptions}
          onChange={(event) => dispatch(setProductParams({orderBy: event.target.value}))}
        />
      </Paper>
      <Paper sx={{mb: 2, p: 2}}>
        <CheckboxButtonsGroup
          items={brands}
          checked={checkedBrands}
          onChange={(items: string[]) => dispatch(setProductParams({brands: items}))}
          resettable={resettable}
          setResettable={setResettable}
        />
      </Paper>
      <Paper sx={{mb: 2, p: 2}}>
        <CheckboxButtonsGroup
            items={types}
            checked={checkedTypes}
            onChange={(items: string[]) => dispatch(setProductParams({types: items}))}
            resettable={resettable}
            setResettable={setResettable}
          />
      </Paper>
      <Button
          variant='outlined'
          fullWidth
          onClick={handleReset}
        >Reset Filters
        </Button>
    </Grid>
  );
}

export default FiltersPanelDesktop;