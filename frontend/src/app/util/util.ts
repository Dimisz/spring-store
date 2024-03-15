export const getCookie = (key: string) => {
  const b = document.cookie.match("(^|;)\\s*" + key + "\\s*=\\s*([^;]+)");
  return b ? b.pop() : "";
}

export const formatCurrency = (amount: number) => {
  // when working with stripe should use Longs and divide by 100 only to shape
  // the dispplayed numbers
  // return amount / 100;
  return amount;
}