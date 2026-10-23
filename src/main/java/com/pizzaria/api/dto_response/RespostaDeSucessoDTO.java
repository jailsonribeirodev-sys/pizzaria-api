package com.pizzaria.api.dto_response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;
import java.util.Map;

public record RespostaDeSucessoDTO<T>(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "America/Fortaleza")
        Instant timestamp,
        int status,
        String mensagem,
      @JsonAnyGetter  Map<String, T> conteudo
) {

    public RespostaDeSucessoDTO(int status, String mensagem, String nomeChave, T dados) {
        this(
                Instant.now(),
                status,
                mensagem,
                Map.of(nomeChave, dados)

        );

    }


}
