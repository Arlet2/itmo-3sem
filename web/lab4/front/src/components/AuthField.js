import React from "react";
import { useDispatch, useSelector } from "react-redux";
import Input from "react-toolbox/lib/input/Input";
import { selectIsLogin, selectLogin, selectPassword, selectRepeatedPassword, setLogin, setPassword, setRepeatedPassword } from "../features/auth/authSlice";

let errorLoginMessage = "";
let errorPasswordMessage = "";
let errorRepeatedPasswordMessage = "";

function AuthField() {
    const isLogin = useSelector(selectIsLogin);

    const login = useSelector(selectLogin);
    const password = useSelector(selectPassword);
    const repeatedPassword = useSelector(selectRepeatedPassword);

    const dispatch = useDispatch();

    if (isLogin) {
        return (
            <div>
                <Input required label="Your login" value={login}
                    onChange={(value) => dispatch(setLogin(value))}
                    error={isLoginCorrect(login) ? null : errorLoginMessage} />
                <Input required type="password" label="Your password" value={password}
                    onChange={(value) => dispatch(setPassword(value))}
                    error={isPasswordCorrect(password) ? null : errorPasswordMessage}
                    maxLength={32} />
            </div>
        );
    }
    else {
        return (
            <div>
                <Input required label="Username" value={login}
                    onChange={(value) => dispatch(setLogin(value))}
                    error={isLoginCorrect(login) ? null : errorLoginMessage}
                />

                <Input required type="password" label="Password" value={password}
                    onChange={(value) => dispatch(setPassword(value))}
                    error={isPasswordCorrect(password) ? null : errorPasswordMessage}
                    maxLength={32} />

                <Input required type="password" label="Repeat password" value={repeatedPassword}
                    onChange={(value) => dispatch(setRepeatedPassword(value))}
                    error={isRepeatedPasswordCorrect(password, repeatedPassword) ? null : errorRepeatedPasswordMessage}
                    maxLength={32} />
            </div>
        );
    }
}

function isLoginCorrect(value) {
    if (value === "") {
        errorLoginMessage = "Имя пользователя не может быть пустым";
        return false;
    }

    return true;
}

function isPasswordCorrect(value) {
    if (value === undefined)
        return false;

    if (value === "") {
        errorPasswordMessage = "Пароль не может быть пустым";
        return false;
    }

    if (value.length < 6) {
        errorPasswordMessage = "Пароль должен состоять из не менее 6 символов";
        return false;
    }

    return true;
}

function isRepeatedPasswordCorrect(password, repeatedPassword) {
    if (repeatedPassword === "") {
        errorRepeatedPasswordMessage = "Пароль не может быть пустым";
        return false;
    }

    if (password === repeatedPassword)
        return true;

    errorRepeatedPasswordMessage = "Пароли должны быть одинаковы";
    return false;
}

export default AuthField;