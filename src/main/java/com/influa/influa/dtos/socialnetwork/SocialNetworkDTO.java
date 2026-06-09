package com.influa.influa.dtos.socialnetwork;

import com.influa.influa.model.socialnetwork.EnumPlatform;

public record SocialNetworkDTO (
        EnumPlatform enumPlatform,
        String username,
        String qtdFollowers,
        String url
){
}
