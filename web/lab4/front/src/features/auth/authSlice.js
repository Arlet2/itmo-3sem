import { createSlice } from "@reduxjs/toolkit";
import React from "react";

const initialState = {
    login: undefined,
    password: undefined,
    isLogin: true
};

export const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        setLogin: (state, action) => {
            state.login = action.payload;
        },
        setPassword: (state, action) => {
            state.password = action.payload;
        },
        clearPassword: (state) => {
            state.password = undefined;
        }
    }
});

export const {setLogin, setPassword, clearPassword} = authSlice.actions;

export const selectLogin = (state) => state.auth.login;
export const selectPassword = (state) => state.auth.password;

export default authSlice.reducer;