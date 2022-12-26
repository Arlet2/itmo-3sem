import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    x: undefined,
    y: undefined,
    r: undefined,
    formError: undefined
};

const formSlice = createSlice({
    name: "formHandler",
    initialState,
    reducers: {
        setX: (state, action) => {
            if (state.x === action.payload)
                state.x = undefined;
            else
                state.x = action.payload;
        },
        setY: (state, action) => {
            state.y = action.payload;
        },
        setR: (state, action) => {
            if (state.r === action.payload)
                state.r = undefined;
            else
                state.r = action.payload;
        },
        setFormError: (state, action) => {
            state.formError = action.payload;
        }
    }
});

export const { setX, setY, setR, setFormError } = formSlice.actions;

export const selectX = (state) => state.formHandler.x;
export const selectY = (state) => state.formHandler.y;
export const selectR = (state) => state.formHandler.r;
export const selectErrorMessage = (state) => state.formHandler.errorMessage;

export default formSlice.reducer;