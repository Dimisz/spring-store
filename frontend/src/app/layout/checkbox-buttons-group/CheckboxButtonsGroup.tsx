import { FormGroup, FormControlLabel, Checkbox } from "@mui/material";
import { useEffect, useState } from "react";


interface Props {
  items: string[];
  checked?: string[];
  onChange: (items: string[]) => void;
  resettable: boolean;
  setResettable: (value: boolean) => void;
}
const CheckboxButtonsGroup = ({ items, checked, onChange, resettable, setResettable }:Props) => {
  const [checkedItems, setCheckedItems] = useState(checked || []);

  useEffect(() => {
    if(resettable){
      setCheckedItems([]);
      setResettable(false);
    }
  }, [resettable]);

  const handleChecked = (value: string) => {
    const currentIndex = checkedItems.findIndex((item) => item === value);
    let newChecked: string[] = [];
    if(currentIndex === -1) newChecked = [...checkedItems, value];
    else newChecked = checkedItems.filter((item) => item !== value);
    setCheckedItems(newChecked);
    onChange(newChecked);
    console.log(newChecked);
  }

  return(
    <FormGroup>
      {items.map((item) => {
        return(
          <FormControlLabel 
            control={
              <Checkbox 
                checked={checkedItems.indexOf(item) !== -1}
                onClick={() => handleChecked(item)}
              />
            } 
            label={item} 
            key={item} 
          />
        );
      })}
    </FormGroup>
  );
}

export default CheckboxButtonsGroup;