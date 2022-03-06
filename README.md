## Order placing application

### Description

The application allows users to place orders, view products offered and view the status of their orders.
It also allows an administrator to view all the orders placed and process them.

### Technologies used

It uses an angular js frontend and a spring boot backend.
Database used at the backend is mysql.
The frontend uses a rest api to access the backend.
Authentication is done using JWT

### Routes

1. frontend routes:

- / - **The home page**
- /orders - **Allows users to view their orders**
- /admin - **Administrator's home page**
- /admin/login - **Login page for the administrator**
- /products/:product-id - **Product detail page**

---

2. backend routes:

- /api/users/login - **Login route for the administrator**
- /api/users/logout - **Logout route for the administrator**
- /api/products - **Route for getting all the products**
- /api/products/:product-id - **View product details**
- /api/orders/:order-id - **Process an order**
- /api/orders - **View all the orders**

