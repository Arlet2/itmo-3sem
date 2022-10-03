<%@ page import="data.Row" %>
<%@ page import="java.util.Optional" %>
<%@ page import="model.DataSaver" %>
<%@ page import="java.util.List" %>
<%@ page import="model.RowsPrinting" %>
<%@ page import="static java.lang.System.out" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Лабораторная работа №2</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

    <link rel="stylesheet" href="style.css">
</head>
<body>
<table class="mainTable">
    <tr class="head">
        <table class="head">
            <tr>
                <td class="head"><p>Шульга Артём Игоревич P32111</td>
                <td class="head"><p>Вариант - <b>1167</b></td>
            </tr>
        </table>
    </tr>
    <tr>
        <table class="mainBody">
            <tr class="task">
                <p class="task">Разработать веб-приложение на базе сервлетов и JSP, определяющее попадание точки на координатной плоскости в заданную область.</p>
                <p class="task">Приложение должно быть реализовано в соответствии с шаблоном MVC и состоять из следующих элементов:</p>
                <ul>
                    <li class="task">ControllerServlet, определяющий тип запроса, и, в зависимости от того, содержит ли запрос информацию о координатах точки и радиусе, делегирующий его обработку одному из перечисленных ниже компонентов. Все запросы внутри приложения должны передаваться этому сервлету (по методу GET или POST в зависимости от варианта задания), остальные сервлеты с веб-страниц напрямую вызываться не должны.</li>
                    <li class="task">AreaCheckServlet, осуществляющий проверку попадания точки в область на координатной плоскости и формирующий HTML-страницу с результатами проверки. Должен обрабатывать все запросы, содержащие сведения о координатах точки и радиусе области.</li>
                    <li class="task">Страница JSP, формирующая HTML-страницу с веб-формой. Должна обрабатывать все запросы, не содержащие сведений о координатах точки и радиусе области.</li>
                </ul>
                <p class="task"><b>Разработанная страница JSP должна содержать:</b></p>
                <ol>
                    <li class="task">"Шапку", содержащую ФИО студента, номер группы и номер варианта.</li>
                    <li class="task">Форму, отправляющую данные на сервер.</li>
                    <li class="task">Набор полей для задания координат точки и радиуса области в соответствии с вариантом задания.</li>
                    <li class="task">Сценарий на языке JavaScript, осуществляющий валидацию значений, вводимых пользователем в поля формы.</li>
                    <li class="task">Интерактивный элемент, содержащий изображение области на координатной плоскости (в соответствии с вариантом задания) и реализующий следующую функциональность:
                        <ul>
                            <li class="task">Если радиус области установлен, клик курсором мыши по изображению должен обрабатываться JavaScript-функцией, определяющей координаты точки, по которой кликнул пользователь и отправляющей полученные координаты на сервер для проверки факта попадания.</li>
                            <li class="task">В противном случае, после клика по картинке должно выводиться сообщение о невозможности определения координат точки.</li>
                            <li class="task">После проверки факта попадания точки в область изображение должно быть обновлено с учётом результатов этой проверки (т.е., на нём должна появиться новая точка).</li>
                        </ul>
                    </li>
                    <li class="task">Таблицу с результатами предыдущих проверок. Список результатов должен браться из контекста приложения, HTTP-сессии или Bean-компонента в зависимости от варианта.</li>
                </ol>
                <p class="task"><b>Страница, возвращаемая AreaCheckServlet, должна содержать:</b>
                <ol>
                    <li class="task">Таблицу, содержащую полученные параметры.</li>
                    <li class="task">Результат вычислений - факт попадания или непопадания точки в область.</li>
                    <li class="task">Ссылку на страницу с веб-формой для формирования нового запроса.</li>
                </ol>
                <p class="task">Разработанное веб-приложение необходимо развернуть на сервере WildFly. Сервер должен быть запущен в standalone-конфигурации, порты должны быть настроены в соответствии с выданным portbase, доступ к http listener'у должен быть открыт для всех IP.
            </tr>
            <tr><h2><b>Ввод данных</b></h2></tr>
            <tr>
                <td>
                    <img src="images/info.png">
                </td>
                <td>
                    <form name="coords">
                        <table class="form">
                            <tr>
                                <p>X:
                              <%
                                for(float i=-2;i<=2; i+=0.5) {
                                  out.println(i+": "+"<input name=\"x\" type=\"checkbox\" value = \""+i+"\">");
                                }
                              %>
                            </tr>
                            <tr>
                                <p>Y:
                                    <input type="text" name="y" require placeholder="-3..3">
                            </tr>
                            <tr>
                                <p>R:
                              <%
                                for(int i=1;i<=5; i++) {
                                  out.println(i+": "+"<input type=\"checkbox\" name=\"r\" value = \""+i+"\">");
                                }
                              %>
                            </tr>
                            <tr>
                                <input type="button" value="Отправить" onclick="sendCoordinates();">
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </tr>
    <tr>
        <h2><b>Таблица данных (последние <%=DataSaver.MAX_ROWS%> записей)</b></h2>
    </tr>
    <tr>
        <table class="results">
            <!--<th>Время</td> -->
            <th>Координаты
            </td>
            <th>Попадание
            </td>
            <!--<th>Время выполнения скрипта</td> -->
            <tfoot id="receivingData">
            <%
                List<Row> rows = DataSaver.loadData(session);
                if (Optional.ofNullable(rows).isPresent()) {
                    for (Row row : rows)
                        out.println(row.toString());
                }
            %>
            </tfoot>
        </table>
    </tr>
</table>
</body>
<script src="js/coords-sender.js"></script>
<script src="js/form-checker.js"></script>
<script src="js/input-handler.js"></script>
</html>
