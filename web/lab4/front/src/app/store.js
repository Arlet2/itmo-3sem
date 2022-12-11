import { configureStore } from '@reduxjs/toolkit';
import formSlice from '../features/formHandler.js/formSlice';

export const store = configureStore({
	reducer: {
		formHandler: formSlice
	}
});