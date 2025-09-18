document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".carousel").forEach((carousel) => {
        const avancar = carousel.querySelector(".avancar");
        const voltar = carousel.querySelector(".voltar");
        const cards = carousel.querySelectorAll(".carousel-card");
        let i = 0;
        const qtdCards = cards.length;

        function moverCards(){
            const distancia = (i * -1) * 120;
            cards.forEach((card) => {
                card.style.transform = `translateX(${distancia}%)`
            })
        }
    
    
        avancar.addEventListener("click", () => {
            i = (i + 1) % qtdCards;
            moverCards();
           
        });
    
        
        voltar.addEventListener("click", () => {
            i = (i - 1 + qtdCards) % qtdCards;
            moverCards();
           
        });

    })


})