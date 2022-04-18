package com.oze.medicalsystem.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class PatientRequest implements Serializable {

    @NotBlank(message = "Staff name cannot be empty")
    @Size(min = 3, max = 80, message = "Staff name length cannot be less than 3 characters and more than 80 characters")
    private String name;

    @NotNull(message = "Patient age cannot be empty")
    private Integer age;

    @NotNull(message = "Patient last visit date cannot be empty")
    private String lastVisitDate;

}
