function validate(x, y, r) {
    console.log("X: "+x+" Y: "+Number(y)+" R: "+r);

    return isXCoordinateCorrect(x) && isYCoordinateCorrect(y) && isRadiusCorrect(r);
}

function isXCoordinateCorrect(coordinateX) {
    if (coordinateX == "") {
        alert("Выберите значение координаты x из предложенных");
        return false;
    }

    if (!isNumeric(coordinateX)) {
        alert("Координата X должна быть числом");
    }
    return true;
}

function isYCoordinateCorrect(coordinateY) {
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
    let digitsAfterPoint = number.match(/(?<=\.)\d*/);
    if (digitsAfterPoint === null || digitsAfterPoint === undefined) {
        return 0;
    }
    return digitsAfterPoint[0].length;
}

function isNumeric(string) {
   return !isNaN(string); 
}

function isRadiusCorrect(radius) {
    if (!isNumeric(radius)) {
        alert("Радиус должен быть числом");
        return false;
    }

    if (radius < 0) {
        alert("Радиус должен быть неотрицательным");
        return false;
    }

    return true;
}