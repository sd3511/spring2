package ru.geekbrains.spring.winter.market.endpoints;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.spring.winter.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.winter.market.services.ProductService;
import ru.geekbrains.spring.winter.market.soap.products.GetAllProductsRequest;
import ru.geekbrains.spring.winter.market.soap.products.GetAllProductsResponse;
import ru.geekbrains.spring.winter.market.soap.products.GetProductByIdRequest;
import ru.geekbrains.spring.winter.market.soap.products.GetProductByIdResponse;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/spring/winter/market/products";
    private final ProductService productService;

    /*
        Пример запроса: POST http://localhost:8080/winter/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
          xmlns:f="http://www.geekbrains.ru/spring/winter/market/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getProductByIdRequest>
                    <f:id>1</f:id>
                </f:getProductByIdRequest>
            </soapenv:Body>
        </soapenv:Envelope>
     */

     /*

      <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/spring/winter/market/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>

        */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    @Transactional
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();

        productService.getAllProducts().forEach(p->response.getProductSoaps().add(p));
        return response;

    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    @Transactional
    public GetProductByIdResponse getGroupByTitle(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        response.setProductSoap(productService.getById(request.getId()).orElseThrow(()->new ResourceNotFoundException("Product not found")));
        return response;
    }



}
