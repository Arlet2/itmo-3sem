import React from "react";
import { selectRows } from "../features/tableHandler/tableSlice";
import { useSelector } from "react-redux";

function DataTable () {
    const rows = useSelector(selectRows);

    console.log(rows);

    return (
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
                            <td>{value.date/*getDateString(value.date)*/}</td>
                            <td>{value.x}</td>
                            <td>{value.y}</td>
                            <td>{value.r}</td>
                            <td>{value.status}</td>
                            <td>{value.scriptTime} мс</td>
                        </tr>
                    );
                })}
            </tbody>
        </table>
    );
}

function getDateString(date) {
    return date.getDate() + "." + date.getMonth() + "." + date.getFullYear() + " - " + 
    date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "::" + date.getMilliseconds();
}

export default DataTable;