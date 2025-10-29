<!DOCTYPE html>
<html lang="en">
<head>
    <title>Local Problem Solver</title>
    <meta charset="UTF-8">
    <link href="https://fonts.googleapis.com/css?family=Inter:400,700&display=swap" rel="stylesheet">
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY"></script>
    <style>
        /* Add your CSS styles for good UI */
    </style>
</head>
<body>
    <div class="maincard">
        <h2>Report Issue</h2>
        <form method="post" action="add-problem" enctype="multipart/form-data">
            <!-- All form fields as before -->
            <label for="problemImage">Upload Image</label>
            <input type="file" id="problemImage" name="problemImage" accept="image/*">

            <label for="map">Select Location</label>
            <div id="map" style="height:300px;"></div>
            <input type="hidden" id="latitude" name="latitude">
            <input type="hidden" id="longitude" name="longitude">

            <input type="submit" value="Submit Report">
        </form>
    </div>
    <script>
    var map, marker;
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {center: {lat: 17.3850, lng: 78.4867}, zoom: 12});
        map.addListener('click', function(e) {
            placeMarker(e.latLng);
            document.getElementById('latitude').value = e.latLng.lat();
            document.getElementById('longitude').value = e.latLng.lng();
        });
    }
    function placeMarker(location) {
        if (marker) marker.setMap(null);
        marker = new google.maps.Marker({position: location, map: map});
    }
    window.onload = initMap;
    </script>
</body>
</html>
