function handleGameCreatedEvent(data) {

    gameId = data.gameId;

    stompClient.send("/app/game/Player.Add", {}, JSON.stringify({
        "gameId": gameId,
        "playerDisplayName": $("#input-player-name").val()
    }));

    $("#statuslabel").text("Game created with ID[" + data.gameId + "], pass it to your friend to start playing");
    $("#lastNumberLabel").text(data.startNumber);
}

function handlePlayerAddedEvent(data) {

    loadGamePlayScene();

    if (userId === data.playerId) {
        $("#logslabel").append("<br><br>* You have joined the game.");
        //fill in :in case this is not the game starter
        gameId = data.gameId;
        $("#lastNumberLabel").text(data.startNumber);
    } else {
        $("#logslabel").append("<br><br>* " + data.playerDisplayName + " has joined the game.");
    }
}

function handleNextTurnEvent(data) {

    $("#lastNumberLabel").text(data.currentNumber);
    loadGamePlayScene();

    //last player was me
    if (data.lastPlayerId === userId) {

        $("#logslabel").append("<br><br>* You played " + data.lastDecision + ", and made it to "+ data.currentNumber);
    }
    //last player is someone else
    else {

        $("#logslabel").append("<br><br>* " + data.lastPlayerDisplayName + " played " + data.lastDecision + ", and made it to "+ data.currentNumber);
    }

    //my turn
    if (data.nextPlayerId === userId) {

        $("#logslabel").append("<br><br>* This is your turn, " + data.nextPlayerName + ".");

        //handle auto check
        if ($("#autocheck").prop("checked") == true) {
            $("#logslabel").append("<br> You are playing automatically ...");
            $("#automaticbtn").click();

        } else if ($("#autocheck").prop("checked") == false) {
            loadTurnPlayScene();
            $("#logslabel").append("<br> Please decide for the operation that would let the given number be divisible by 3 without a remainder");
        }

    }
    //others turn
    else {
        $("#logslabel").append("<br><br>* " + data.nextPlayerName + " is thinking ...");
    }

}

function handleGameFinishedEvent(data) {
    loadGamePlayScene();
    $(".autocheckcontainer").hide();
    $("#lastNumberLabel").text(data.currentNumber);

    if (data.gameWon === true) {

        if (data.lastPlayerId === userId) {
            $("#logslabel").append("<br><br>* You decided for " + data.lastDecision + " and you Won the Game, Congratulations !");
        } else {
            $("#logslabel").append("<br><br>* " + data.lastPlayerDisplayName + " has decided for " + data.lastDecision + " and won the game, hard luck !");
        }

    } else {

        if (data.lastPlayerId === userId) {
            $("#logslabel").append("<br><br>* You decided for " + data.lastDecision + " and the game was finished without winners");
        } else {
            $("#logslabel").append("<br><br>* " + data.lastPlayerDisplayName + " has decided for " + data.lastDecision + " and finished the game without winners");
        }

    }


}