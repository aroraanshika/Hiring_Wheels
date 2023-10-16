package org.ncu.hirewheels.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id", nullable = false, unique = true)
    private Long vehicleId;

    @NotBlank
    @Size(max = 50)
    @Column(name = "vehicle_model", nullable = false)
    private String vehicleModel;

    @NotBlank
    @Size(max = 10)
    @Column(name = "vehicle_number", nullable = false)
    private String vehicleNumber;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_subcategory_id", nullable = false)
    @JsonBackReference
    private VehicleSubcategory vehicleSubcategory;

    @NotBlank
    @Size(max = 50)
    @Column(name = "color", nullable = false)
    private String color;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private Location location;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fuel_type_id", nullable = true)
    @JsonBackReference
    private FuelType fuelType;

    @NotNull
    @Column(name = "availability_status", nullable = false)
    private Integer availabilityStatus;

    @NotBlank
    @Size(max = 500)
    @Column(name = "vehicle_image_url", nullable = false)
    private String vehicleImageUrl;
    
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Booking> bookings;

    // Constructors, getters, setters, and any other methods as needed
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", vehicleSubcategory=" + vehicleSubcategory.getVehicleSubcategoryName() + // Include the related VehicleSubcategory's name
                ", color='" + color + '\'' +
                ", location=" + location.getLocationName() + // Include the related Location's name
                ", fuelType=" + (fuelType != null ? fuelType.getFuelType() : "N/A") + // Include the related FuelType's name if available
                ", availabilityStatus=" + availabilityStatus +
                ", vehicleImageUrl='" + vehicleImageUrl + '\'' +
                '}';
    }

}
