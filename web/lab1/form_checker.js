function validate() {
    let x = document.forms["coords"]["x"].value;
    let y = document.forms["coords"]["y"].value.replace(',','.').replace(' ',"");
    let r = document.forms["coords"]["r"].value;

    //todo: добавить изменение , при передаче на сервер

    console.log(x+" "+Number(y)+" "+r);

    if (x == "") {
        alert("Выберите значение координаты x из предложенных")
        return false;
    }

    if (calculateDigitsAfterPoint(y) >= 16) {
        alert("Введите координату y с меньшей точностью (до 15 знаков после запятой)");
        return false;
    }

    if (y == "") {
        alert("Поле координаты y является обязательным");
        return false;
    }
    
    if (!isNumeric(y)) {
        alert("Координата y должна быть числом");
        return false;
    }

    if (y > 5 || y < -5) {
        alert("Координата y должна быть от -5 до 5");
        return false;
    }

    return true;
}

function isNumeric(string) {
   return !isNaN(string); 
}

function calculateDigitsAfterPoint(number) {
    return number.match(/(?<=\.)\d*/)[0].length;
}