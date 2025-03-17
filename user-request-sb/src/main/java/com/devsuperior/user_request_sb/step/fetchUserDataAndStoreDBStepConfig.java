package com.devsuperior.user_request_sb.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.devsuperior.user_request_sb.dto.UserDTO;

@Configuration
public class fetchUserDataAndStoreDBStepConfig {

  @Autowired
  private PlatformTransactionManager transactionManager;

  @Value("${chunkSize}")
  private int chunkSize;

  @Bean
  public Step fetchUserDataAndStoreDBStep(ItemReader<UserDTO> fetchUserDataReader, JobRepository jobRepository) {

    return new StepBuilder("fetchUserDataAndStoreDBStep", jobRepository)
        .<UserDTO, UserDTO>chunk(chunkSize, transactionManager)
        .reader(fetchUserDataReader)
        .build();
  }

}
