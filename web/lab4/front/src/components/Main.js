import React from 'react';
import '../assets/react-toolbox/theme.css';
import theme from '../assets/react-toolbox/theme.js';
import ThemeProvider from 'react-toolbox/lib/ThemeProvider';

import '../css/Main.css';

import Form from './Form';
import Header from './Header';
import Map from './Map';
import Cookies from 'js-cookie';
import { goToLogin, validateToken } from '../utils';
import DataTable from './DataTable';

function Main() {
	validateToken()
		.then((result) => {
			if (!result) {
				console.log(123);
				Cookies.remove("jwt-token");
				goToLogin();
			}
		});

	return (
		<ThemeProvider theme={theme}>
			<div className="Main">
				<Header />
				<Form />
				<DataTable />
				<Map />
			</div>
		</ThemeProvider>
	); 
}

export default Main;