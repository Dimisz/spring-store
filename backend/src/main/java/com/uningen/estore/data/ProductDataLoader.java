package com.uningen.estore.data;

import com.uningen.estore.domain.Product;
import com.uningen.estore.domain.ProductRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProductDataLoader {
    private final ProductRepository productRepository;

    public ProductDataLoader(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadProductsTestData(){
        productRepository.deleteAll();
        productRepository.save(Product.of(
//                1L,
                "Brown Brim",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/ZYW3VTp/brown-brim.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                2L,
                "Blue Beanie",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                17.99,
                "https://i.ibb.co/ypkgK0X/blue-beanie.png",
                "Hats",
                "Generic",
                20
        ));

        productRepository.save(Product.of(
//                3L,
                "Brown Cowboy",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                34.99,
                "https://i.ibb.co/QdJwgmp/brown-cowboy.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                4L,
                "Grey Brim",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/RjBLWxB/grey-brim.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                5L,
                "Green Beanie",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                17.99,
                "https://i.ibb.co/YTjW3vF/green-beanie.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                6L,
                "Palm Tree Cap",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                13.99,
                "https://i.ibb.co/rKBDvJX/palm-tree-cap.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                7L,
                "Red Beanie",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                17.99,
                "https://i.ibb.co/bLB646Z/red-beanie.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                8L,
                "Wolf Cap",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                13.99,
                "https://i.ibb.co/1f2nWMM/wolf-cap.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                9L,
                "Blue Snapback",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                15.99,
                "https://i.ibb.co/X2VJP2W/blue-snapback.png",
                "Hats",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                10L,
                "Adidas NMD",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                219.99,
                "https://i.ibb.co/0s3pdnc/adidas-nmd.png",
                "Sneakers",
                "Adidas",
                20
        ));
        productRepository.save(Product.of(
//                11L,
                "Adidas Yeezy",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                279.99,
                "https://i.ibb.co/dJbG1cT/yeezy.png",
                "Sneakers",
                "Adidas",
                20
        ));
        productRepository.save(Product.of(
//                12L,
                "Black Converse",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                109.99,
                "https://i.ibb.co/bPmVXyP/black-converse.png",
                "Sneakers",
                "Converse",
                20
        ));
        productRepository.save(Product.of(
//                13L,
                "Nike White AirForce",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                159.99,
                "https://i.ibb.co/1RcFPk0/white-nike-high-tops.png",
                "Sneakers",
                "Nike",
                20
        ));
        productRepository.save(Product.of(
//                14L,
                "Nike Red High Tops",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                159.99,
                "https://i.ibb.co/QcvzydB/nikes-red.png",
                "Sneakers",
                "Nike",
                20
        ));
        productRepository.save(Product.of(
//                15L,
                "Nike Brown High Tops",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                159.99,
                "https://i.ibb.co/fMTV342/nike-brown.png",
                "Sneakers",
                "Nike",
                20
        ));
        productRepository.save(Product.of(
//                16L,
                "Air Jordan Limited",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                189.99,
                "https://i.ibb.co/w4k6Ws9/nike-funky.png",
                "Sneakers",
                "Nike",
                20
        ));
        productRepository.save(Product.of(
//                17L,
                "Timberlands",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                199.99,
                "https://i.ibb.co/Mhh6wBg/timberlands.png",
                "Sneakers",
                "Timberlands",
                20
        ));
        productRepository.save(Product.of(
//                18L,
                "Black Jean Shearling",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                124.99,
                "https://i.ibb.co/XzcwL5s/black-shearling.png",
                "Jackets",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                19L,
                "Blue Jean Jacket",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                89.99,
                "https://i.ibb.co/mJS6vz0/blue-jean-jacket.png",
                "Jackets",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                20L,
                "Grey Jean Jacket",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                89.99,
                "https://i.ibb.co/N71k1ML/grey-jean-jacket.png",
                "Jackets",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                21L,
                "Brown Shearling",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                164.99,
                "https://i.ibb.co/s96FpdP/brown-shearling.png",
                "Jackets",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                22L,
                "Tan Trench",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                184.99,
                "https://i.ibb.co/M6hHc3F/brown-trench.png",
                "Jackets",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                23L,
                "Blue Tanktop",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/7CQVJNm/blue-tank.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                24L,
                "Floral Blouse",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                19.99,
                "https://i.ibb.co/4W2DGKm/floral-blouse.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                25L,
                "Floral Dress",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                29.99,
                "https://i.ibb.co/KV18Ysr/floral-skirt.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                26L,
                "Red Dots Dress",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                79.99,
                "https://i.ibb.co/N3BN1bh/red-polka-dot-dress.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                27L,
                "Striped Sweater",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                44.99,
                "https://i.ibb.co/KmSkMbH/striped-sweater.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                28L,
                "Yellow Track Suit",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                134.99,
                "https://i.ibb.co/v1cvwNf/yellow-track-suit.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                29L,
                "White Blouse",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                19.99,
                "https://i.ibb.co/qBcrsJg/white-vest.png",
                "Womens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                30L,
                "Camo Down Vest",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                324.99,
                "https://i.ibb.co/xJS0T3Y/camo-vest.png",
                "Mens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                31L,
                "Floral T-Shirt",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                19.99,
                "https://i.ibb.co/qMQ75QZ/floral-shirt.png",
                "Mens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                32L,
                "Black & White Longsleeve",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/55z32tw/long-sleeve.png",
                "Mens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                33L,
                "Pink T-Shirt",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/RvwnBL8/pink-shirt.png",
                "Mens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                34L,
                "Jean Long Sleeve",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                39.99,
                "https://i.ibb.co/VpW4x5t/roll-up-jean-shirt.png",
                "Mens",
                "Generic",
                20
        ));
        productRepository.save(Product.of(
//                35L,
                "Burgundy T-Shirt",
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Maecenas porttitor congue massa. Fusce posuere, magna sed pulvinar ultricies, purus lectus malesuada libero, sit amet commodo magna eros quis urna.",
                24.99,
                "https://i.ibb.co/mh3VM1f/polka-dot-shirt.png",
                "Mens",
                "Generic",
                20
        ));

    }
}
