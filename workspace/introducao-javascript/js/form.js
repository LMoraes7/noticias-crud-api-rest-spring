var botaoAdicionar = document.querySelector("#adicionar-paciente");
// Adiciona um "escutador de eventos" no Objeto referenciado, 
//      caso o usuário realize uma ação no Objeto do tipo informado, será executada a função informada
botaoAdicionar.addEventListener("click", function(event) {
    
    // Previne o comportamento padrão do Objeto
    event.preventDefault();
    var form = document.querySelector("#form-adiciona");
    var paciente = obtemPacienteDoFormulario(form);
    var errors = validaPaciente(paciente);
    if(errors.length > 0) {
        exibeMensagensDeErro(errors);
        return;
    } 
    var pacienteTr = montaTr(paciente);
    insereTr(pacienteTr);

    // Limpa os campos do formulário após o seu envio
    form.reset();
    var ul = document.querySelector("#mensagem-erro");
    ul.innerHTML = "";
});

function obtemPacienteDoFormulario(form) {
    var paciente = {
        nome: form.nome.value,
        peso: form.peso.value,
        altura: form.altura.value,
        gordura: form.gordura.value,
        imc: calcularImc(form.peso.value, form.altura.value)
    };
    return paciente;
}

function montaTr(paciente) {
    // Cria um elemento/TAG do mundo HTML
    var pacienteTr = document.createElement("tr");
    
    var nomeTd = montaTd(paciente.nome, "info-nome");
    var pesoTd = montaTd(paciente.peso, "info-peso");
    var alturaTd = montaTd(paciente.altura, "info-altura");
    var gorduraTd = montaTd(paciente.gordura, "info-gordura");
    var imcTd = montaTd(paciente.imc, "info-imc");

    // Adiciona o elemento/TAG como filho no mundo HTML -> colocar um elemento dentro do outro
    pacienteTr.appendChild(nomeTd);
    pacienteTr.appendChild(pesoTd);
    pacienteTr.appendChild(alturaTd);
    pacienteTr.appendChild(gorduraTd);
    pacienteTr.appendChild(imcTd);
    pacienteTr.classList.add("paciente");
    return pacienteTr
}

function montaTd(dado, classeCss) {
    var td = document.createElement("td");
    td.textContent = dado;
    td.classList.add(classeCss);
    return td;
}

function insereTr(pacienteTr) {
    var tabelaPacientes = document.querySelector("#tabela-pacientes");
    tabelaPacientes.appendChild(pacienteTr);
}

function validaPaciente(paciente) {

    var erros = [];

    if(paciente.nome == 0) {
        erros.push("O nome não pode ser vazio");
    }

    if(!validaPeso(paciente.peso)) {
        erros.push("Peso é inválido");
    }
    
    if(!validaAltura(paciente.altura)) {
        erros.push("Altura é inválida");
    }

    if(paciente.gordura < 0) {
        erros.push("Taxa de gordura é inválida");
    }
    return erros;
}

function exibeMensagensDeErro(errors) {
    var ul = document.querySelector("#mensagem-erro");
    ul.innerHTML = "";
    errors.forEach(function(erro) {
        var li = document.createElement("li");
        li.textContent = erro;
        ul.appendChild(li);
    });
}

function adicionaPacienteNaTabela(paciente) {
    var pacienteTr = montaTr(paciente);
    insereTr(pacienteTr);
}