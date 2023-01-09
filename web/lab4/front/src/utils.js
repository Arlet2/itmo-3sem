import Cookies from "js-cookie";
import { setErrorMessage } from "./features/auth/authSlice";
import { setRows } from "./features/tableHandler/tableSlice";
import { clearFormError, setFormError } from "./features/formHandler/formSlice";
import { clearMap } from "./components/Map";

export function returnBack() {
    window.location.href = document.referrer;
}
export function goToLogin() {
    window.location.replace("/login");
}
export function goToMain() {
    window.location.replace("/");
}

export async function validateToken() {
    if (Cookies.get("jwt-token") == undefined)
        return false;
    let isTokenCorrect = false;
    await fetch("/api/token-validation?token=" + Cookies.get("jwt-token"))
        .then(
            async (result) => {
                if (result.ok) {
                    isTokenCorrect = true;
                    await result.text().then((text) => {
                        isTokenCorrect = text === "true" ? true : false;
                    });
                }
            }
        );
    return isTokenCorrect;
}

export function getAllPoints(dispatch) {
    clearMap();
    fetch("api/secure/points", {
        method: "GET",
        headers: { "Content-Type": "application/json" }
    })
        .then(
            (result) => {
                if (result.ok) {

                    result.text().then(
                        (text) => { dispatch(setRows(JSON.parse(text))); }
                    );
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

export function sendCoordinates(dispatch, x, y, r) {
    dispatch(clearFormError());

    fetch("/api/secure/add-point", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ x: x, y: y, r: r })
    })
        .then(
            (result) => {
                if (result.ok) {
                    getAllPoints(dispatch);
                }
                else {
                    result.text().then(
                        (text) => { dispatch(setFormError(JSON.parse(text).message)) }
                    );
                }
            });
};