
function registerCreateGameFormAction() {
    $(document).on("click", "#create-game-button", function () {
        let form = $("form#create-game-form");
        form.submit(function (event) {
            event.preventDefault();
            event.stopPropagation();
        }, false);
        form.validate();
        if (form.valid() === true) {
            $("#create-game-button").prop("disabled", true);
            stompClient.send("/app/game/Create.New", {}, JSON.stringify({"initialNumber": $("#input-first-number").val()}))
        }
    });
}

function registerJoinGameFormAction() {
    $(document).on("click", "#join-game-button", function () {

        let form = $("form#join-game-form");
        form.submit(function (event) {
            event.preventDefault();
            event.stopPropagation();
        }, false);
        form.validate();
        if (form.valid() === true) {
            $("#join-game-button").prop("disabled", true);
            stompClient.send("/app/game/Player.Add", {}, JSON.stringify({
                "gameId": $("#join-game-id").val(),
                "playerDisplayName": $("#join-player-name").val()
            }))
        }
    });
}

function sendDecision(decision, automatic) {
    stompClient.send("/app/game/Player.Play", {}, JSON.stringify({
        "gameId": gameId,
        "turnDecision": decision,
        "automatic": automatic
    }));
}

function registerDecisionButtonsActions() {

    $(document).on("click", "#minusonebtn", function () {
        sendDecision("MINUS_ONE", false);
    });
    $(document).on("click", "#zerobtn", function () {
        sendDecision("ZERO", false);
    });
    $(document).on("click", "#plusonebtn", function () {
        sendDecision("PLUS_ONE", false);
    });
    $(document).on("click", "#automaticbtn", function () {
        sendDecision(null, true);
    });
}