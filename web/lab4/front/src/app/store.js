import { configureStore } from '@reduxjs/toolkit';
import authSlice from '../features/auth/authSlice';
import formSlice from '../features/formHandler/formSlice';
import tableSlice from '../features/tableHandler/tableSlice';

export const store = configureStore({
	reducer: {
		formHandler: formSlice,
		auth: authSlice,
		tableHandler: tableSlice
	}
});