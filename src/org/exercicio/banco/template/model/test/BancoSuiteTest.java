package org.exercicio.banco.template.model.test;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.runner.RunWith;

@SuppressWarnings("deprecation")
@RunWith(JUnitPlatform.class)
@Suite
@SelectClasses({ClienteTest.class, ContaBancariaTest.class})
public class BancoSuiteTest {

}
