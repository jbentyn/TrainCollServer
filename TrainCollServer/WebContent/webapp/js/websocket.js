
var wsUri = "ws://localhost:8080/TrainCollServer/collision";
var websocket;
var trains = {};

function onMessage(webSocketMessage){
	console.log(webSocketMessage);
	var msgBody=JSON.parse( webSocketMessage.data);
	var type = msgBody.type;
	console.log(msgBody);
	console.log(msgBody.data);
	if (type==="EVENT"){
		writeToScreen("Message Received: " + msgBody.data)
	}else if (type==="POSITION_UPDATE"){
		updateTrainPosition(msgBody.data);
	}else if (type==="REMOVE_TRAIN"){
		removeTrain(msgBody.data);
	}
}


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

function sendEvent() {
	//TODO message text + priority from input
	var text=$("#new-text").val();
	doSend(
			{"type":"EVENT",
				"data":{
					"priority":"normal",
					"text":text
				} 
			} );
}

function onOpen(evt) {
	writeToScreen("Connected to Endpoint!");
	
	var message= {"type":"BASE_STATION_REGISTER", 
			"data":{
					"id":stationId,
					"latitude":stationLat,
					"longitude":stationLng	
				}
	}
	doSend(message);
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