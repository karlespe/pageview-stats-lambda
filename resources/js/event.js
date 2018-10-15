var API_ENDPOINT = "https://example.execute-api.us-east-1.amazonaws.com/prod/Event";

var events = [];
var readStart = new Date().getTime();

$(document).ready(function() {

    events.push(createEvent("READ", 1));

    $(".button-informative").click(function() {
        events.push(createEvent("INFORMATIVE", 1));
        hideButtons();
    });
    $(".button-not-informative").click(function() {
        hideButtons();
    });

    $(window).unload(function() {
        var readEnd = new Date().getTime();
        var dwellTimeSeconds = (readEnd - readStart)/1000;
        events.push(createEvent("DURATION", dwellTimeSeconds));
        var event = {
            url: URL,
            events: []
        };
        for (var i = 0; i < events.length; i++) {
            event.events.push({ type: events[i]["type"], value: events[i]["value"] });
        }
        $.ajax({
            url: API_ENDPOINT,
            type: 'POST',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(event),
            async: false
        });
    });

});

function hideButtons() {
    $(".buttons").addClass("hidden");
    $(".buttons-thanks").removeClass("hidden");
}

function createEvent(type, value) {
    var event = [];
    event["type"] = type;
    event["value"] = value;
    return event;
}

