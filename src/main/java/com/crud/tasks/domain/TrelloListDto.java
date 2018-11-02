package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//@AllArgsConstructor
@NoArgsConstructor
//@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("closed")
    private boolean isClosed;

    public TrelloListDto(String id, String name, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.isClosed = isClosed;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return isClosed;
    }
}
