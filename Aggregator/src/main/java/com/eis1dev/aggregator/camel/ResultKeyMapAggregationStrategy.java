package com.eis1dev.aggregator.camel;

import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import java.util.HashMap;
import java.util.Map;

public class ResultKeyMapAggregationStrategy implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldEx, Exchange newEx) {
        String key = newEx.getMessage().getHeader("resultKey", String.class);
        Object val = newEx.getMessage().getBody();
        if (oldEx == null) {
            Map<String,Object> map = new HashMap<>();
            map.put(key, val);
            newEx.getMessage().setBody(map);
            return newEx;
        }
        @SuppressWarnings("unchecked")
        Map<String,Object> map = oldEx.getMessage().getBody(Map.class);
        map.put(key, val);
        oldEx.getMessage().setBody(map);
        return oldEx;
    }
}
