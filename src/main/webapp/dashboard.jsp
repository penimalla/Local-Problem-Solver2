<%@ page import="java.util.List,com.penimalla.servlet.Problem" %>
<%
    List<Problem> problems = (List<Problem>) request.getAttribute("problems");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY"></script>
    <style>
        /* Add suitable CSS */
    </style>
</head>
<body>
    <div class="dashboardcard">
        <h2>Reported Issues</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Category</th>
                <th>Priority</th>
                <th>Image</th>
                <th>Location</th>
            </tr>
            <% for (Problem pb : problems) { %>
                <tr>
                    <td><%= pb.getId() %></td>
                    <td><%= pb.getIssueTitle() %></td>
                    <td><%= pb.getCategory() %></td>
                    <td><%= pb.getPriority() %></td>
                    <td>
                        <% if (pb.getImagePath() != null && !pb.getImagePath().isEmpty()) { %>
                            <img src="<%= pb.getImagePath() %>" width="70" height="70"/>
                        <% } else { %>
                            No image
                        <% } %>
                    </td>
                    <td>
                        <div id="map_<%= pb.getId() %>" style="width:150px;height:120px;"></div>
                        <script>
                        function showMap<%= pb.getId() %>() {
                          var latlng = { lat: <%= pb.getLatitude() %>, lng: <%= pb.getLongitude() %> };
                          var map = new google.maps.Map(document.getElementById("map_<%= pb.getId() %>"), {center: latlng, zoom: 15});
                          new google.maps.Marker({position: latlng, map: map});
                        }
                        showMap<%= pb.getId() %>();
                        </script>
                    </td>
                </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
