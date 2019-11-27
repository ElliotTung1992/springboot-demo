package com.ecer.kafka.connect.oracle.models;

import lombok.AllArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@lombok.Data
public class AnalysisSourceResult {
    private Map<String, Object> basic;
    private Map<String, LinkedHashMap<String, String>> record;
}
