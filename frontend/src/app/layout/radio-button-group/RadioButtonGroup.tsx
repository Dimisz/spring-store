import { FormControl, FormLabel, RadioGroup, FormControlLabel, Radio } from "@mui/material";

interface Props {
  options: any[];
  onChange: (event: any) => void;
  selectedValue: string;
}

const RadioButtonGroup = ({options, onChange, selectedValue}: Props) => {
  return(
    <FormControl component='fieldset'>
          <FormLabel component='legend'>Sort By</FormLabel>
          <RadioGroup
            onChange={onChange}
            value={selectedValue}
            aria-label='sorting options'
            defaultValue='name'
            name='radio-buttons-group'
          >
            {options.map((option) => {
              return(
                <FormControlLabel 
                  key={option.value}
                  value={option.value} 
                  control={<Radio/>} label={option.label} />
              )
            })}
          </RadioGroup>
        </FormControl>
  );
}
export default RadioButtonGroup;