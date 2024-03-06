package com.example.ticketsystem.enums;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
public enum Role  {
    USER, ADMIN
}
