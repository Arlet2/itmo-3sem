import {createSlice} from "@reduxjs/toolkit";

const initialState = {
    x: undefined,
    y: 0,
    r: undefined
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
        }
    }
});

export const { setX, setY, setR } = formSlice.actions;

export const selectX = (state) => state.formHandler.x;
export const selectY = (state) => state.formHandler.y;
export const selectR = (state) => state.formHandler.r;

export default formSlice.reducer;