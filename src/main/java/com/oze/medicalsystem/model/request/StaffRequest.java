package com.oze.medicalsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Project title: medical-system
 *
 * @author johnadeshola
 * Date: 4/18/22
 * Time: 1:57 PM
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StaffRequest implements Serializable {

    @NotBlank(message = "Staff name cannot be empty")
    @Size(min = 3, max = 80, message = "Staff name length cannot be less than 3 characters and more than 80 characters")
    private String name;

    @NotBlank(message = "Staff registration date cannot be empty")
    private String registrationDate;

}
