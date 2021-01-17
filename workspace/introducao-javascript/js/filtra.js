var busca = document.querySelector("#filtrar-tabela");
var pacientes = document.querySelectorAll(".paciente");
busca.addEventListener("input", function() {
    if(this.value.length > 0) {
        for(var i = 0; i < pacientes.length; i++) {
            var paciente = pacientes[i];
            var nome = paciente.querySelector(".info-nome").textContent;
            var expressao = new RegExp(this.value, "i");
            if(!expressao.test(nome)) {
                pacientes[i].classList.add("invisivel");
            } else {
                pacientes[i].classList.remove("invisivel");
            }
        }
    } else {
        pacientes.forEach(function(paciente) {
            paciente.classList.remove("invisivel");
        })
    }
})

