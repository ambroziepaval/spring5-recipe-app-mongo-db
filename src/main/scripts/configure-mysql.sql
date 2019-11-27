## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE s5recipe_dev;
CREATE DATABASE s5recipe_prod;

#Create database service accounts
CREATE USER 's5recipe_dev_user'@'localhost' IDENTIFIED BY 'zica';
CREATE USER 's5recipe_prod_user'@'localhost' IDENTIFIED BY 'zica';
CREATE USER 's5recipe_dev_user'@'%' IDENTIFIED BY 'zica';
CREATE USER 's5recipe_prod_user'@'%' IDENTIFIED BY 'zica';

#Database grants
GRANT SELECT ON s5recipe_dev.* to 's5recipe_dev_user'@'localhost';
GRANT INSERT ON s5recipe_dev.* to 's5recipe_dev_user'@'localhost';
GRANT DELETE ON s5recipe_dev.* to 's5recipe_dev_user'@'localhost';
GRANT UPDATE ON s5recipe_dev.* to 's5recipe_dev_user'@'localhost';
GRANT SELECT ON s5recipe_prod.* to 's5recipe_prod_user'@'localhost';
GRANT INSERT ON s5recipe_prod.* to 's5recipe_prod_user'@'localhost';
GRANT DELETE ON s5recipe_prod.* to 's5recipe_prod_user'@'localhost';
GRANT UPDATE ON s5recipe_prod.* to 's5recipe_prod_user'@'localhost';

GRANT SELECT ON s5recipe_dev.* to 's5recipe_dev_user'@'%';
GRANT INSERT ON s5recipe_dev.* to 's5recipe_dev_user'@'%';
GRANT DELETE ON s5recipe_dev.* to 's5recipe_dev_user'@'%';
GRANT UPDATE ON s5recipe_dev.* to 's5recipe_dev_user'@'%';
GRANT SELECT ON s5recipe_prod.* to 's5recipe_prod_user'@'%';
GRANT INSERT ON s5recipe_prod.* to 's5recipe_prod_user'@'%';
GRANT DELETE ON s5recipe_prod.* to 's5recipe_prod_user'@'%';
GRANT UPDATE ON s5recipe_prod.* to 's5recipe_prod_user'@'%';
