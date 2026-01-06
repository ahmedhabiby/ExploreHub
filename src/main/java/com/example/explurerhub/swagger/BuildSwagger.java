package com.example.explurerhub.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.stereotype.Component;

@Component
@OpenAPIDefinition(
        info = @Info(
                title = "ExploreHub App",
                description = "Application That Help Tourists To Discover Egypt",
                contact = @Contact(
                        name = "Eng Ahmed Mohamed Habiby ",
                        email = "ahmednsra329@gmail.com",
                        url = "http://localhost:8080/demo"
                )
        )

)
public class BuildSwagger {
}
