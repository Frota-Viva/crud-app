document.addEventListener('DOMContentLoaded', () => {
//     Fazendo o gráfico das informações dos caminhões
    const ctxCaminhoes = document.getElementById("graficoCaminhoes");
    let totalCaminhoes = document.getElementById("totalCaminhoes").textContent;
    let inativosCaminhoes = document.getElementById("inativosCaminhoes").textContent;
    let manutencaoCaminhoes = document.getElementById("manutencaoCaminhoes").textContent;
    let ativosCaminhoes = document.getElementById("ativosCaminhoes").textContent;

    new Chart(ctxCaminhoes, {
        type: 'doughnut',
        data: {
            labels: ['Ativos', 'Manutenção', 'Inativos'],
            datasets: [{
                data: [10, 15, 20],
                backgroundColor: ['rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)'],
                borderWidth: 2,
                borderColor: ['rgba(0, 255, 102, 1)',
                    'rgba(0, 123, 255, 1)',
                    'rgba(255, 51, 51, 1)'],
                spacing: 4
            }]
        },
        options: {
            cutout: '70%', // deixa o "buraco" no meio maior
            // plugins: {
            //     legend: {
            //         position: 'bottom',
            //         labels: {
            //             color: 'white',
            //             boxWidth: 10,
            //             boxHeight: 5,
            //             usePointStyle : true,
            //             pointStyle: 'rounded',
            //             font: {
            //                 size: 16
            //             }
            //         }
            //     }
            // }
        }
    });

    // Fazendo o gráfico das entregas

    const ctxEntregas = document.getElementById("graficoEntregas");
    let totalEntregas = document.getElementById("totalEntregas").textContent;
    let realizadasEntregas = document.getElementById("realizadasEntregas").textContent;
    let aCaminhoEntregas = document.getElementById("manutencaoCaminhoes").textContent;

    new Chart(ctxEntregas, {
        type: 'doughnut',
        data: {
            labels: ['Realizadas', 'À caminho', 'Pendentes'],
            datasets: [{
                data: [10, 15, 20],
                backgroundColor: ['rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)'],
                borderWidth: 2,
                borderColor: ['rgba(0, 255, 102, 1)',
                    'rgba(0, 123, 255, 1)',
                    'rgba(255, 51, 51, 1)'],
                spacing: 4
            }]
        },
        options: {
            cutout: '70%', // deixa o "buraco" no meio maior
            // plugins: {
            //     legend: {
            //         position: 'bottom',
            //         align: 'center',
            //         labels: {
            //             color: 'white',
            //             boxWidth: 10,
            //             boxHeight: 5,
            //             usePointStyle : true,
            //             pointStyle: 'rounded',
            //             font: {
            //                 size: 16
            //             }
            //         }
            //     }
            // }
        }
    });
})