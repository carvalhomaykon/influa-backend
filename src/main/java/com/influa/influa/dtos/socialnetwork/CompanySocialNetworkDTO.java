package com.influa.influa.dtos.socialnetwork;

import com.influa.influa.model.user.Company;

public record CompanySocialNetworkDTO(
        SocialNetworkDTO socialNetworkDTO,

        Company company
) {
}
