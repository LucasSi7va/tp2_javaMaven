package org.LojaVirtual;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestRespostas {



    public double CalcularRembolso(double consulta, double valor) {
        return consulta * valor;
    }


    @Test
    public void TestRembolso() {

        assertEquals(140.0, CalcularRembolso(200, 0.70), "resultado de 200 * 70% : 140,0");

    }

    @Test
    public void TestRembolso2() {

        assertEquals(0.0, CalcularRembolso(200, 0.0), "resultado de 200 * 0% : 0,0");
        assertEquals(200.0, CalcularRembolso(200, 1), "resultado de 200 * 1% e 200");
    }

    @Test
    public void TestRembolsoFailed() {
        assertEquals(200.0, CalcularRembolso(200, 0.0), "resultado de 200 * 0% : 0,0");
        assertEquals(0.0, CalcularRembolso(200, 1), "resultado de 200 * 1% e 200");
    }

    @Test
    public void Paciente() {
      PlanoSaude planostub = () -> 0.80;
      double rembolso = CalcularRembolso(200, planostub.PercentualCobertura());
      assertEquals(160.0, rembolso, "resultado de 200 * 80% : 120,0");
    }

@Test
public void TestChamadaAuditoriaComSpy(){
        Auditoria auditoria = new Auditoria() {
            @Override
            public void registrarConsulta(String paciente) {
                System.out.println("Consulta registrada para: " + paciente);
            }
        };
}

@Test
public void TestChamadaAuditoriaComMock(){
        AutorizadorReembolso autorizadorMock = Mockito.mock(AutorizadorReembolso.class);

        Mockito.when(autorizadorMock.autorizarConsulta()).thenReturn(true);

        Paciente paciente = new Paciente(autorizadorMock);

        assertFalse(paciente.tentarReembolso());

    Mockito.verify(autorizadorMock).autorizarConsulta();
    }


    private void auxiliar(double valorConsulta, double percentual, double resultadoEsperado, String mensagem) {
        double resultado = CalcularRembolso(valorConsulta, percentual);
        assertEquals(resultadoEsperado, resultado, mensagem);
    }


    @Test
    public void TestRembolsoComAuxiliar() {
       auxiliar(200, 0.70, 140.0, "resultado de 200 * 70% : 140,0");
    }

    @Test
    public void TestRembolso2ComAuxiliar() {
        auxiliar(200, 0.0, 0.0, "resultado de 200 * 0% : 0,0");
        auxiliar(200, 1, 200.0, "resultado de 200 * 1% e 200");
    }

    @Test
    public void TestRembolsoFailedComAuxiliar() {
        auxiliar(200, 0.0, 200.0, "resultado de 200 * 0% : 0,0");
        auxiliar(200, 1, 0.0, "resultado de 200 * 1% e 200");
    }


    public double CalcularLimiteMaximo(double consulta , double valor){
    double reembolsoCalculo = consulta * valor;
    final double limiteMaximo = 150.0;
    return Math.min(reembolsoCalculo, limiteMaximo);
    }



    @Test
    public void testReembolsoNaoExcedeLimiteMaximo() {

       auxiliar(300, 0.70, 150.0, "70% de 300 deveria ser 210, mas é limitado a 150");
        auxiliar(200, 1.0, 150.0, "100% de 200 deveria ser 200, mas é limitado a 150");
        auxiliar(150, 1.0, 150.0, "100% de 150 é exatamente o limite");
    }

    @Test
    public void testReembolsoAbaixoDoLimitePermaneceIgual() {

        auxiliar(100, 1.0, 100.0, "100% de 100 está abaixo do limite");
        auxiliar(200, 0.50, 100.0, "50% de 200 está abaixo do limite");
        auxiliar(149, 1.0, 149.0, "100% de 149 está abaixo do limite");
    }







}




