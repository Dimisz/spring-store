import * as yup from 'yup';

export const validationSchema = [
  yup.object({
    fullName: yup.string().required('Please enter your full name'),
    address1: yup.string().required('Please fill out address line 1'),
    address2: yup.string().required('Please fill out address line 2'),
    city: yup.string().required('Please specify your city'),
    state: yup.string().required('Please specify your state / province'),
    zip: yup.string().required('Please specify your zip / postal code'),
    country: yup.string().required('Please specify your country'),
  }),
  yup.object(),
  yup.object({
    nameOnCard: yup.string().required('Please enter the Cardholder Name')
  })
]