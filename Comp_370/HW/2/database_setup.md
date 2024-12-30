# Database Setup

- Install MariaDB
- Run sudo mysql_secure_installation
- Go to /etc/mysql/my.cnf and change the port to 6002
- Edit bind address in /etc/mysql/mariadb.conf.d/50-server.cnf and set it to:
  `0.0.0.0`
- Go into MariaDB
  - sudo mysql -u root
- Create database
  - Create Database comp370_test;
- Create User
  - CREATE USER 'comp370'@'%' IDENTIFIED BY '$ungl@ss3s';
- Grant Privilege
  - GRANT ALL PRIVILEGES ON comp370_test.\* TO 'comp370'@'%' IDENTIFIED BY '$ungl@ss3s';
