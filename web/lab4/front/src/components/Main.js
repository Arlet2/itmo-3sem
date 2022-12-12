import React from 'react';
import '../assets/react-toolbox/theme.css';
import theme from '../assets/react-toolbox/theme.js';
import ThemeProvider from 'react-toolbox/lib/ThemeProvider';

import '../css/Main.css';

import Form from './Form';
import Header from './Header';
import Map from './Map';
import Cookies from 'js-cookie';

function Main() {
	if (!Cookies.get("jwt-token"))
		window.location.replace("/login");

	return (
		<ThemeProvider theme={theme}>
			<div className="Main">
				<Header />
				<Form />
				<Map />
			</div>
		</ThemeProvider>
	);
}

export default Main;