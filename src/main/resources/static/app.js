(function () {

	var ws;
	var client;

	$(document).ready(function () {
		$("#connectButton").click(function () {
			ws = new SockJS($('#url').val().trim());
			client = Stomp.over(ws);
			client.connect({}, function(frame) {
				console.log(frame);
				
				client.subscribe("/topic/bids", function(reply) {
					console.log("recieved: " + reply);
					$("#messages ul").append("<li>"+reply.body+"</li>");
				});
				
				client.subscribe("/topic/chat", function(reply) {
					console.log("recieved: " + reply);
					$("#chat ul").append("<li>"+reply.body+"</li>");
				});
			});
		});
	});

	$(document).ready(function () {
		$("#submitButton").click(function () {
			client.send("/app/chat", {priority: 9}, $('#message').val().trim());
		});
	});






})();