<%--
  Created by IntelliJ IDEA.
  User: milko.mitropolitsky
  Date: 11/29/17
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>

    <dir>
        Id:<input class="input" type="text" name="id" value=""><br>
        Name:<input class="input" type="text" name="name" value=""><br>
        Country:   <input class="input" type="text" name="country" value=""><br>
        Manufacturer:<input class="input" type="text" name="manufacturer" value=""><br>
        Type:<input class="input" type="text" name="type" value=""><br>
        Weight:<input class="input" type="text" name="weight" value=""><br>
        Length:    <input class="input" type="text" name="length" value=""><br>
        Width:  <input class="input" type="text" name="width" value=""><br>
        Height:  <input class="input" type="text" name="height" value=""><br>
        Crew:  <input class="input" type="text" name="crew" value=""><br>
        Speed:  <input class="input" type="text" name="speed" value=""><br><br>

        <button onclick="findTank()">Find</button>
        <button onclick="createTank()">Create</button>
    </dir>

    <div id="edit" hidden>
        <input id="id" class="edit_input" type="text" name="id" hidden>
        Name:<input class="edit_input" type="text" name="name" value=""><br>
        Country:   <input class="edit_input" type="text" name="country" value=""><br>
        Manufacturer:<input class="edit_input" type="text" name="manufacturer" value=""><br>
        Type:<input class="edit_input" type="text" name="type" value=""><br>
        Weight:<input class="edit_input" type="text" name="weight" value=""><br>
        Length:    <input class="edit_input" type="text" name="length" value=""><br>
        Width:  <input class="edit_input" type="text" name="width" value=""><br>
        Height:  <input class="edit_input" type="text" name="height" value=""><br>
        Crew:  <input class="edit_input" type="text" name="crew" value=""><br>
        Speed:  <input class="edit_input" type="text" name="speed" value=""><br>
        <button onclick="updateTank()">Edit</button>
    </div>
    <br><br>
    <form action="/api/tanks/upload" method="post" enctype="multipart/form-data">
        <input type="file" accept="text/csv" name="file">
        <input type="submit" value="Bo0om">
    </form>

    <form action="/api/tanks/download" method="GET">
        <input type="submit" value="Download" />
    </form>

    <table>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Country</th>
            <th>Manufacturer</th>
            <th>Type</th>
            <th>Weight</th>
            <th>Length</th>
            <th>Width</th>
            <th>Height</th>
            <th>Crew</th>
            <th>Speed</th>
        </tr>
        <tbody id="table">
        </tbody>
    </table>

    <script>
        $(document).ready(function () {
            getAllTanks();
        })

        function getAllTanks() {
            $.get("/api/tanks", function (data) {
                updateTable(data);
            })
        }

        function getInputData() {
            var query = "";
            $(".input").each(function () {
                if ($(this).val() != "") {
                    query += $(this).attr("name") + "=" + $(this).val() + "&";
                }
            });

            return query;
        }

        function createTank() {
            var query = {};

            $(".input").each(function () {
                query[$(this).attr("name")] = $(this).val();
            });

            $.ajax({
                method: 'POST',
                url: '/api/tanks',
                data: JSON.stringify(query),
                headers: {"Content-Type" : "application/json"},
                success: function (data) {
                    getAllTanks();
                }
            });
        }

        function findTank() {
            $.ajax({
                method: 'GET',
                url: "/api/tanks?" + getInputData(),
                success: function (data) {
                    updateTable(data)
                }
            });
        }

        function deleteTank(id) {
            $.ajax({
                method: "DELETE",
                url: "/api/tanks/" + id,
                success: function () {
                    getAllTanks()
                }
            })
        }

        function editTank(id) {
            $("#id").val(id)
            $("#edit").show()
        }

        function updateTank() {

            var query = {};

            $(".edit_input").each(function () {
                query[$(this).attr("name")] = $(this).val();
            });

            $.ajax({
                method: 'PUT',
                url: '/api/tanks/' +  $("#id").val(),
                data: JSON.stringify(query),
                headers: {"Content-Type" : "application/json"},
                success: function (data) {
                    getAllTanks();
                    $("#edit").hide()
                }
            });
        }

        function updateTable(data) {
            var content = "";
            for(var item in data) {
                var table = "<tr>";
                table += ("<td>" + data[item].id + "</td>");
                table += ("<td>" + data[item].name + "</td>");
                table += ("<td>" + data[item].country + "</td>");
                table += ("<td>" + data[item].manufacturer + "</td>");
                table += ("<td>" + data[item].type + "</td>");
                table += ("<td>" + data[item].weight + "</td>");
                table += ("<td>" + data[item].length + "</td>");
                table += ("<td>" + data[item].width + "</td>");
                table += ("<td>" + data[item].height + "</td>");
                table += ("<td>" + data[item].crew + "</td>");
                table += ("<td>" + data[item].speed + "</td>");
                table += ("<td> <button onclick='editTank(" + data[item].id + ")'>Edit</button> </td>");
                table += ("<td> <button onclick='deleteTank(" + data[item].id + ")'>Delete</button> </td> </tr>");
                content += table
            }

            $("#table").html(content);
        }
    </script>
</body>
</html>
