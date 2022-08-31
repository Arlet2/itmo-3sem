function validate(x, y, r) {
    console.log("X: "+x+" Y: "+Number(y)+" R: "+r);

    return checkXCoordinate(x) && checkYCoordinate(y);
}

function checkXCoordinate(coordinateX) {
    if (coordinateX == "") {
        alert("Выберите значение координаты x из предложенных");
        return false;
    }
    return true;
}

function checkYCoordinate(coordinateY) {
    if (calculateDigitsAfterPoint(coordinateY) >= 16) {
        alert("Введите координату y с меньшей точностью (до 15 знаков после запятой)");
        return false;
    }

    if (coordinateY == "") {
        alert("Поле координаты y является обязательным");
        return false;
    }
    
    if (!isNumeric(coordinateY)) {
        alert("Координата y должна быть числом");
        return false;
    }

    if (coordinateY > 5 || coordinateY < -5) {
        alert("Координата y должна быть от -5 до 5");
        return false;
    }

    return true;
}

function calculateDigitsAfterPoint(number) {
    let digits = number.match(/(?<=\.)\d*/);
    if (digits === null || digits === undefined) {
        return 0;
    }
    return digits[0].length;
}

function isNumeric(string) {
   return !isNaN(string); 
}