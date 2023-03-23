package org.exercicio.banco.template.model.test;

import static org.junit.Assert.*;

import org.exercicio.banco.template.model.Cliente;
import org.exercicio.banco.template.model.ContaBancaria;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

public class ClienteTest {
    private Cliente cliente;
    private ContaBancaria conta1;
    private ContaBancaria conta2;
    
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
    
    @Before
    public void setUp() {
        cliente = new Cliente("Joao da Silva","000");
        conta1 = new ContaBancaria(12345,0);
        conta2 = new ContaBancaria(67890,0);
    }
    
    @Test
    public void testAdicionarConta() {
        cliente.adicionarConta(conta1);
        assertEquals(1, cliente.getContas().size());
        assertTrue(cliente.getContas().contains(conta1));
        assertEquals("Conta adicionada com sucesso!", systemOutRule.getLog());
    }
    
    @Test
    public void testAdicionarContaJahAssociada() {
        cliente.adicionarConta(conta1);
        assertEquals(1, cliente.getContas().size());
        assertTrue(cliente.getContas().contains(conta1));
        systemOutRule.clearLog();
        cliente.adicionarConta(conta1);
        assertEquals("A conta jah estah associada a este cliente.", systemOutRule.getLog());
    }
    
    @Test
    public void testRemoverConta() {
        cliente.adicionarConta(conta1);
        systemOutRule.clearLog();
        cliente.removerConta(conta1);
        assertEquals(0, cliente.getContas().size());
        assertFalse(cliente.getContas().contains(conta1));
        assertEquals("Conta removida com sucesso!", systemOutRule.getLog());
    }
    
    @Test
    public void testRemoverContaNaoAssociada() {
        cliente.adicionarConta(conta1);
        systemOutRule.clearLog();
        cliente.removerConta(conta2);
        assertEquals(1, cliente.getContas().size());
        assertFalse(cliente.getContas().contains(conta2));
        assertEquals("A conta nao esta associada a este cliente.", systemOutRule.getLog());
    }
    
    @Test
    public void testLocalizarContaPorNumero() {
        cliente.adicionarConta(conta1);
        ContaBancaria conta = cliente.localizarContaNumero(12345);
        assertEquals(conta1, conta);
        cliente.localizarContaNumero(12345);
        systemOutRule.clearLog();
        assertEquals(conta, cliente.localizarContaNumero(12345));
        assertEquals("Conta encontrada!", systemOutRule.getLog());
    }
    
    @Test
    public void testLocalizarContaNumeroInexistente() {
        cliente.adicionarConta(conta1);
        ContaBancaria conta = cliente.localizarContaNumero(12345);
        assertEquals(conta1, conta);
        systemOutRule.clearLog();
        assertNull(cliente.localizarContaNumero(67890));
        assertEquals("Conta nao encontrada.", systemOutRule.getLog());
    }
    
    @Test
    public void testLocalizarConta() {
        cliente.adicionarConta(conta1);
        systemOutRule.clearLog();
        assertTrue(cliente.localizarConta(conta1));
        assertEquals("Conta encontrada!", systemOutRule.getLog());
        systemOutRule.clearLog();
        assertFalse(cliente.localizarConta(conta2));
        assertEquals("Conta nao encontrada.", systemOutRule.getLog());
    }
    
    @Test
    public void testBalancoEntreContas() {
        cliente.adicionarConta(conta1);
        cliente.adicionarConta(conta2);
        conta1.depositar(1000);
        conta2.depositar(500);
        systemOutRule.clearLog();
        assertEquals(1500.0, cliente.balancoEntreContas(), 0.01);
        assertEquals("Balanco entre contas: RS1500.0", systemOutRule.getLog());
    }
}