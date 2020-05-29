function loadInitialScene() {
    $("#game-play-scene").hide();
    $("#create-or-join").show();
    $("#game-log-scene").hide();
    $("#decision-box").hide();
}

function loadGamePlayScene() {
    $("#game-play-scene").show();
    $("#create-or-join").hide();
    $("#game-log-scene").show();
    $("#decision-box").hide();
}

function loadTurnPlayScene() {
    $("#game-play-scene").show();
    $("#create-or-join").hide();
    $("#game-log-scene").show();
    $("#decision-box").show();
}