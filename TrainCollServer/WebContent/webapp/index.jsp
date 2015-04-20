<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="webapp/resources/Theme-DarkAdmin/css/local.css">
<script src="webapp/resources/Theme-DarkAdmin/js/jquery-1.10.2.min.js"></script>

<script src="webapp/resources/Theme-DarkAdmin/bootstrap/js/bootstrap.min.js"></script>



<style>
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
	bottom:0;
	left:0;
	width: 100%;
	height: 25px;
	color:black;
}

#event-container {
	overflow-y: scroll;
	max-height: 170px;
	position:relative;
	display:box;
}

#send-buttton {
	
}

#new-text {
	width:80%;
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
            
            var wsUri = "ws://localhost:8080/TrainCollServer/collision";
            var websocket;
            function init() {
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
              //  doSend("init");
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
                //websocket.close();
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
            window.addEventListener("load", init, false);
        </script>

	<h1 style="text-align: center;">Hello World WebSocket Client</h1>
	<br>

	<div id="output"></div>

	<div id="chat-window" class="panel panel-primary">
		<div class="panel-heading">
			<h3 class="panel-title">Events</h3>
		</div>
		<div id="event-container" class="panel-body feed">

		</div>
		<div id="new-enevt-box">
			<input id="send-button" onclick="send_message()" value="Send" type="button">
			<input id="new-text" name="message" value="Hello WebSocket!" type="text">
		</div>
	</div>

</body>
</html>