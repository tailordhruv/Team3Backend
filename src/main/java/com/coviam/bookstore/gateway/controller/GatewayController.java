package com.coviam.bookstore.gateway.controller;

import com.coviam.bookstore.gateway.client.*;
import com.coviam.bookstore.gateway.DTO.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/router")
public class GatewayController {

    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private MerchantClient merchantClient;
    @Autowired
    private CustomerClient customerClient;
    @Autowired
    private LoginClient loginClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private CartClient cartClient;


    //---------------------------------LOGIN-----------------------------------





    @PostMapping("/signup")
    String signup(@RequestBody SignupDTO signupDTO){
        System.out.println(signupDTO);
        String responseEntity = loginClient.signup(signupDTO);
        if(responseEntity!=null && "customer".equals(signupDTO.getLoginType())) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setAddress(signupDTO.getAddress());
            customerDTO.setCustomerId(responseEntity);
            customerDTO.setName(signupDTO.getName());
            customerDTO.setEmail(signupDTO.getEmail());
            customerDTO.setPhoneNumber(signupDTO.getPhoneNumber());
            customerDTO.setPincode(signupDTO.getPincode());
            System.out.println("connecting to clinet");
            System.out.println("-->" + customerDTO.getCustomerId());
            System.out.println("{\"customerId\":\""+customerClient.add(customerDTO)+"\"}");
            return "{\"response\":\""+customerClient.add(customerDTO)+"\"}";
        }
        if(responseEntity!=null && "merchant".equals(signupDTO.getLoginType())) {
            MerchantDTO merchantDTO = new MerchantDTO();
            merchantDTO.setMerchantAddress(signupDTO.getAddress());
            merchantDTO.setMerchantId(responseEntity);
            merchantDTO.setMerchantName(signupDTO.getName());
            merchantDTO.setMerchantEmail(signupDTO.getEmail());
            merchantDTO.setMerchantPhone(signupDTO.getPhoneNumber());
            merchantDTO.setPincode(signupDTO.getPincode());
            System.out.println("connecting to clinet");
            System.out.println("-->" + merchantDTO.getMerchantId());
            return "{\"response\":\""+merchantClient.addMerchant(merchantDTO)+"\"}";
        }
        else
            return "{\"response\":\"Already Exist\"}";
    }


    @PostMapping("/login")
    String login(@RequestBody LoginDTO loginDTO){
        System.out.println("Login called"+loginDTO);
        String status;
        status=loginClient.login(loginDTO);
        if(loginDTO.getDeviceId()!=null){
            CartUpdateDTO cartUpdateDTO=new CartUpdateDTO();
            cartUpdateDTO.setCartId(loginDTO.getDeviceId());
            cartUpdateDTO.setCustomerId(status);
            System.out.println("Upadating cart----->"+cartUpdateDTO);
            cartClient.updateGuestCart( cartUpdateDTO);
        }

        return "{\"response\":\""+status+"\"}";

    }


    @GetMapping("/signout")
    void signOut(){
        loginClient.signout();
    }
    //---------------------------------PRODUCT AND MERCHANT-----------------------------------




    @GetMapping("/getGenreList")
    ArrayList<String> getGenreList(){
        return (ArrayList<String>) productClient.getGenreList();
    }



    @PostMapping("/addProduct")
    String addProduct(@RequestBody ProductDetailsDTO productDetailsDTO){
        ProductDTO productDTO=new ProductDTO();

        //HashMap<String,String> attributes = new HashMap<String,String>();
        BeanUtils.copyProperties(productDetailsDTO,productDTO);
        //attributes.put("binding_type",productDetailsDTO.getBinding_type());
        //attributes.put("year_of_publishing",productDetailsDTO.getYear_of_publishing());
        //productDTO.setAttributes(attributes);
        String product_id=productClient.addProduct(productDTO);

        ProductMerchantDTO productMerchantDTO=new ProductMerchantDTO();
        System.out.println();
        productMerchantDTO.setMerchantId(productDetailsDTO.getMerchantId());
        productMerchantDTO.setProductId(product_id);
        productMerchantDTO.setPrice(productDetailsDTO.getPrice());
        productMerchantDTO.setQuantity(productDetailsDTO.getQuantity());
        System.out.println(productMerchantDTO);
        merchantClient.addProductMerchant(productMerchantDTO);
        return "{\"response\":\"Success\"}";
    }







    @DeleteMapping("/removeProduct")
    String remove(@RequestBody RemoveProductDTO removeProductDTO){
        System.out.println(removeProductDTO.getProductId());
        productClient.deleteProductById(removeProductDTO.getProductId());
        merchantClient.removeProduct(removeProductDTO);
        return "{\"response\":\"Success\"}";

    }

    @GetMapping("/getProductByMerchantId/{id}")
    List<ProductDetailsDTO> getProductByMerchantid(@PathVariable("id") String merchantId){
        List<ProductMerchantDTO> productMerchantList = merchantClient.getProductMerchantByMerchantId(merchantId);
        System.out.println(productMerchantList);
        ArrayList<ProductDetailsDTO> productDetailsDTOS=new ArrayList<ProductDetailsDTO>();
        for(ProductMerchantDTO productMerchant:productMerchantList){
            System.out.println(productMerchant);
            ProductDTO product=productClient.getProductById(productMerchant.getProductId());
            ProductDetailsDTO productDetailsDTO1=new ProductDetailsDTO();
            productDetailsDTO1.setProductId(product.getProductId());
            productDetailsDTO1.setProductName(product.getProductName());
            productDetailsDTO1.setGenre(product.getGenre());
            productDetailsDTO1.setAttributes(product.getAttributes());
            productDetailsDTO1.setDescription(product.getDescription());
            productDetailsDTO1.setAuthor(product.getAuthor());
            productDetailsDTO1.setUrl(product.getUrl());
            productDetailsDTO1.setIsbn(product.getIsbn());
            productDetailsDTO1.setPrice(product.getPrice());
            productDetailsDTO1.setQuantity(productMerchant.getQuantity());
            productDetailsDTOS.add(productDetailsDTO1);
        }
        System.out.println(productDetailsDTOS);
        return productDetailsDTOS;
    }

    @GetMapping("/getMerchantByProductId/{id}")
    List<MerchantDTO> getMerchantByProductId(@PathVariable("id") String productId){
        return merchantClient.getMerchantByProductId(productId);
    }

    @GetMapping("/getProductByGenre/{genre}")
    List<ProductDTO3> getProductByGenre(@PathVariable("genre") String genre){

        return productClient.getProductByGenre(genre);
    }

    @GetMapping("/getMerchantById/{id}")
    MerchantDTO getMerchantById(@PathVariable("id") String merchantId){
        return merchantClient.getMerchantById(merchantId);
    }

    @PostMapping("/addMerchantRating")
    String addMerchantRating(@RequestBody MerchantRatingDTO merchantRatingDTO){
        return merchantClient.addMerchantRating(merchantRatingDTO);
    }

    @GetMapping("/getProductByProductId/{id}")
    ProductDetailsDTO getProductbyProductId(@PathVariable("id") String productId){
        ProductDetailsDTO productDetailsDTO=new ProductDetailsDTO();
        ProductDTO productDTO = productClient.getProductById(productId);
        BeanUtils.copyProperties(productDTO,productDetailsDTO);
        ProductMerchantDTO productMerchantDTO=merchantClient.getDefaultMerchantByProductId(productId);
        BeanUtils.copyProperties(productMerchantDTO,productDetailsDTO);
        return productDetailsDTO;
    }

    @GetMapping("/getTopProducts")
    List<ProductDTO3> getTopProducts(){
        return productClient.getTopProducts();
    }


    //---------------------------------CUSTOMER -----------------------------------

    @GetMapping("/getCustomerById/{id}")
    CustomerDTO getCustomerById(@PathVariable("id") String customerId){
        return customerClient.getCustomerById(customerId);
    }


    //-----------------------------------ORDER-------------------------------------


    @PostMapping("/checkout")
    ResponseEntity<String> checkout(@RequestBody List<CheckoutDTO> checkoutDTOList){
        OrderDTO orderDTO=new OrderDTO();
        for(CheckoutDTO checkoutDTO:checkoutDTOList){
            QuantityUpdateDTO quantityUpdateDTO=new QuantityUpdateDTO();
            BeanUtils.copyProperties(checkoutDTO,quantityUpdateDTO);
            Boolean quantityCheck = merchantClient.checkQuantity(quantityUpdateDTO);
            if(quantityCheck){
                BeanUtils.copyProperties(checkoutDTO,orderDTO);
                String email=customerClient.getEmailById(checkoutDTO.getCustomerId());
                orderDTO.setCustomerEmail(email);
                String orderId=orderClient.addOrder(orderDTO);
                checkoutDTO.setOrderId(orderId);
                orderDTO.setOrderId(orderId);
                orderClient.addOrderDetails(checkoutDTO);
                System.out.println(orderDTO);
                merchantClient.updateQuantity(quantityUpdateDTO);
            }
            else{
                return new ResponseEntity<String>("Falied",HttpStatus.OK);
            }
        }


            orderClient.sendEmail(orderDTO);
        return new ResponseEntity<String>("Order Confirmed",HttpStatus.OK);
//        QuantityUpdateDTO quantityUpdateDTO=new QuantityUpdateDTO();
//        BeanUtils.copyProperties(checkoutDTO,quantityUpdateDTO);
//        Boolean quantityCheck = merchantClient.checkQuantity(quantityUpdateDTO);
//        OrderDTO orderDTO=new OrderDTO();
//        BeanUtils.copyProperties(checkoutDTO,orderDTO);
//        String email=customerClient.getEmailById(checkoutDTO.getCustomerId());
//        orderClient.addOrderDetails(orderDTO);
//        merchantClient.updateQuantity(quantityUpdateDTO);
//        return new ResponseEntity<String>("Order Confirmed",HttpStatus.OK);
    }





    //---------------------------------_CART----------------------------------------



    @PostMapping("/addToCart")
    ResponseEntity<String> add(@RequestBody CartDTO cartDTO){
        cartClient.add(cartDTO);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

    @DeleteMapping("/removeFromCart")
    ResponseEntity<String> remove(@RequestBody CartDTO cartDTO){
        cartClient.remove(cartDTO);
        return new ResponseEntity<String>("Success",HttpStatus.OK);
    }

    @DeleteMapping("/removeAllFromCart")
    ResponseEntity<String> removeAll(@RequestBody RemoveAllDTO removeAllDTO){
        cartClient.removeAll(removeAllDTO);
        return new ResponseEntity<String>("{'status':'Success'}",HttpStatus.OK);
    }


    @GetMapping("/getFromCart/{id}")
    List<CartListDTO> getFromCart(@PathVariable("id") String cartId){
        List<CartDTO> cartDTOList=cartClient.getByCartId(cartId);
        System.out.println(cartDTOList);
        List<CartListDTO> cartItemsListDTO=new ArrayList<>();
        for(CartDTO cartDTO:cartDTOList){
            CartListDTO cartListDTO=new CartListDTO();
            BeanUtils.copyProperties(cartDTO,cartListDTO);
            ProductDTO productDTO=productClient.getProductById(cartDTO.getProductId());
            BeanUtils.copyProperties(productDTO,cartListDTO);
            cartListDTO.setProductId(cartDTO.getProductId());
            cartListDTO.setMerchantId(cartDTO.getMerchantId());
            cartItemsListDTO.add(cartListDTO);
        }
        return cartItemsListDTO;
    }


    //-----------------------------------SEarch----------------------------------------
    @GetMapping("/search")
    List<SearchDocumentDTO> search(@RequestParam("keyword")String keyword){
        return searchClient.search(keyword);
    }
}
