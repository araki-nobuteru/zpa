/*
 * Sonar PL/SQL Plugin (Community)
 * Copyright (C) 2015 Felipe Zorzo
 * felipe.b.zorzo@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package br.com.felipezorzo.sonar.plsql.statements;

import static org.sonar.sslr.tests.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.felipezorzo.sonar.plsql.api.PlSqlGrammar;
import br.com.felipezorzo.sonar.plsql.api.RuleTest;

public class ExecuteImmediateStatementTest extends RuleTest {

    @Before
    public void init() {
        setRootRule(PlSqlGrammar.EXECUTE_IMMEDIATE_STATEMENT);
    }

    @Test
    public void matchesSimpleExecuteImmediate() {
        assertThat(p).matches("execute immediate 'command';");
    }
    
    @Test
    public void matchesExecuteImmediateIntoVariable() {
        assertThat(p).matches("execute immediate 'command' into var;");
    }
    
    @Test
    public void matchesExecuteImmediateBulkCollectIntoVariable() {
        assertThat(p).matches("execute immediate 'command' bulk collect into var;");
    }
    
    @Test
    public void matchesExecuteImmediateIntoMultipleVariables() {
        assertThat(p).matches("execute immediate 'command' into var, var2;");
    }
    
    @Test
    public void matchesExecuteImmediateBulkCollectIntoMultipleVariables() {
        assertThat(p).matches("execute immediate 'command' bulk collect into var, var2;");
    }
    
    @Test
    public void matchesExecuteImmediateUsingWithVariable() {
        assertThat(p).matches("execute immediate 'command' using var;");
    }
    
    @Test
    public void matchesExecuteImmediateUsingWithMultipleVariables() {
        assertThat(p).matches("execute immediate 'command' using var, var2;");
    }
    
    @Test
    public void matchesExecuteImmediateUsingWithExplicitInVariable() {
        assertThat(p).matches("execute immediate 'command' using in var;");
    }
    
    @Test
    public void matchesExecuteImmediateUsingWithOutVariable() {
        assertThat(p).matches("execute immediate 'command' using out var;");
    }
    
    @Test
    public void matchesExecuteImmediateUsingWithInOutVariable() {
        assertThat(p).matches("execute immediate 'command' using in out var;");
    }
    
    @Test
    public void matchesLabeledExecuteImmediate() {
        assertThat(p).matches("<<label>> execute immediate 'command';");
    }

}
