package com.peterholub.sqlquerybulder;

import java.util.Set;

public class SqlQueryBuilder {
    private final CSVParserForLogs csvParserForLogs = new CSVParserForLogs();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    public static void main(String[] args) {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        Set<String> ids = sqlQueryBuilder.
                csvParserForLogs.parseFile("Discover 2021-04-26T19_44_40.110Z - 2021-04-27T19_44_40.110Z.csv");
        System.out.println("There are " + ids.size() + " product id's");
        String sqlQuery = sqlQueryBuilder.queryBuilder.buildQuery(ids);
        System.out.println(sqlQuery);
    }

}
