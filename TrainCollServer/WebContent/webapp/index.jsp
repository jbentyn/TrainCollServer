<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/css/local.css">
<script src="webapp/resources/Theme-DarkAdmin/js/jquery-1.10.2.min.js"></script>

<script src="webapp/resources/Theme-DarkAdmin/bootstrap/js/bootstrap.min.js"></script>

<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyA9_9b-JjrC8dD-wVlgoVZ_57qEU1ZIYc4"></script>

<style>
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0;
	padding: 0
}

#map-canvas {
	height: 100%;
	margin: 0;
	padding: 0;
	position: absolute width:100%;
	top: 0;
	left: 0;
}

#chat-window {
	position: fixed;
	width: 40%;
	max-height: 200px;
	bottom: 0;
	right: 0;
	margin: 0;
}

#new-enevt-box {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 25px;
	color: black;
}

#event-container {
	overflow-y: scroll;
	max-height: 170px;
	position: relative;
	display: box;
}

#send-buttton {
	
}

#new-text {
	width: 80%;
}

.event-header {
	box-sizing: border-box;
}

.feed-item::before, .feed-item::after {
	content: " ";
	display: block;
	height: auto;
}
</style>
<meta charset="utf-8">
<title>Your First WebSocket!</title>
</head>
<body>

	<script type="text/javascript">
	var markers = [];
	
	function addMarker(location) {
		  var marker = new google.maps.Marker({
		    position: location,
		    map: map
		  });
		  markers.push(marker);
		}
	
	function setAllMap(map) {
	  for (var i = 0; i < markers.length; i++) {
	    markers[i].setMap(map);
	  }
	}

	function clearMarkers() {
	  setAllMap(null);
	}
	function deleteMarkers() {
		  clearMarkers();
		  markers = [];
		}
	
	function initializeMap() {
        var mapOptions = {
          center: { lat: -34.397, lng: 150.644},
          zoom: 8
        };
       map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
      }
      google.maps.event.addDomListener(window, 'load', initializeMap);
      
      
            var wsUri = "ws://localhost:8080/TrainCollServer/collision";
            var websocket;
            function initWebSocket() {
                websocket = new WebSocket(wsUri);
                websocket.onopen = function(evt) {
                    onOpen(evt)
                };
                websocket.onmessage = function(evt) {
                    onMessage(evt)
                };
                websocket.onerror = function(evt) {
                    onError(evt)
                };
            }
            function send_message() {
            	doSend(
            			{"type":"EVENT",
            				"data":{
            					"priority":"normal",
            					"text":"some text"
            					} 
            			} );
            }
            function onOpen(evt) {
                writeToScreen("Connected to Endpoint!");
            }
            function onMessage(evt) {
                writeToScreen("Message Received: " + evt.data);
            }
            function onError(evt) {
                writeToScreen('ERROR: ' + evt.data);
            }
            function doSend(message) {
            	var jsonToSend = JSON.stringify(message)
                writeToScreen("Message Sent: " + jsonToSend);
                websocket.send(jsonToSend);
            }
            function writeToScreen(message) {
                var section = document.createElement("section");
                section.className="feed-item";
                var div = document.createElement("div");
                div.className="feed-item-body";
                
                var textDiv =document.createElement("div");
                textDiv.className="text";
                textDiv.innerHTML = message;
                 
                div.appendChild(textDiv);
                section.appendChild(div);
                $('#event-container').append(section);
            }
            window.addEventListener("load", initWebSocket, false);
        </script>

	<div id="map-canvas"></div>

	<div id="output"></div>

	<div id="chat-window" class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Events</h3>
		</div>
		<div id="event-container" class="panel-body feed"></div>
		<div id="new-enevt-box">
			<input id="send-button" onclick="send_message()" value="Send" type="button">
			<input id="new-text" name="message" value="Hello WebSocket!" type="text">
		</div>
	</div>

</body>
</html>