package org.LojaVirtual;

public class Paciente {

    private AutorizadorReembolso autorizador;

    public Paciente(AutorizadorReembolso autorizador) {
        this.autorizador = autorizador;
    }

    public boolean tentarReembolso() {
        return autorizador.autorizarConsulta();
    }
}
