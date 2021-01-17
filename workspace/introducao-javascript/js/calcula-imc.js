function calcularImc(peso, altura) {
    // .toFixed() Limita o número de casas decimais
    return (peso/(altura**2)).toFixed(2);
}

function validaPeso(peso) {
    if(peso > 0 && peso < 1000) {
        return true;
    }
    return false;
}

function validaAltura(altura) {
    if(altura > 0 && altura < 3.00) {
        return true;
    }
    return false;
}

// Retorna a TAG que possui a classe .titulo
var titulo = document.querySelector(".titulo");
console.log(titulo.textContent);

// Retorna um array da quantidade de TAG's que possuírem a classe .paciente
var pacientes = document.querySelectorAll(".paciente");

var cor = "lightcoral";

for(var i = 0; i < pacientes.length; i++) {
    var peso = pacientes[i].querySelector(".info-peso").textContent;
    var altura = pacientes[i].querySelector(".info-altura").textContent;
    var pesoEhValido = true;
    var alturaEhValida = true;
    
    if(!validaPeso(peso)) {
        console.log("Peso Inválido!");
        peso.textContent = "Peso Inválido";
        // Adiciona uma classe CSS na TAG do HTML referenciada
        pacientes[i].classList.add("paciente-invalido");
        pesoEhValido = false;
    }

    if(!validaAltura(altura)) {
        console.log("Altura Inválida!");
        altura.textContent = "Altura Inválida";
        // Adiciona uma classe CSS na TAG do HTML referenciada
        pacientes[i].classList.add("paciente-invalido");
        alturaEhValida = false;
    }

    if(alturaEhValida && pesoEhValido) {
        var imc = calcularImc(peso,altura);
        pacientes[i].querySelector(".info-imc").textContent = imc;
    } else {
        pacientes[i].querySelector(".info-imc").textContent =  "Imc Inválido";
    }   
}
