import Cookies from "js-cookie";

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
    await fetch("http://localhost:8080/api/token-validation?token=" + Cookies.get("jwt-token"))
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