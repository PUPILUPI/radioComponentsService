package ru.belov.radioComponentsService.domain.entity.sql;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "legal_entity", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SellerInfo {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    @MapsId
    private MyUser user;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "rc_bic")
    private String rcBic;

    @Column(name = "сorr_acc")
    private String corrAcc;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "zip")
    private String zip;

    @Column(name = "city")
    private String city;

    @Column(name = "business_address")
    private String businessAddress;

    @Column(name = "inn")
    private String inn;

    @Column(name = "kpp")
    private String kpp;

    @Column(name = "acc")
    private String acc;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "flag_w_with_ind")
    private Boolean indFlag;

    @Column(name = "flag_manufacturer")
    private Boolean flagManufacturer;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "api_response_format")
    private String responseFormat;

    @Column(name = "api_address")
    private String apiAddress;

    @Column(name = "agreement")
    private String agreement;

    @Column(name = "agreement_flag")
    private Boolean agreementFlag;
}
