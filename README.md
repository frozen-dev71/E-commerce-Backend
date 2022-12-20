# E-commerce Backend
This REST API was created for an E-Commerce Project. This API handles all of the basic CRUD functions of an E-commerce Application platform, including validation at each stage.

# About the Project
- You can see API Documentation at API root end Point via Swagger.
- Authentication and validation for customer and admin was made using the Session UUID.
- Lombok library used so I minimized boilerplate code in the project.


# Tech Stack
+ [X] Java
+ [X] Springboot
+ [X] Spring Data JPA
+ [X] Hibernate
+ [X] Lombok
+ [X] MySQL
+ [X] Swagger
+ [X] PostMan
---
# Modules

## Admin Module operations

+ [X] Add new products
+ [X] Manage product quantities
+ [X] All of the Order's management
+ [X] Search by customer details ( name, e-mail, phone, id )
+ [X] Customer management
+ [X] Search by order
+ [X] Admin and Customers ( different login )

## Customer Module operations

+ [X] Login and Register
+ [X] See cart details and total price
+ [X] Add to cart
+ [X] Make a purchase, and track its status
+ [X] Search by category
+ [X] Payment

## API Root End Point
<code>http://localhost:8880/swagger-ui/</code>


## E-R Diagram

![e-r](https://user-images.githubusercontent.com/44535117/208772546-433860dc-1d5a-474b-a834-ec48086d0089.png)

---

## API Module End Points

### Sample API Response for Admin Login

<code>POST localhost:8080/admin/login </code>

- Request Body
<pre class="notranslate"> 
<code>
{
  "adminId": 15,
  "password": "berkbeleli"
}
</code>
</pre>
- Response
<pre class="notranslate"> 
<code>
   CurrentAdminSession(userId=15, uuid=E4Jiid, localDateTime=2022-12-21T00:39:58.270034)
</code>
</pre>

---

### Swagger UI

<img width="1433" alt="Screenshot 2022-12-21 at 12 43 46 AM" src="https://user-images.githubusercontent.com/44535117/208773525-d5e74bb3-bfc3-481c-bc01-eda5a9486b9e.png">

---

### Admin Controller
<img width="1438" alt="Screenshot 2022-12-21 at 12 44 53 AM" src="https://user-images.githubusercontent.com/44535117/208773677-cf494ef8-31b6-447b-a876-439199942c45.png">

---

### Customer Controller
<img width="1439" alt="Screenshot 2022-12-21 at 12 45 17 AM" src="https://user-images.githubusercontent.com/44535117/208773739-f74b974b-2449-478b-aabe-6a97ec4bc7d4.png">


---

### Order Controller
<img width="1438" alt="Screenshot 2022-12-21 at 12 45 35 AM" src="https://user-images.githubusercontent.com/44535117/208773793-93d091be-e3d6-4089-b815-b644f3c9b82f.png">


---

### Product Controller
<img width="1439" alt="Screenshot 2022-12-21 at 12 45 46 AM" src="https://user-images.githubusercontent.com/44535117/208773819-57d203a4-4a40-4788-873d-5036c9260e89.png">







