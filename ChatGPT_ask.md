Por favor, necesitaría saber el código para la siguiente tarea:
En un proyecto de Spring Boot 17, con las librerías:
spring-boot-starter-parent
spring-boot-starter-actuator
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-thymeleaf
spring-boot-starter-web
thymeleaf-extras-springsecurity6
spring-boot-devtools
mysql-connector-j
lombok
spring-boot-starter-test
spring-security-test
hibernate-community-dialects
jjwt-api 0.11.5
jjwt-impl 0.11.5
jjwt-jackson 0.11.5
springdoc-openapi-starter-webmvc-ui 2.8.3

Quiero crear dos Java Package:
Uno con el modelo, respositorio, servicio y controlador de usuarios con el id id_usuario.
Otro con un modelo, repositorio, servicio y controlador hipotecas con el id id_hisopteca.
Entre las dos entidades quiero establecer una relación mucho a mucho con una tabla alternativa usuario_hipoteca, y es que un usuario puede tener varias hipotecas y una hipoteca puede pertenecer a varios usuarios.

Quiero crear dos Java Package:
Uno para usuario con modelo, respositorio, servicio y controlador de usuarios con el id id_usuario.
Otro para creditos con un modelo, repositorio, servicio y controlador de creditos con el id id_credito.
Entre las dos entidades quiero establecer una relación uno a mucho, y es que un usuario puede tener varias creditos y una credito puede pertenecer a un usuarios.