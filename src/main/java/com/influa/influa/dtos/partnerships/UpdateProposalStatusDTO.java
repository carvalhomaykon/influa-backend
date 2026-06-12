package com.influa.influa.dtos.partnerships;

import com.influa.influa.model.partnerships.EnumStatusProposal;

public record UpdateProposalStatusDTO(
    EnumStatusProposal status
) {}