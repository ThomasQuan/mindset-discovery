package com.tq.mindset.dto.updateDto;

import com.tq.mindset.domain.enums.UserStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UpdateUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;  // Assuming email can be updated
    private UserStatus status;
    private String title;
    private String headline;
    private String avatarURL;
    private LocalDateTime lastActiveAt;
}
