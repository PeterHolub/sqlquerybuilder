package com.peterholub.sqlquerybulder;

import java.util.Set;

public class QueryBuilder {

    public String buildQuery(Set<String> ids) {
        return "update pimdam_incremental_log set update_sent =false\n" +
                "where product_id in (" + buildInClause(ids) + ");";
    }

    private String buildInClause(Set<String> ids) {
        String result = "";

        for (String id : ids) {
            if (ids.iterator().hasNext()) {
                result = result.concat(id + ", ");
            }
        }
        return removeInTheEnd(result);
    }

    private String removeInTheEnd(String string) {
        return string.substring(0, string.length() - 2);
    }
}
