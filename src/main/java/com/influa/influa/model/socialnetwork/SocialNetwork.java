package com.influa.influa.model.socialnetwork;

import java.util.UUID;

import com.influa.influa.dtos.socialnetwork.SocialNetworkDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "social_networks")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "owner_type", discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class SocialNetwork {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private EnumPlatform platform;
    private String username;
    private String qtdFollowers;
    private String url;

    public SocialNetwork(SocialNetworkDTO data) {
        this.platform = data.enumPlatform();
        this.username = data.username();
        this.qtdFollowers = data.qtdFollowers();
        this.url = data.url();
    }

}
