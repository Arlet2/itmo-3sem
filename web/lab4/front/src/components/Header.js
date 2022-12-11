import React from 'react';

import AppBar from 'react-toolbox/lib/app_bar/AppBar';
import Navigation from 'react-toolbox/lib/navigation/Navigation';

import LogoutLogo from '../img/logout.svg';

function Header() {
    return (
        <header>
            <AppBar title='WEB lab 4'>
                <Navigation>
                    <p>Logout</p>
                    <img src={LogoutLogo} alt="Logout"/>
                </Navigation>
            </AppBar>
        </header>
    );
}

export default Header;