#!/usr/bin/env bash
#wget "https://rpmfind.net/linux/centos/7.5.1804/extras/x86_64/Packages/ansible-2.4.2.0-2.el7.noarch.rpm"
#sudo yum install ansible-2.4.2.0-2.el7.noarch.rpm
sudo yum install -y epel-release
sudo yum install -y ansible
sudo yum install -y git
export PROJECT_FOLDER="/home/vagrant/pliki"
export GIT_PROJECT_URL="https://bitbucket.org/mpelczarski/vagrantproject/src/master/"
git clone $GIT_PROJECT_URL $PROJECT_FOLDER
chown -R vagrant:vagrant $PROJECT_FOLDER
cp -ar /home/vagrant/pliki/hosts /etc/ansible/hosts