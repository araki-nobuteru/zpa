/*
 * Z PL/SQL Analyzer
 * Copyright (C) 2015-2019 Felipe Zorzo
 * mailto:felipebzorzo AT gmail DOT com
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
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.plugins.plsqlopen.api;

import org.sonar.sslr.grammar.GrammarRuleKey;
import org.sonar.sslr.grammar.LexerfulGrammarBuilder;

import static org.sonar.plugins.plsqlopen.api.PlSqlGrammar.EXPRESSION;
import static org.sonar.plugins.plsqlopen.api.PlSqlKeyword.*;
import static org.sonar.plugins.plsqlopen.api.PlSqlPunctuator.*;

public enum AggregateSqlFunctionsGrammar implements GrammarRuleKey {

    LISTAGG_EXPRESSION,
    AGGREGATE_SQL_FUNCTION;

    public static void buildOn(LexerfulGrammarBuilder b) {
        b.rule(LISTAGG_EXPRESSION).is(
            LISTAGG,
            LPARENTHESIS, b.optional(b.firstOf(ALL, DISTINCT)), EXPRESSION, b.optional(COMMA, EXPRESSION),
            b.optional(ON, OVERFLOW, b.firstOf(
                ERROR,
                b.sequence(TRUNCATE, b.optional(EXPRESSION), b.optional(b.firstOf(WITH, WITHOUT), COUNT)))),
            RPARENTHESIS,
            WITHIN, GROUP, LPARENTHESIS, DmlGrammar.ORDER_BY_CLAUSE, RPARENTHESIS);
        
        b.rule(AGGREGATE_SQL_FUNCTION).is(LISTAGG_EXPRESSION);
    }

}
