package com.example.ecommercebackend.model.payload.purchase;

import com.example.ecommercebackend.model.entity.Country;
import com.example.ecommercebackend.model.entity.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressPayload {
    private String address;
    private String city;
    private String countryName;
    private String stateName;
    private String zipCode;
}
