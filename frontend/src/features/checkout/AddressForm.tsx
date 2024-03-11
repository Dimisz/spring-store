import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import { useFormContext } from 'react-hook-form';
import AppTextInput from '../../app/layout/app-text-input/AppTextInput';
import AppCheckBox from '../../app/layout/app-checkbox/AppCheckBox';

const AddressForm = () => {
  const { control, formState } = useFormContext();
  return (
    <>
      <Typography variant="h6" gutterBottom>
        Shipping address
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <AppTextInput 
            control={control} 
            name='fullName' 
            label='Full Name' 
          />
        </Grid>
        <Grid item xs={12}>
        <AppTextInput 
            control={control} 
            name="address1"
            label="Address line 1"
        />
        </Grid>
        <Grid item xs={12}>
        <AppTextInput 
            control={control} 
            name="address2"
            label="Address line 2"
        />
        </Grid>
        <Grid item xs={12} sm={6}>
          <AppTextInput 
              control={control} 
              name="city"
              label="City"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <AppTextInput 
              control={control} 
              name="state"
              label="State/Province/Region"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <AppTextInput 
              control={control} 
              name="zip"
              label="Zip / Postal code"
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <AppTextInput 
              control={control} 
              name="country"
              label="Country"
          />
        </Grid>
        <Grid item xs={12}>
          <AppCheckBox 
            disabled={!formState.isDirty}
            name='saveAddress' 
            label='Save this as the default address'
            control={control}
          />
        </Grid>
      </Grid>
    </>
  );
}

export default AddressForm;