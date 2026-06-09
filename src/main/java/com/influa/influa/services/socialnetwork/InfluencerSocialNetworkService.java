package com.influa.influa.services.socialnetwork;

import com.influa.influa.repositories.socialnetwork.InfluencerSocialNetworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfluencerSocialNetworkService {

    @Autowired
    private InfluencerSocialNetworkRepository influencerSocialNetworkRepository;

}
