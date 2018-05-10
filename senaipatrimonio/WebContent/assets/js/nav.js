//Variavel auxiliar
var isPressed = false;

//quando carrega a pagina
window.addEventListener('load', () => {
    windowSize();
    setButtonClick();
});

//quando a pagina é redimencionada
window.addEventListener("resize", () => {
    windowSize();
});


//função que setta o click do botão
function setButtonClick() {

    //pega o botão e coloca uma fução para o onClick
    document.getElementById("abrirNav").onclick = () => {
        if (isPressed) {
            notPressedButton();
        } else {
            pressedButton();
        }
    };
}


//Função para exibir(pc) ou não (mobile) a barra de navegção
function windowSize() {
    notPressedButton();
    
    const mq = window.matchMedia("(max-width: 690px)");
    if (mq.matches) {
        document.querySelectorAll(".headerNav").forEach((e) => {
            e.classList.add("displayNone");
        });
    } else {
        document.querySelectorAll(".headerNav").forEach((e) => {
            e.classList.remove("displayNone");
        });
    }
}


//Função exibir a barra de navegação mobile
function pressedButton() {
    document.querySelectorAll(".headerNav").forEach((e) => {
        e.classList.add("showOnMobile");
        e.classList.add("positionFixed");
        e.classList.remove("displayNone");
    });

    document.querySelectorAll("nav").forEach((e) => {
        e.classList.add("notPositionFixed");

    });

    isPressed = true;
}

//Função para esconder a barra de navegação mobile
function notPressedButton() {
    document.querySelectorAll(".headerNav").forEach((e) => {
        e.classList.remove("showOnMobile");
        e.classList.remove("positionFixed");
        e.classList.add("displayNone");
    });

    document.querySelectorAll("nav").forEach((e) => {
        e.classList.remove("notPositionFixed");

    });

    isPressed = false;
}