var tabela = document.querySelector("table");
tabela.addEventListener("dblclick", function(event) {
    var alvoEvento = event.target; // Retorna o Objeto que sofreu a ação do evento
    var paiDoAlvo = alvoEvento.parentNode; // Retorna o pai do Objeto que sofreu a ação do evento
    paiDoAlvo.classList.add("fadeOut");
    
    // Função de esperar um tempo em milissegundos para ser realizada uma função
    setTimeout(function() {
        paiDoAlvo.remove();    
    }, 500);
    
})
