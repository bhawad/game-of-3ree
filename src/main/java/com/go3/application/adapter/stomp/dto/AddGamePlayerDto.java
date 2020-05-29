package com.go3.application.adapter.stomp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGamePlayerDto implements Serializable {

    private String gameId;
    private String playerDisplayName;

}
