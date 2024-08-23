package MiniJava.parser;

import MiniJava.errorHandler.ErrorHandler;

public class ParserFacade {
    private Parser parser;
    private ErrorHandler errorHandler;

    public ParserFacade() {
        this.errorHandler = new ErrorHandler();
        ParseTable parseTable = new ParseTable();
        this.parser = new Parser(parseTable, errorHandler);
    }

    public void parseInput(String input) {
        parser.parse(input);
    }

    public void addParseRule(NonTerminal nonTerminal, Rule rule) {
        parser.getParseTable().addRule(nonTerminal, rule);
    }

}
