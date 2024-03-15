import { Elements } from '@stripe/react-stripe-js';
import CheckoutPage from './CheckoutPage';
import { loadStripe } from '@stripe/stripe-js';
import { useAppDispatch, useAppSelector } from '../../app/store/configureStore';
import { useEffect, useState } from 'react';
import agent from '../../app/api/agent';
import { setCart } from '../cart/cartSlice';
import Loader from '../../app/layout/Loader';

const stripePromise = loadStripe(
  'pk_test_51O7qiQJLrKiodSguGQ66BDpwrWegh1CC4kxKGcIwmkoiihMS8ybJ6J6qgO2T8QD16toID66Qhp0QAfDSTMKcjNek003Lxgs9Z2'
);

const CheckoutWrapper = () => {
  const dispatch = useAppDispatch();
  const [loading, setLoading] = useState(false);

  const { cart } = useAppSelector((state) => state.cart);

  // stripe prep here
  useEffect(() => {
    setLoading(true);
    agent.Payments.createPaymentIntent()
      .then(() => dispatch(setCart(cart)))
      .catch((error) => console.log(error))
      .finally(() => setLoading(false));
  }, [dispatch]);

  if (loading) return <Loader message="Loading checkout..." />;
  return (
    <Elements stripe={stripePromise}>
      <CheckoutPage />
    </Elements>
  );
};

export default CheckoutWrapper;
