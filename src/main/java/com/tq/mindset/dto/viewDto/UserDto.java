package com.tq.mindset.dto.viewDto;

import com.tq.mindset.domain.enums.UserStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private UserStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private String headline;
    private LocalDateTime lastActiveAt;
    private String avatarURL;
}