package com.devsuperior.user_request_sb.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.devsuperior.user_request_sb.dto.UserDTO;
import com.devsuperior.user_request_sb.entities.User;

@Configuration
public class SelectFieldsUserDataProcessorConfig {

  private static Logger logger = LoggerFactory.getLogger(SelectFieldsUserDataProcessorConfig.class);
  private int counter = 1;

  @Bean
  public ItemProcessor<UserDTO, User> selectFieldsUserDataProcessor() {
    return new ItemProcessor<UserDTO, User>() {

      @Override
      public User process(UserDTO item) throws Exception {
        User user = new User();
        user.setLogin(item.getLogin());
        user.setName(item.getName());
        user.setAvatarUrl(item.getAvatarUrl());
        logger.info("[PROCESSOR STEP] Select user fields " + counter + " - " + user);
        counter++;
        return user;
      }

    };
  }

}
