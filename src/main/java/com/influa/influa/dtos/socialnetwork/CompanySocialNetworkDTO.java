package com.influa.influa.dtos.socialnetwork;

import java.util.UUID;

public record CompanySocialNetworkDTO(
                SocialNetworkDTO socialNetworkDTO,
                UUID company) {
}
