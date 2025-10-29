document.addEventListener("DOMContentLoaded", () => {
    let modal_overlay = document.querySelector(".modal-overlay");
    let caixa_excluir = document.querySelector("#caixa-excluir");
    let botoes_excluir = document.querySelectorAll(".bt-excluir");
    let botao_cancelar = document.querySelector("#cancelar");
    let botao_efetuar = document.querySelector("#efetuar");
    let servlet_excluir = <%= request.getParameter("servletExclusao")%>;
        console.log(servlet_excluir);

    botoes_excluir.forEach((botao) => {
        botao.addEventListener("click", () => {
            modal_overlay.classList.add("mostrar-excluir");
            caixa_excluir.classList.add("mostrar-excluir");
        })
    })

    botao_cancelar.addEventListener("click", () => {
        modal_overlay.classList.remove("mostrar-excluir");
        caixa_excluir.classList.remove("mostrar-excluir");
    });

    botao_efetuar.addEventListener("click", () => {
        window.location.href = "";
    })
});