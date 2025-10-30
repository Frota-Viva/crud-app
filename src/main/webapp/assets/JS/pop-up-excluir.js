document.addEventListener("DOMContentLoaded", () => {
    const modal_overlay = document.querySelector(".modal-overlay");
    const caixa_excluir = document.querySelector("#caixa-excluir");
    const botoes_excluir = document.querySelectorAll(".bt-excluir");
    const botao_cancelar = document.querySelector("#cancelar");
    const botao_efetuar = document.querySelector("#efetuar");
    let servletExclusao = caixa_excluir.dataset.servlet;
    let idExcluir;

    botoes_excluir.forEach((botao) => {
        botao.addEventListener("click", () => {
            modal_overlay.classList.add("mostrar-excluir");
            caixa_excluir.classList.add("mostrar-excluir");
            idExcluir = botao.dataset.id;
        })
    })

    botao_cancelar.addEventListener("click", () => {
        modal_overlay.classList.remove("mostrar-excluir");
        caixa_excluir.classList.remove("mostrar-excluir");
    });

    botao_efetuar.addEventListener("click", () => {
         window.location.href = `${servletExclusao}?id=${idExcluir}`;
    })
});