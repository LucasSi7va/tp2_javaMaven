package org.LojaVirtual;

public class TestSistemaConsulta {
    private Auditoria auditoria;

    public TestSistemaConsulta(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public void registrarConsulta(String paciente) {
        auditoria.registrarConsulta(paciente);
    }
}
