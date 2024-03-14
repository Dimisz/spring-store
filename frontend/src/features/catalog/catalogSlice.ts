import { createAsyncThunk, createEntityAdapter, createSlice } from "@reduxjs/toolkit";
import { Product, ProductParams } from "../../app/models/product";
import agent from "../../app/api/agent";
import { RootState } from "../../app/store/configureStore";
import { MetaData } from "../../app/models/pagination";

interface CatalogState {
  productsLoaded: boolean;
  filtersLoaded: boolean;
  status: string;
  brands: string[];
  categories: string[];
  productParams: ProductParams;
  metaData: MetaData | null;
}

const productsAdapter = createEntityAdapter<Product>();

const getAxiosParams = (productParams: ProductParams) => {
  const params = new URLSearchParams();
  params.append('pageNumber', productParams.pageNumber.toString());
  params.append('pageSize', productParams.pageSize.toString());
  params.append('orderBy', productParams.orderBy);

  // checking if optional parameters were passed
  if(productParams.searchTerm){
    params.append('searchTerm', productParams.searchTerm);
  }
  if(productParams.brands.length > 0){
    params.append('brands', productParams.brands.toString());
  }
  if(productParams.categories.length > 0){
    params.append('categories', productParams.categories.toString());
  }
  return params;
}

export const fetchAllProductsAsync = createAsyncThunk<Product[], void, {state: RootState}>(
  'catalog/fetchAllProductsAsync',
  async (_, thunkApi) => {
    const params = getAxiosParams(thunkApi.getState().catalog.productParams);
    try {
      const response = await agent.Catalog.list(params);
      console.log(response);
      // DEBUG
      // thunkApi.dispatch(setMetaData(response.metaData));
      thunkApi.dispatch(setMetaData({
        currentPage: response.number,
        totalPages: response.totalPages,
        pageSize: response.size,
        totalCount: response.totalElements
      }));

      return response.content;
    }
    catch(error: any){
      return thunkApi.rejectWithValue({ error: error.data });
    }
  }
)

export const fetchProductAsync = createAsyncThunk<Product, number>(
  'catalog/fetchProductAsync',
  async (productId, thunkApi) => {
    try {
      return await agent.Catalog.details(productId);
    }
    catch(error: any){
      console.log(thunkApi.rejectWithValue({error: error.data}));
    }
  }
)

export const fetchFiltersAsync = createAsyncThunk(
  'catalog/fetchFiltersAsync',
  async (_, thunkApi) => {
    try {
      return await agent.Catalog.fetchFilters();
    }
    catch(error: any){
      return thunkApi.rejectWithValue({error: error.data});
    }
  }
)

const initParams = () => {
  return {
    pageNumber: 0,
    pageSize: 6,
    orderBy: 'name',
    brands: [],
    categories: []
  }
}

export const catalogSlice = createSlice({
  name: 'catalog',
  initialState: productsAdapter.getInitialState<CatalogState>({
    productsLoaded: false,
    filtersLoaded: false,
    status: 'idle',
    brands: [],
    categories: [],
    productParams: initParams(),
    metaData: null
  }),
  reducers: {
    setProductParams: (state, action) => {
      state.productsLoaded = false;
      state.productParams = {...state.productParams, ...action.payload, pageNumber: 0};
    },
    setPageNumber: (state, action) => {
      state.productsLoaded = false;
      state.productParams = {...state.productParams, ...action.payload};
    },
    setMetaData: (state, action) => {
      state.metaData = action.payload;
    },
    resetProductParams: (state) => {
      state.productParams = initParams();
      state.productsLoaded = false;
    }
  },
  extraReducers: (builder => {
    builder.addCase(fetchAllProductsAsync.pending, (state) => {
      state.status = 'pendingFetchAllProducts';
    });
    builder.addCase(fetchAllProductsAsync.fulfilled, (state, action) => {
      productsAdapter.setAll(state, action.payload);
      state.status = 'idle';
      state.productsLoaded = true;
    });
    builder.addCase(fetchAllProductsAsync.rejected, (state) => {
      state.status = 'idle';
      // console.log(action.payload);
    });
    builder.addCase(fetchProductAsync.pending, (state) => {
      state.status = 'pendingFetchProduct';
    });
    builder.addCase(fetchProductAsync.fulfilled, (state, action) => {
      productsAdapter.upsertOne(state, action.payload);
      state.status = 'idle';
    });
    builder.addCase(fetchProductAsync.rejected, (state) => {
      // console.log(action);
      state.status = 'idle';
    });
    builder.addCase(fetchFiltersAsync.pending, (state) => {
      state.status = 'pendingFetchFilters';
    });
    builder.addCase(fetchFiltersAsync.fulfilled, (state, action) => {
      state.brands = action.payload.brands;
      state.categories = action.payload.types;
      state.filtersLoaded = true;
      state.status = 'idle';
    });
    builder.addCase(fetchFiltersAsync.rejected, (state) => {
      state.status = 'idle';
    });

  })
})

export const productSelectors = productsAdapter.getSelectors((state: RootState) => state.catalog);

export const { setProductParams, resetProductParams, setMetaData, setPageNumber } = catalogSlice.actions;