package com.uningen.estore.domain.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @Column(name = "id")
    private String id;

//    @Column(name = "user_email")
//    private String userEmail;

    @ElementCollection
    @CollectionTable(name = "cart_product_mapping",
    joinColumns = {@JoinColumn(name = "cart_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "product_qty")
    private Map<Long, Integer> cartProducts; // productID : quantity



//    @JsonFormat(pattern = "dd/MM/yyyy")
//    private LocalDate dateCreated;
//
//    private String status;

//    @JsonManagedReference
//    @OneToMany(mappedBy = "pk.cart")
////    @Column(name = "cart_items")
//    private List<CartItem> cartItems = new ArrayList<>();

//    @Transient
//    public Double getTotalCartPrice(){
//        double sum = 0D;
//        List<CartItem> cartItems = getCartItems();
//        for(CartItem ci : cartItems){
//            sum += ci.getTotalPrice();
//        }
//        return sum;
//    }
//
//    @Transient
//    public int getNumberOfCartItems(){
//        return this.cartItems.size();
//    }


}
