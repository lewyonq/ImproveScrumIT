package com.lewyonq.isit.model;

import com.lewyonq.isit.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRequest {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private final UserRole role = UserRole.OWNER;
    private String companyName;

}
