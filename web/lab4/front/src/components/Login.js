import React from "react";

import Card from "react-toolbox/lib/card/Card";
import CardTitle from "react-toolbox/lib/card/CardTitle";
import CardActions from "react-toolbox/lib/card/CardActions";
import CardText from "react-toolbox/lib/card/CardText";
import Button from "react-toolbox/lib/button/Button";

import '../assets/react-toolbox/theme.css';
import theme from '../assets/react-toolbox/theme.js';
import ThemeProvider from 'react-toolbox/lib/ThemeProvider';
import Cookies from "js-cookie";
import AuthField from "./AuthField";
import { useDispatch, useSelector } from "react-redux";
import {
    clearErrorMessage, clearPassword, selectErrorMessage, selectIsLogin, selectLogin,
    selectPassword, selectRepeatedPassword, setErrorMessage, switchIsLogin
} from "../features/auth/authSlice";
import { goToMain, returnBack, validateToken } from "../utils";
import Header from "./Header";
import AppBar from "react-toolbox/lib/app_bar/AppBar";

function Login() {
    checkToken();

    const isLogin = useSelector(selectIsLogin);
    const login = useSelector(selectLogin);
    const password = useSelector(selectPassword);
    const repeatedPassword = useSelector(selectRepeatedPassword);
    const errorMessage = useSelector(selectErrorMessage);

    let buttonInfo = { name: undefined, action: undefined };
    let switchButtonInfo = undefined;

    const dispatch = useDispatch();

    const validateFields = () => {
        if (login === "" || login === undefined) {
            dispatch(setErrorMessage("Логин не может быть пустым"));
            return false;
        }

        if (password === "" || password === undefined) {
            dispatch(setErrorMessage("Пароль не может быть пустым"));
            return false;
        }

        if (password.length < 6) {
            dispatch(setErrorMessage("Пароль должен состоять из не менее 6 символов"));
            return false;
        }

        return true;
    };

    const getTokenByLogin = () => {
        if (!validateFields())
            return;

        clearErrorMessage();

        fetch("api/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login: login, password: password })
        })
            .then(
                (result) => {
                    if (result.ok) {

                        result.text().then(
                            (text) => { Cookies.set("jwt-token", text); }
                        );
                        dispatch(clearPassword());
                        returnBack();
                    }
                    else {
                        if (result.status === 504)
                            dispatch(setErrorMessage("Сервер недоступен"));
                        else
                            result.text().then(
                                (text) => { dispatch(setErrorMessage(JSON.parse(text).message)) }
                            );
                    }
                });
    };

    const getTokenByRegister = () => {
        if (!validateFields())
            return;

        if (password !== repeatedPassword) {
            dispatch(setErrorMessage("Пароли должны быть одинаковы"));
            return;
        }

        clearErrorMessage();

        fetch("api/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ login: login, password: password })
        })
            .then(
                (result) => {
                    if (result.ok) {

                        result.text().then(
                            (text) => { Cookies.set("jwt-token", text); }
                        );
                        
                        dispatch(clearPassword());

                        returnBack();
                    }
                    else {
                        if (result.status === 504)
                            dispatch(setErrorMessage("Сервер недоступен"));
                        else
                            result.text().then(
                                (text) => { dispatch(setErrorMessage(JSON.parse(text).message)) }
                            );
                    }
                });
    };

    if (isLogin) {
        buttonInfo = { name: "Sign in", action: getTokenByLogin };
        switchButtonInfo = "Switch to register";
    }
    else {
        buttonInfo = { name: "Sign up", action: getTokenByRegister };
        switchButtonInfo = "Switch to log in";
    }

    return (
        <ThemeProvider theme={theme}>
            <div>
                <AppBar title="Выполнил Шульга Артём P32111. Вариант: 1139"/>
                <Card>
                    <CardTitle>Authorization</CardTitle>
                    <CardText>
                        <AuthField />
                    </CardText>
                    <CardActions>
                        <Button label={buttonInfo.name} onClick={() => buttonInfo.action()} />
                        <Button label={switchButtonInfo} onClick={() => dispatch(switchIsLogin())} />
                    </CardActions>
                    <p>{errorMessage}</p>
                </Card>
            </div>
        </ThemeProvider>
    );
}

function checkToken() {
    validateToken().then(
        (result) => {
            if (result) {
                if (!document.referrer.includes("login"))
                    returnBack();
                else
                    goToMain();
            } else {
                Cookies.remove("jwt-token");
            }
        }
    );
}

export default Login;