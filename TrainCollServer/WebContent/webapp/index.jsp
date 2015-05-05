<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Train Collision base station</title>

<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/css/local.css">
<link rel="stylesheet" type="text/css" href="webapp/css/style.css">

<script src="webapp/resources/Theme-DarkAdmin/js/jquery-1.10.2.min.js"></script>
<script src="webapp/resources/Theme-DarkAdmin/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9_9b-JjrC8dD-wVlgoVZ_57qEU1ZIYc4"></script>

<script src="webapp/js/StyledMarker.js"></script>
<script src="webapp/js/maps.js"></script>
<script src="webapp/js/websocket.js"></script>


</head>
<body>

	<script type="text/javascript">
	var stationId = "STATION_1";
	var stationLat = 51.67702610655511;
	var stationLng = 19.337782859802246;
	
		$(document).ready(function() {
			initWebSocket();
			initializeMap();
		});
	</script>

	<div id="map-canvas"></div>

	<div id="output"></div>

	<div id="chat-window" class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Events</h3>
		</div>
		<div id="event-container" class="panel-body feed"></div>
		<div id="new-enevt-box">
			<input id="send-button" onclick="sendEvent()" value="Send" type="button">
			<input id="new-text" name="message" value="Hello WebSocket!" type="text">
		</div>
	</div>

</body>
</html>