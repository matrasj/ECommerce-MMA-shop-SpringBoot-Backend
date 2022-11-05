package com.example.ecommercebackend.model.payload.purchase;

import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.entity.State;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AddressPayload {
    @NotNull(message = "Address is required")
    private String address;
    @NotNull(message = "City is required")
    private String city;
    @NotNull(message = "Country is required")
    private String countryName;
    @NotNull(message = "State is required")
    private String stateName;
    @NotNull(message = "Zip-code is required")
    private String zipCode;
}
