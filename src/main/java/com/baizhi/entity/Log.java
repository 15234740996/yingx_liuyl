package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Log {
    @Id
    private String id;

    private String name;
@JsonFormat(pattern = "yyyy-MM-dd")
@DateTimeFormat(pattern ="yyyy-MM-dd")
    private Date times;

    private String optioncz;

    private String status;


}