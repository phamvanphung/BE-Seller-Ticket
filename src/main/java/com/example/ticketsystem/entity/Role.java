package com.example.ticketsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Getter
public enum Role  {
    USER, ADMIN
}
