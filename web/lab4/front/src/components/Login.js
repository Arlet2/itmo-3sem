import React from "react";

import Card from "react-toolbox/lib/card/Card";
import CardTitle from "react-toolbox/lib/card/CardTitle";
import CardActions from "react-toolbox/lib/card/CardActions";
import CardText from "react-toolbox/lib/card/CardText";
import Input from "react-toolbox/lib/input/Input";
import Button from "react-toolbox/lib/button/Button";

import '../assets/react-toolbox/theme.css';
import theme from '../assets/react-toolbox/theme.js';
import ThemeProvider from 'react-toolbox/lib/ThemeProvider';
import Cookies from "js-cookie";
import {jQuery as $} from "jquery";

function Login() {
    checkToken();
    return (
        <ThemeProvider theme={theme}>
            <div>
                <Card>
                    <CardTitle>Authorization</CardTitle>
                    <CardText>
                        <Input label="Login"/>
                        <Input type="password" label="Password"/>
                    </CardText>
                    <CardActions>
                        <Button label="Login" onClick={getTokenByLogin}/>
                        <Button label="Register" onClick={getTokenByRegister}/>
                    </CardActions>
                </Card>
            </div>
        </ThemeProvider>
    );
}

function checkToken() {
    if (Cookies.get("jwt-token")) {
        if (!document.referrer.includes("login"))
            returnBack();
    }
}

function returnBack() {
    window.location.replace(document.referrer);
}

function getTokenByLogin() {
    Cookies.set("jwt-token", "1235");
    returnBack();
    return;
    $.ajax({
        type: "POST",
        url: "/api/login",
        data: {},
        dataType: "json",
        success: function (response) {
            
        }
    });
    
}
function getTokenByRegister() {
    Cookies.set("jwt-token", "1234");
    returnBack();

    $.ajax({
        type: "POST",
        url: "/api/register",
        data: {},
        dataType: "json",
        success: function (response) {
            
        }
    });
}

export default Login;