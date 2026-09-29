// Traffic Signal FSM

const states = [
    {
        name: "NORTH / SOUTH GREEN",
        duration: 5,
        northSouth: "green",
        eastWest: "red"
    },

    {
        name: "NORTH / SOUTH YELLOW",
        duration: 2,
        northSouth: "yellow",
        eastWest: "red"
    },

    {
        name: "EAST / WEST GREEN",
        duration: 5,
        northSouth: "red",
        eastWest: "green"
    },

    {
        name: "EAST / WEST YELLOW",
        duration: 2,
        northSouth: "red",
        eastWest: "yellow"
    }
];

let currentState = 0;
let timeLeft = states[currentState].duration;

const stateText = document.getElementById("state");
const timerText = document.getElementById("timer");

function setLights(direction, colour) {

    const lights = document.querySelectorAll("." + direction + " .light");

    lights.forEach(light => {
        light.style.background = "#292929";
    });

    const activeLight = document.querySelector(
        "." + direction + " ." + colour
    );

    activeLight.style.background = colour;
}

function updateTrafficLights() {

    const state = states[currentState];

    stateText.textContent = state.name;
    timerText.textContent = timeLeft;

    setLights("north", state.northSouth);
    setLights("south", state.northSouth);

    setLights("east", state.eastWest);
    setLights("west", state.eastWest);
}

function changeState() {

    currentState++;

    if (currentState >= states.length) {
        currentState = 0;
    }

    timeLeft = states[currentState].duration;

    updateTrafficLights();
}

updateTrafficLights();

setInterval(() => {

    timeLeft--;

    if (timeLeft <= 0) {
        changeState();
    } else {
        timerText.textContent = timeLeft;
    }

}, 1000);