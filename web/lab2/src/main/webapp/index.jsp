<%@ page import="data.Row" %>
<%@ page import="java.util.Optional" %>
<%@ page import="model.DataSaver" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    final int heightOfMap = 600;
    final int widthOfMap = 600;
%>
<!DOCTYPE html>
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
                <img src="images/task.png">
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
                <td class="map">
                    <canvas height="<%=heightOfMap%>" width="<%=widthOfMap%>" class="map"></canvas>
                    <svg class="map" xmlns="http://www.w3.org/2000/svg" width="<%=widthOfMap%>" height="<%=heightOfMap%>" fill="none" viewBox="0 0 701 701">
                        <g filter="url(#a)">
                            <path fill="#5BE983" d="M651 351H351V51c165.685 0 300 134.315 300 300Z"/>
                            <path stroke="#fff" d="M351.5 51.5c165.013.27 298.73 133.987 299 299h-299v-299Z"/>
                        </g>
                        <path fill="#5BE983" d="M351 501V351h300L351 501ZM50 352h300v150H50z"/>
                        <path fill="#000" d="M700.355 351.353a.502.502 0 0 0-.002-.708l-3.192-3.172a.5.5 0 1 0-.705.709l2.837 2.82-2.82 2.836a.5.5 0 0 0 .709.706l3.173-3.191ZM349.21 352v.5h.001l-.001-.5ZM0 352.5h349.21v-1H0v1Zm349.211 0 350.79-1-.002-1-350.79 1 .002 1Z"/>
                        <path fill="#000" d="M350.353.646a.5.5 0 0 0-.707.001l-3.177 3.186a.5.5 0 1 0 .708.707l2.824-2.833 2.832 2.824a.501.501 0 0 0 .707-.708L350.353.646ZM349.5 1l1 700 1-.002-1-700-1 .002Z"/>
                        <defs>
                            <filter id="a" width="308" height="308" x="347" y="51" color-interpolation-filters="sRGB" filterUnits="userSpaceOnUse">
                                <feFlood flood-opacity="0" result="BackgroundImageFix"/>
                                <feColorMatrix in="SourceAlpha" result="hardAlpha" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"/>
                                <feOffset dy="4"/>
                                <feGaussianBlur stdDeviation="2"/>
                                <feComposite in2="hardAlpha" operator="out"/>
                                <feColorMatrix values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"/>
                                <feBlend in2="BackgroundImageFix" result="effect1_dropShadow_3_9"/>
                                <feBlend in="SourceGraphic" in2="effect1_dropShadow_3_9" result="shape"/>
                            </filter>
                        </defs>
                    </svg>
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
                                <input type="button" name="submitButton" value="Отправить">
                            </tr>
                            <tr>
                                <input type="button" name="clearButton" value="Очистить">
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
            <th>Время</td>
            <th>Координаты
            </td>
            <th>Попадание
            </td>
            <th>Время выполнения скрипта</td>
            <tfoot id="receivingData">
            <%
                List<Row> rows = DataSaver.loadData(session);
                if (Optional.ofNullable(rows).isPresent()) {
                    for (Row row : rows)
                        out.println(row.getHTMLRow());
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
<script src="js/map-handler.js"></script>
<script src="js/form-handler.js"></script>
<script src="js/map-clear.js"></script>
</html>
