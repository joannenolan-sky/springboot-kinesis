package com.example.consumer.model.kinesis.data;

import com.example.consumer.model.kinesis.Header;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
@JsonSerialize(as = ImmutableDataRecord.class)
@JsonDeserialize(as = ImmutableDataRecord.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class DataRecord {

    public abstract Optional<Header> header();

    public abstract Optional<Data> data();
}
