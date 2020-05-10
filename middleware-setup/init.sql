-- Intial idenity_service_db;
create database emart_identity_service_db;
create user emart_identity_service_db  IDENTIFIED BY 'Test123';
GRANT ALL PRIVILEGES ON emart_identity_service_db. * TO  emart_identity_service_db;
FLUSH PRIVILEGES;

create database emart_inventory_service_db;
create user emart_inventory_service_db  IDENTIFIED BY 'Test123';
GRANT ALL PRIVILEGES ON emart_inventory_service_db. * TO emart_inventory_service_db;
FLUSH PRIVILEGES;

create database emart_order_service_db;
create user emart_order_service_db  IDENTIFIED BY 'Test123';
GRANT ALL PRIVILEGES ON emart_order_service_db. * TO emart_order_service_db;
FLUSH PRIVILEGES;

create database emart_financial_service_db;
create user emart_financial_service_db  IDENTIFIED BY 'Test123';
GRANT ALL PRIVILEGES ON emart_financial_service_db. * TO emart_financial_service_db;
FLUSH PRIVILEGES;

create database emart_mock_payment_db;
create user emart_mock_payment_db  IDENTIFIED BY 'Test123';
GRANT ALL PRIVILEGES ON emart_mock_payment_db. * TO emart_mock_payment_db;
FLUSH PRIVILEGES;

