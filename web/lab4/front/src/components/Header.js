import Cookies from 'js-cookie';
import React from 'react';

import AppBar from 'react-toolbox/lib/app_bar/AppBar';
import Navigation from 'react-toolbox/lib/navigation/Navigation';

import LogoutLogo from '../img/logout.svg';
import { goToLogin } from '../utils';

function Header() {
    return (
        <header>
            <AppBar title='WEB lab 4'>
                <Navigation>
                    <p>Logout</p>
                    <img id="logoutIcon" onClick={logout} src={LogoutLogo} alt="Logout"/>
                </Navigation>
            </AppBar>
        </header>
    );
}

function logout() {
    Cookies.remove("jwt-token");

    goToLogin();
}

export default Header;