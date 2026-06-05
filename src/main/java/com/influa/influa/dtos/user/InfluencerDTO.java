package com.influa.influa.dtos.user;

public record InfluencerDTO(
        UserDTO userDTO,

        String biography,
        String mainNiche,
        String mediaKitUrl
) {
}