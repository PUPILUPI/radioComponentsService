package ru.belov.radioComponentsService.domain.entity.sql;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "sellers_list", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SellersList implements Serializable {

    @EmbeddedId
    private SellersListId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("consumerId")
    @JoinColumn(name = "consumer_id", referencedColumnName = "user_id")
    private MyUser consumer;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("sellerId")
    @JoinColumn(name = "seller_id", referencedColumnName = "seller_id")
    private SellerInfo seller;

    @Column(name = "favorite_flag", nullable = false)
    private Boolean favoriteFlag = false;

    @Column(name = "blacklist_flag", nullable = false)
    private Boolean blacklistFlag = false;
}


