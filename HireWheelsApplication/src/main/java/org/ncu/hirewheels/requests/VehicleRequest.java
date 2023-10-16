package org.ncu.hirewheels.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequest {

    @NotBlank
    @Size(max = 50)
    private String vehicleModel;

    @NotBlank
    @Size(max = 10)
    private String vehicleNumber;

    @NotNull
    private Long vehicleSubCategoryId;

    @NotBlank
    @Size(max = 50)
    private String color;

    @NotNull
    private Long fuelTypeId;

    @NotNull
    private Long locationId;

    @NotBlank
    @Size(max = 500)
    private String vehicleImageUrl;

    @NotNull
    private Integer availabilityStatus;


}