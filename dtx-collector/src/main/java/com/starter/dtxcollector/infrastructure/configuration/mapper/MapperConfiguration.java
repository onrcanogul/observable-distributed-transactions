package com.starter.dtxcollector.infrastructure.configuration.mapper;

import com.starter.dtxcollector.domain.model.*;
import com.starter.dtxcollector.infrastructure.persistence.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {
    @Bean
    public Mapper<UserEntity, User> userMapper() {
        return new Mapper<>(UserEntity.class, User.class);
    }

    @Bean
    public Mapper<TraceStepEntity, TraceStep> traceStepMapper() {
        return new Mapper<>(TraceStepEntity.class, TraceStep.class);
    }

    @Bean
    public Mapper<TeamEntity, Team> teamMapper() {
        return new Mapper<>(TeamEntity.class, Team.class);
    }

    @Bean
    public Mapper<TeamMemberEntity, TeamMember> teamMemberMapper() {
        return new Mapper<>(TeamMemberEntity.class, TeamMember.class);
    }

    @Bean
    public Mapper<ProjectEntity, Project> projectMapper() {
        return new Mapper<>(ProjectEntity.class, Project.class);
    }
}
