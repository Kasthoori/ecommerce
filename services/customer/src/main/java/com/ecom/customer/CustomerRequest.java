package com.ecom.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
         String id,

         @NotNull(message = "First name cannot be null")
         String firstname,

         @NotNull(message = "Last name cannot be null")
         String lastname,

         @NotNull(message = "Email cannot be null")
         @Email(message = "Email should be valid")
         String email,
         
         Address address
) {

}
