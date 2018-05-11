
window.addEventListener('load', () => {



    document.querySelectorAll("*[data-trigger-dialog]").forEach((e) => {
        let vendoDivSenha = localStorage.getItem("vendoDivSenha");
        if (vendoDivSenha) {
            let id = e.getAttribute('data-trigger-dialog');
            mostrarUpdateSenhaDiv(id);
        }

        e.addEventListener('click', () => {
            let id = e.getAttribute('data-trigger-dialog');
            mostrarUpdateSenhaDiv(id);


            let vendoDivSenha = localStorage.getItem("vendoDivSenha");

            if (vendoDivSenha) {
                localStorage.removeItem("vendoDivSenha");
            } else {
                localStorage.setItem("vendoDivSenha", true);
            }
        })
    });

});


function mostrarUpdateSenhaDiv(id) {

    document.getElementById("titulo").classList.toggle('displayNone');
    document.getElementById("informacoes").classList.toggle('displayNone');
    document.querySelector("#" + id).classList.toggle('show');
    document.querySelector("#mudarSenha").classList.toggle('telaCheia');
    document.querySelector("#mudarSenha div").classList.toggle('telaCheia');

    var text = document.querySelector("#btnMostrarUpdateSenha").firstChild;
    text.data = text.data == "Alterar Senha" ? "Voltar" : "Alterar Senha";


}
