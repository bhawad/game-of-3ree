let userId = null;
let gameId = null;
let stompClient = null;

$(function () {

    //adjust status message
    $("#statuslabel").text("Connecting to Server ..");

    //open websocket connection
    connect();

});


function connect() {
    let socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {

        //record userid
        userId = frame.headers["user-name"];

        //adjust status message
        $("#statuslabel").text("Connected to Server.");

        subscribeToUserReply();

        subscribeToUserError();

        loadInitialScene();

        registerCreateGameFormAction();

        registerJoinGameFormAction();

        registerDecisionButtonsActions();
    });
}

function subscribeToUserError() {

    stompClient.subscribe("/user/queue/error", function (payload) {
        let errorMessage = payload.body;
        $("#errorlabel").text(errorMessage);

        setTimeout(function () {
            $("#errorlabel").text("");
        }, 5000);
    });

}

function subscribeToUserReply() {
    stompClient.subscribe("/user/queue/reply", function (payload) {
        let body = JSON.parse(payload.body);
        let type = body.type;

        switch (type) {
            case "GAME_CREATED":
                handleGameCreatedEvent(body);
                break;
            case "PLAYER_ADDED":
                handlePlayerAddedEvent(body);
                break;
            case "NEXT_TURN":
                handleNextTurnEvent(body);
                break;
            case "GAME_FINISHED":
                handleGameFinishedEvent(body);
                break;
        }
    });
}