package com.peterholub.sqlquerybulder;

import java.util.Set;

public class SqlQueryBuilder {
    private final CSVParserForLogs csvParserForLogs = new CSVParserForLogs();
    private final QueryBuilder queryBuilder = new QueryBuilder();

    public static void main(String[] args) {
        SqlQueryBuilder sqlQueryBuilder = new SqlQueryBuilder();
        Set<String> ids = sqlQueryBuilder.
                csvParserForLogs.parseFile("Discover 2021-04-26T11_30_59.286Z - 2021-04-27T11_30_59.286Z.csv");
        String sqlQuery = sqlQueryBuilder.queryBuilder.buildQuery(ids);
        System.out.println(sqlQuery);
    }

}
