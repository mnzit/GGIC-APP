package com.ggic.app.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private Long id;
    private String name;
    private Date dob;
    private String address;
    private String contactNo;

}
