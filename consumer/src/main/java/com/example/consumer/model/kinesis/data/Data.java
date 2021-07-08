package com.example.consumer.model.kinesis.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = ImmutableData.class)
@JsonDeserialize(as = ImmutableData.class)
@Value.Style(jdkOnly = true)
public abstract class Data {

    public abstract String uuid();

    public abstract String field1();

    public abstract String field2();

    public abstract String field3();

    public abstract List<String> fieldList();
}
