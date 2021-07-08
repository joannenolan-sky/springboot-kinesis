package com.example.consumer.model.kinesis;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableHeader.class)
@JsonDeserialize(as = ImmutableHeader.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Header {
    public abstract String type();

    public abstract String sourceId();

    public abstract String task();
}
