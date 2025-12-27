package com.starter.dtxcollector.domain.repository;

import com.starter.dtxcollector.domain.model.TeamMember;

import java.util.List;
import java.util.UUID;

public interface TeamMemberRepository {
    List<TeamMember> findByTeam_Id(UUID teamId);
}
