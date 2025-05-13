document.write("<h2> This is a heading made inside a script </h2>");
console.log("This is a console message, made inside a script");

document.getElementById("testButton").addEventListener("click", displayText);

function displayText() {
    const testMessage = document.getElementById("testMessage");
    testMessage.innerHTML = "SURPRISE TEXT, ARE YOU SURPRISED OR NOT?";

    // Crear y mostrar otro botón
    const newButton = document.createElement("button");
    newButton.innerHTML = "How Dare You Click on that button?";
    newButton.id = "newButton";
    document.body.appendChild(newButton);

    // Agregar evento al nuevo botón
    newButton.addEventListener("click", displayText2);
}

function displayText2() {
    const newButtonMessage = document.getElementById("newButtonMessage");
    newButtonMessage.innerHTML = "AHA! ARE YOU SURPRISED NOW?";

    // Crear y mostrar otro botón
    const newButton2 = document.createElement("button");
    newButton2.innerHTML = "DO NOT CLICK THIS, I SWEAR IT DOES NOTHING.";
    newButton2.id = "newButton2";
    document.body.appendChild(newButton2);

    // Agregar evento al nuevo botón
    newButton2.addEventListener("click", displayImage);
}
function displayImage() {
    const imageContainer = document.getElementById("imageContainer");
    imageContainer.innerHTML =
        '<img src="TestImage.png" alt="Test Image" style="width: 500px; height: 500px;"/>';
}