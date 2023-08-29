const boton = document.getElementById("boton");
const fondo = document.body;

boton.addEventListener("click", function() {
    const randomColor = getRandomColor();
    fondo.style.backgroundColor = randomColor;
});

function getRandomColor() {
    const letters = "0123456789ABCDEF";
    let color = "#";
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}