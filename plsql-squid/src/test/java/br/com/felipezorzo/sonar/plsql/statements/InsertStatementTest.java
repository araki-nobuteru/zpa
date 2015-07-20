package br.com.felipezorzo.sonar.plsql.statements;

import static org.sonar.sslr.tests.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.com.felipezorzo.sonar.plsql.api.PlSqlGrammar;
import br.com.felipezorzo.sonar.plsql.api.RuleTest;

public class InsertStatementTest extends RuleTest {

    @Before
    public void init() {
        setRootRule(PlSqlGrammar.INSERT_STATEMENT);
    }
    
    @Test
    public void matchesSimpleInsert() {
        assertThat(p).matches("insert into tab values (1);");
    }
    
    @Test
    public void matchesInsertWithExplicitColumn() {
        assertThat(p).matches("insert into tab (x) values (1);");
    }
    
    @Test
    public void matchesInsertMultipleColumns() {
        assertThat(p).matches("insert into tab (x, y) values (1, 2);");
    }
    
    @Test
    public void matchesInsertWithSubquery() {
        assertThat(p).matches("insert into tab (x, y) (select 1, 2 from dual);");
    }

}
