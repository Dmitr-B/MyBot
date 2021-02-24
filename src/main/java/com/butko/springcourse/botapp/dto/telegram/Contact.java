package com.butko.springcourse.botapp.dto.telegram;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Contact {

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonCreator
    public Contact(String phoneNumber, String firstName) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
    }
}
