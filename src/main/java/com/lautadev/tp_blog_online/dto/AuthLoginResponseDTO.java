package com.lautadev.tp_blog_online.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"username","message","JWT","status"})
public record AuthLoginResponseDTO(String username,
                              String message,
                              String JWT,
                              boolean status) {

}
