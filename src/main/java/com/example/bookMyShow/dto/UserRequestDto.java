package com.example.bookMyShow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String name;

    private String mobile;
}
