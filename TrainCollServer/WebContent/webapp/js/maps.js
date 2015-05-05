
var map;

function initializeMap() {
	var mapOptions = {
			center: { lat: stationLat, lng: stationLng},
			zoom: 14
	};
	map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
}

function updateTrainPosition(train){
	var color;
	if (train.id in trains){
		color = trains[train.id].marker.styleIcon.color;
		trains[train.id].marker.setMap(null);
		delete trains[train.id].marker;
	}else{
		color='#'+Math.floor(Math.random()*16777215).toString(16)
	}
	var marker = new StyledMarker({
		styleIcon:new StyledIcon(StyledIconTypes.BUBBLE,{color:color,text:train.id}),
		position: {lat:train.latitude,lng:train.longitude},
		map: map
	});
	trains[train.id]={train:train,marker:marker};
}

function removeTrain(train){
	trains[train.id].marker.setMap(null);
	delete trains[train.id];
}
//TODO remove train

//function addMarker(location) {
//	var marker = new google.maps.Marker({
//		position: location,
//		map: map
//	});
//	markers.push(marker);
//}
//
//function setAllMap(map) {
//	for (var i = 0; i < markers.length; i++) {
//		markers[i].setMap(map);
//	}
//}
//
//function clearMarkers() {
//	setAllMap(null);
//}
//function deleteMarkers() {
//	clearMarkers();
//	markers = [];
//}

