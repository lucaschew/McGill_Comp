# Webserver Setup

## Create EC2 Server

- Create account on AWS
- Create Lightsail EC2 instance
- Generate ssh keypair
- Connect to server with given IP from AWS using keypair

## Set File as www Root

- Install Apache with sudo
- Go to apache2/ folder and edit ports.conf to replace 80 with 8008
- Go to apache2/sites-enabled and edit 000-default.conf and replace 80 with 8008
- Go to Lightsail on AWS and enable port 8008 on firewall
- Create comp370_hw2.txt file in /var/www/html
