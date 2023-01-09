import React from "react";
import { selectRows } from "../features/tableHandler/tableSlice";
import { useSelector } from "react-redux";

import "../css/DataTable.css";

function DataTable () {
    const rows = useSelector(selectRows);

    return (
        <div className="DataTable">
            <table>
                <thead>
                    <tr>
                        <td>Date</td>
                        <td>x</td>
                        <td>y</td>
                        <td>r</td>
                        <td>Status</td>
                        <td>Script time</td>
                    </tr>
                </thead>
                <tbody>
                    {rows.map((value) => {
                        return (
                            <tr>
                                <td>{getDateString(new Date(value.date))}</td>
                                <td>{value.x}</td>
                                <td>{value.y}</td>
                                <td>{value.r}</td>
                                <td>{value.status}</td>
                                <td>{(""+value.scriptTime).replace(".", ",")} мс</td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}

function getDateString(date) {
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();

    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();
    let milliseconds = date.getMilliseconds();

    day = addUpToTwoDigits(day);
    month = addUpToTwoDigits(month);

    hours = addUpToTwoDigits(hours);
    minutes = addUpToTwoDigits(minutes);
    seconds = addUpToTwoDigits(seconds);


    return day + "." + month + "." + year + " - " + 
    hours + ":" + minutes + ":" + seconds + "." + milliseconds;
}

function addUpToTwoDigits(value) {
    if (value < 10)
        return "0"+value;
    return value;
}

export default DataTable;