package com.example.ticketsystem.entity;

import com.example.ticketsystem.enums.Country;
import com.example.ticketsystem.enums.Language;
import com.example.ticketsystem.enums.TypeFilm;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table(name = "film")
@FieldNameConstants
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(VarcharJdbcType.class)
    private UUID id ;
    @Column(name = "image")
    private String image;
    @Column(name = "name")

    private String name;
    @Column(name = "type_film")

    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    private TypeFilm typeFilm;
    @Column(name = "time")

    private Integer time; //thoi luong film(phut)
    @Column(name = "begin_date")

    private LocalDateTime beginDate;
    @Column(name = "language")
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    private Language language;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    @Enumerated(EnumType.STRING) // Possibly optional (I'm not sure) but defaults to ORDINAL.
    private Country country;

    @Column(name = "rate", scale=2)
    private Double rate;

    @Column(name = "sum_rate")
    private Long sumRate = 0L;

    @Column(name = "count_rate")
    private int countRate = 0;

//    @Column(name = "deleted")
//    private Boolean deleted;
    @Column(name = "price")
    private Long price;



}
