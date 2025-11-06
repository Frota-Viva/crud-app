document.addEventListener('DOMContentLoaded', () => {
//     Fazendo o gráfico das informações dos caminhões
    const ctxCaminhoes = document.getElementById("graficoCaminhoes");
    let inativosCaminhoes = parseInt(document.getElementById("inativosCaminhoes").textContent);
    let manutencaoCaminhoes = parseInt(document.getElementById("manutencaoCaminhoes").textContent);
    let ativosCaminhoes = parseInt(document.getElementById("ativosCaminhoes").textContent);

    new Chart(ctxCaminhoes, {
        type: 'doughnut',
        data: {
            labels: ['Ativos', 'Manutenção', 'Inativos'],
            datasets: [{
                data: [ativosCaminhoes, manutencaoCaminhoes, inativosCaminhoes],
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
            plugins: {
                legend: {
                    position: 'bottom',
                    labels: {
                        color: 'white',
                        boxWidth: 10,
                        boxHeight: 5,
                        usePointStyle : true,
                        pointStyle: 'rounded',
                        font: {
                            size: 16
                        }
                    }
                }
            }
        }
    });

    // Fazendo o gráfico das entregas

    const ctxEntregas = document.getElementById("graficoEntregas");
    let realizadasEntregas = parseInt(document.getElementById("realizadasEntregas").textContent);
    let aCaminhoEntregas = parseInt(document.getElementById("aCaminhoEntregas").textContent);

    new Chart(ctxEntregas, {
        type: 'doughnut',
        data: {
            labels: ['Realizadas', 'À caminho'],
            datasets: [{
                data: [realizadasEntregas, aCaminhoEntregas],
                backgroundColor: ['rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)'],
                borderWidth: 2,
                borderColor: ['rgba(0, 255, 102, 1)',
                    'rgba(0, 123, 255, 1)'
                ],
                spacing: 4
            }]
        },
        options: {
            cutout: '70%', // deixa o "buraco" no meio maior
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'center',
                    labels: {
                        color: 'white',
                        boxWidth: 10,
                        boxHeight: 5,
                        usePointStyle : true,
                        pointStyle: 'rounded',
                        font: {
                            size: 16
                        }
                    }
                }
            }
        }
    });

    //Fazendo os gráficos das manutenções
    // const ctxManutencao = document.getElementById("graficoManutencao");
    // let corretivasManutencao = document.getElementById("corretivasManutencao").textContent;
    // let preventivasManutencao = document.getElementById("preventivasManutencao").textContent;
    //
    // new Chart(ctxManutencao, {
    //     type: 'doughnut',
    //     data: {
    //         labels: ['Corretivas', 'Preventivas'],
    //         datasets: [{
    //             data: [corretivasManutencao, preventivasManutencao],
    //             backgroundColor: ['rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)'],
    //             borderWidth: 2,
    //             borderColor: ['rgba(0, 255, 102, 1)',
    //                 'rgba(0, 123, 255, 1)'
    //             ],
    //             spacing: 4
    //         }]
    //     },
    //     options: {
    //         cutout: '70%', // deixa o "buraco" no meio maior
    //         plugins: {
    //             legend: {
    //                 position: 'bottom',
    //                 align: 'center',
    //                 labels: {
    //                     color: 'white',
    //                     boxWidth: 10,
    //                     boxHeight: 5,
    //                     usePointStyle : true,
    //                     pointStyle: 'rounded',
    //                     font: {
    //                         size: 16
    //                     }
    //                 }
    //             }
    //         }
    //     }
    // });

    //Fazendo o gráfico dos preços das manutenções
    const ctxPrecos = document.getElementById("graficoPrecosManutencoes");
    let totalPrecos = document.getElementById("totalPrecoManutencao");
    console.log(totalPrecos.textContent);
    let corretivasPrecos = document.getElementById("corretivasPrecoManutencao");
    let preventivasPrecos = document.getElementById("preventivasPrecoManutencao");
    let corretivasValor = parseFloat(corretivasPrecos.textContent);
    let preventivasValor = parseFloat(preventivasPrecos.textContent);

    totalPrecos.textContent = formatarDinheiro(totalPrecos.textContent);
    corretivasPrecos.textContent = formatarDinheiro(corretivasValor);
    preventivasPrecos.textContent = formatarDinheiro(preventivasValor);



    new Chart(ctxPrecos, {
        type: 'doughnut',
        data: {
            labels: ['Corretivas', 'Preventivas'],
            datasets: [{
                data: [corretivasValor, preventivasValor],
                backgroundColor: ['rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)', 'rgba(0, 0, 0, 0)'],
                borderWidth: 2,
                borderColor: ['rgba(255, 51, 51, 1)',
                    'rgba(0, 123, 255, 1)'
                ],
                spacing: 4
            }]
        },
        options: {
            cutout: '70%', // deixa o "buraco" no meio maior
            plugins: {
                legend: {
                    position: 'bottom',
                    align: 'center',
                    labels: {
                        color: 'white',
                        boxWidth: 10,
                        boxHeight: 5,
                        usePointStyle : true,
                        pointStyle: 'rounded',
                        font: {
                            size: 16
                        }
                    }
                }
            }
        }
    });

    function formatarDinheiro(valor) {
        return parseFloat(valor).toLocaleString("pt-BR", {
            style: "currency",
            currency: "BRL"
        });
    }


})