function validate() {
    let x = document.forms["coords"]["x"].value;
    let y = document.forms["coords"]["y"].value;
    let r = document.forms["coords"]["r"].value;

    y.prototype.replace(",", ".");
    //todo: добавить изменение , при передаче на сервер
    //todo: попробовать сломать с плавающей точкой/пробелами
    
    //todo: валится на пробелах!!!

    if (x == "") {
        alert("Выберите значение координаты x из предложенных")
        return false;
    }
    
    if (!isNumeric(y) || y == "") {
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